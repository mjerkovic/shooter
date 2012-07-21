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

    public Miner(Vector position, double radius, Army army, Vector heading, double maxTurnRate, Goal<Miner> goal,
                 Steering steering, int capacity) {
        super(position, radius, army, heading, maxTurnRate, goal, steering);
        this.capacity = capacity;
    }

    public boolean atMine() {
        return intersects(workingMine);
    }

    public void moveToClosestMine() {
        workingMine = army.getClosestMine();
        steering().arriveOn(workingMine.position());
    }

    public void work() {
        if (++cycles % LOAD_EVERY == 0) {
            workingMine.mine(this);
        }
    }

    public boolean finishedWork() {
        return isFull() || workingMine.isEmpty();
    }

    public void load() {
        load++;
    }

    private boolean isEmpty() {
        return load == 0;
    }

    private boolean isFull() {
        return load == capacity;
    }

    public void leaveMine() {
        workingMine = null;
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
