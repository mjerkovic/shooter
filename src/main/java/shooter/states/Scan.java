package shooter.states;

import shooter.unit.Vehicle;
import shooter.unit.WatchTower;

public class Scan implements State<WatchTower> {

    private final StateMachine<WatchTower> stateMachine;
    private Vehicle target;

    public Scan(StateMachine<WatchTower> stateMachine) {
        this.stateMachine = stateMachine;
    }

    public void enter(WatchTower tower) {
    }

    public void execute(WatchTower tower) {
        target = tower.findTarget();
        if (target != null) {
            stateMachine.changeStateTo(new Track(stateMachine, target));
        }
    }

    public void terminate(WatchTower tower) {
    }

}
