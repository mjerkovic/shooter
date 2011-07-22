package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class SeekBehaviour implements SteeringBehaviour {

    private final MovingEntity entity;
    private final Vector targetPosition;

    public SeekBehaviour(MovingEntity entity, Vector targetPosition) {
        this.entity = entity;
        this.targetPosition = targetPosition;
    }

    public Vector calculate() {
        Vector desiredVelocity = targetPosition.subtract(entity.getPosition()).normalise().scale(entity.getMaxSpeed());
        return desiredVelocity.subtract(entity.getVelocity());
    }

    public void terminate() {
        entity.stop();
    }

}
