package shooter.goals.watchtower;

import static shooter.goals.GoalState.COMPLETED;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.TargetingSystem;

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

    @Override
    protected void doTermination(TargetingSystem targetingSystem) {
        targetingSystem.stopTracking();
    }

}
