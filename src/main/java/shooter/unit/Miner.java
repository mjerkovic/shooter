package shooter.unit;

import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.states.StateMachine;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.unit.structure.Mine;

public class Miner extends Vehicle {

    private static final int LOAD_EVERY = 100;

    private final int capacity;
    private Mine workingMine;
    private int cycles;
    private int load;

    public Miner(Vector position, double radius, Vector heading, double maxTurnRate, Goal<Miner> goal,
                 Steering steering, int capacity) {
        super(position, radius, heading, maxTurnRate, goal, steering);
        this.capacity = capacity;
    }

    public void goToMine(Mine mine) {
        this.workingMine = mine;
        steering.arriveOn(mine.position());
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
    }

    @Override
    public void renderWith(GameRenderer renderer) {
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
