package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class Flee implements SteeringBehaviour {

    private final MovingEntity entity;
    private final Vector toPosition;

    public Flee(MovingEntity entity, Vector toPosition) {
        this.entity = entity;
        this.toPosition = toPosition;
    }

    public Vector calculate() {
        Vector desiredVelocity = entity.position().subtract(toPosition).normalise().scale(entity.getMaxSpeed());
        return desiredVelocity.subtract(entity.velocity());
    }

}
