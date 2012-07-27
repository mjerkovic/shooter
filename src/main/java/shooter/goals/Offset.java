package shooter.goals;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class Offset extends SimpleGoal<MovingEntity> {

    private final MovingEntity leader;
    private final Vector offset;

    public Offset(MovingEntity leader, Vector offset) {
        this.leader = leader;
        this.offset = offset;
    }

    @Override
    protected void doActivation(MovingEntity entity) {
        entity.steering().offsetPursuitOn(leader, offset);
        entity.steering().obstacleAvoidanceOn();
    }

    @Override
    protected GoalState doProcess(MovingEntity entity) {
        return null;
    }

    @Override
    protected void doTermination(MovingEntity entity) {
    }

}
