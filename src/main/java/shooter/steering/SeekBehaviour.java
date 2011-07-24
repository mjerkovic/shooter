package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class SeekBehaviour {

    public Vector calculate(MovingEntity entity, Vector targetPosition) {
        Vector desiredVelocity = targetPosition.subtract(entity.position()).normalise().scale(entity.getMaxSpeed());
        return desiredVelocity.subtract(entity.velocity());
    }

}
