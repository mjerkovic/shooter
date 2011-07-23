package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class Seek implements SteeringBehaviour {

    private final MovingEntity entity;
    private final Vector targetPosition;

    public Seek(MovingEntity entity, Vector targetPosition) {
        this.entity = entity;
        this.targetPosition = targetPosition;
    }

    public Vector calculate() {
        Vector desiredVelocity = targetPosition.subtract(entity.getPosition()).normalise().scale(entity.getMaxSpeed());
        return desiredVelocity.subtract(entity.getVelocity());
    }

}
