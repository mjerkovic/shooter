package shooter.goals;

import shooter.unit.MovingEntity;

public class Roam extends BaseGoal<MovingEntity> {

    @Override
    protected void activateGoal(MovingEntity entity) {
        entity.steering().wanderOn();
    }

    @Override
    protected GoalState processGoal(MovingEntity entity) {
        return null;
    }

    public void terminate(MovingEntity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
