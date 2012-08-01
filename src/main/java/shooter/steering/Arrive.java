package shooter.steering;


import static org.apache.commons.math3.util.FastMath.min;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class Arrive implements SteeringBehaviour {

    private static enum Arrival {
        FAST(1),
        NORMAL(2),
        SLOW(3);

        private double deceleration;

        private Arrival(double deceleration) {
            this.deceleration = deceleration;
        }

        public double getDeceleration() {
            return deceleration;
        }

    }

    private final MovingEntity entity;
    private final Vector targetPosition;

    public Arrive(MovingEntity entity, Vector targetPosition) {
        this.entity = entity;
        this.targetPosition = targetPosition;
    }

    public Vector calculate() {
        Vector toTarget = targetPosition.subtract(entity.position());
        double dist = toTarget.length();
        if (dist > 0) {
            double tweaker = 20;
            double speed = dist / ((Arrival.FAST.getDeceleration() * tweaker));
            speed = min(speed, entity.getMaxSpeed());
            Vector desiredVelocity = toTarget.scale(speed / dist);
            return desiredVelocity.subtract(entity.velocity());
        }
        return Vector.ZERO;
    }

}
