package shooter.unit;

import static shooter.comms.MessageDispatcher.broadcast;

import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.ui.Renderer;
import shooter.unit.structure.Mine;

public class Miner extends Vehicle {

    private static final int LOAD_EVERY = 100;

    private final int capacity;
    private Mine workingMine;
    private int cycles;
    private int load;

    public Miner(Orientation orientation, Goal<Miner> brain, Movement movement, int capacity) {
        super(orientation, brain, movement);
        this.capacity = capacity;
    }

    public void goToMine(Mine mine) {
        this.workingMine = mine;
        steering.arriveOn(mine.position());
        broadcast(this, "Going to mine");
    }

    public void returnToBase(Vector position) {
        steering.arriveOn(position);
    }

    public void work() {
        if (++cycles % LOAD_EVERY == 0) {
            workingMine.mine(this);
        }
    }

    public boolean finishedWork() {
        return isFull() || nothingToMine();
    }

    public void load() {
        ++load;
    }

    public boolean isEmpty() {
        return load == 0;
    }

    public boolean unload(long elapsed) {
        if (elapsed >= 2000) {
            --load;
            return true;
        }
        return false;
    }

    private boolean isFull() {
        return load == capacity;
    }

    public void leaveMine() {
        cycles = 0;
        workingMine = null;
        broadcast(this, "Leaving mine");
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

    public void arrivedAtMine() {
        steering.arriveOff();
        stop();
    }

    public boolean atMine() {
        return intersects(workingMine);
    }

    public boolean assignedToMine() {
        return workingMine != null;
    }

    public boolean nothingToMine() {
        return workingMine.isEmpty();
    }
}
