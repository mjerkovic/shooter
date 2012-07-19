package shooter.unit;

import shooter.geom.Vector;
import shooter.states.StateMachine;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.unit.structure.Mine;

public class Miner extends MovingEntity {

    private static final int LOAD_EVERY = 100;

    private final int capacity;
    private final StateMachine<Miner> stateMachine;
    private Mine workingMine;
    private int cycles;
    private int load;

    public Miner(Army army, Vector position, Vector heading, Steering steering, int capacity,
                 StateMachine<Miner> stateMachine) {
        super(army, null, steering);
        this.capacity = capacity;
        this.stateMachine = stateMachine;
        army.add(this);
        steering.setOwner(this);
        this.position = position;
        this.heading = heading;
    }

    public double x() {
        return position.x();
    }

    public double y() {
        return position.y();
    }

    @Override
    public void update() {
        stateMachine.update(this);
        calculateSteering();
    }

    public boolean atMine() {
        return workingMine.position.subtract(this.position()).length() <= workingMine.boundingRadius();
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
