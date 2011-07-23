package shooter.goals;

import shooter.unit.MovingEntity;

public class Roam extends BaseGoal {

    @Override
    protected void activateGoal(MovingEntity entity) {
        entity.steering().wanderOn();
    }

    @Override
    protected void processGoal(MovingEntity entity) {
    }

}
