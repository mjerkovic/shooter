package shooter.goals;

import shooter.unit.MovingEntity;

public class Roam extends SimpleGoal<MovingEntity> {

    @Override
    protected void doActivation(MovingEntity entity) {
        entity.steering().wanderOn();
    }

    @Override
    protected GoalState doProcess(MovingEntity entity) {
        return state;
    }

    @Override
    protected void doTermination(MovingEntity entity) {
        entity.steering().wanderOff();
    }

    @Override
    public String description() {
        return "Wandering";
    }

}
