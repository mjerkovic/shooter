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

    public void terminate(Entity entity) {
    }

}
