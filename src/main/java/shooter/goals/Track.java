package shooter.goals;

import static shooter.goals.GoalState.COMPLETED;

import shooter.unit.WatchTower;

public class Track extends SimpleGoal<WatchTower> {

    @Override
    protected void doActivation(WatchTower tower) {
        tower.startTracking();
    }

    @Override
    protected GoalState doProcess(WatchTower tower) {
        if (tower.targetAcquired()) {
            tower.fire();
            return GoalState.ACTIVE;
        }
        return COMPLETED;
    }

    public void terminate(WatchTower tower) {
        tower.stopTracking();
    }

}
