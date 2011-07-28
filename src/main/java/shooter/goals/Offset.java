package shooter.goals;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class Offset extends BaseGoal<MovingEntity> {

    private final MovingEntity leader;
    private final Vector offset;

    public Offset(MovingEntity leader, Vector offset) {
        this.leader = leader;
        this.offset = offset;
    }

    @Override
    protected void activateGoal(MovingEntity entity) {
        entity.steering().offsetPursuitOn(entity, leader, offset);
        entity.steering().obstacleAvoidanceOn(entity);
    }

    @Override
    protected void processGoal(MovingEntity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
