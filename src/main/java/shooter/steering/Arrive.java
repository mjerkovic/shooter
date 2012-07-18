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
        //System.out.print("MovingTo: " + targetPosition + "\t");
        Vector toTarget = targetPosition.subtract(entity.position());
        //System.out.print("toTarget: " + toTarget + "\t");
        double dist = toTarget.length();
        //System.out.print("dist: " + dist + "\n");
        if (dist > 0) {
            double tweaker = 20;
            double speed = dist / ((Arrival.FAST.getDeceleration() * tweaker));
            //System.out.print("speed: " + speed + "\t");
            speed = min(speed, entity.getMaxSpeed());
            //System.out.print("adjustedSpeed: " + speed + "\t");
            Vector desiredVelocity = toTarget.scale(speed / dist);
            //System.out.print("desiredVelocity: " + desiredVelocity + "\n");
            //System.out.print("desiredVelocity.subtract(entity.velocity()y: " + desiredVelocity.subtract(entity.velocity()) + "\n");
            return desiredVelocity.subtract(entity.velocity());
        }
        return Vector.ZERO;
    }

}
