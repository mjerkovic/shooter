package shooter.goals.watchtower;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.TargetingSystem;

import static shooter.goals.GoalState.COMPLETED;

public class Track extends SimpleGoal<TargetingSystem> {

    @Override
    protected void doActivation(TargetingSystem targetingSystem) {
        targetingSystem.startTracking();
    }

    @Override
    protected GoalState doProcess(TargetingSystem targetingSystem) {
        targetingSystem.fire();
        return targetingSystem.isInRange() ? GoalState.ACTIVE : COMPLETED;
    }

    public void terminate(TargetingSystem targetingSystem) {
        targetingSystem.stopTracking();
    }

}
