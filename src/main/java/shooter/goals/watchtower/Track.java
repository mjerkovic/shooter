package shooter.goals.watchtower;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.WatchTower;

import static shooter.goals.GoalState.COMPLETED;

public class Track extends SimpleGoal<WatchTower> {

    @Override
    protected void doActivation(WatchTower tower) {
        System.out.println("starting tracking");
        tower.startTracking();
    }

    @Override
    protected GoalState doProcess(WatchTower tower) {
        if (tower.targetAcquired()) {
            tower.fire();
        }
        return tower.targetInRange() ? GoalState.ACTIVE : COMPLETED;
    }

    public void terminate(WatchTower tower) {
        System.out.println("terminate tracking");
        tower.stopTracking();
    }

}
