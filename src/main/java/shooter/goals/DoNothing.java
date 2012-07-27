package shooter.goals;

import shooter.unit.Entity;

public class DoNothing extends SimpleGoal<Entity> {

    @Override
    protected void doActivation(Entity entity) {
    }

    @Override
    protected GoalState doProcess(Entity entity) {
        return GoalState.ACTIVE;
    }

    @Override
    protected void doTermination(Entity entity) {
    }

}
