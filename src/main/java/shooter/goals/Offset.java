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
        entity.steering().offsetPursuitOn(leader, offset);
        entity.steering().obstacleAvoidanceOn();
    }

    @Override
    protected GoalState processGoal(MovingEntity entity) {
        return null;
    }

    public void terminate(MovingEntity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
