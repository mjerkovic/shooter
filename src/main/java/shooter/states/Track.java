package shooter.states;

import shooter.unit.Vehicle;
import shooter.unit.WatchTower;

public class Track implements State<WatchTower> {

    private final StateMachine<WatchTower> stateMachine;
    private final Vehicle target;

    public Track(StateMachine<WatchTower> stateMachine, Vehicle target) {
        this.stateMachine = stateMachine;
        this.target = target;
    }

    public void enter(WatchTower tower) {
        System.out.println("Entering Track");
        tower.startTracking(target);
    }

    public void execute(WatchTower tower) {
        if (!tower.inRange(target)) {
            stateMachine.changeStateTo(new Scan(stateMachine));
        }
        tower.fireAt(target);
    }

    public void terminate(WatchTower tower) {
        System.out.println("Terminating Track");
        tower.stopTracking();
    }
}
