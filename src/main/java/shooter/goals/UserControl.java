package shooter.goals;

import static shooter.goals.GoalState.ACTIVE;

import shooter.unit.MovingEntity;

public class UserControl extends SimpleGoal<MovingEntity> {

    @Override
    protected void doActivation(MovingEntity entity) {
    }

    @Override
    protected GoalState doProcess(MovingEntity entity) {
        return ACTIVE;
    }

    @Override
    protected void doTermination(MovingEntity entity) {
    }

    @Override
    public String description() {
        return "Under user control";
    }
    
}
