package shooter.steering;

import static java.lang.Math.min;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class ArriveBehaviour implements SteeringBehaviour {

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

    public ArriveBehaviour(MovingEntity entity, Vector targetPosition) {
        this.entity = entity;
        this.targetPosition = targetPosition;
    }

    public Vector calculate() {
        System.out.print("MovingTo: " + targetPosition + "\t");
        Vector toTarget = targetPosition.subtract(entity.getPosition());
        System.out.print("toTarget: " + toTarget + "\t");
        double dist = toTarget.length();
        System.out.print("dist: " + dist + "\t");
        if (dist > 0.1) {
            double tweaker = 0.3;
            double speed = dist / (Arrival.SLOW.getDeceleration() * tweaker);
            System.out.print("speed: " + speed + "\t");
            speed = min(speed, entity.getMaxSpeed());
            System.out.print("adjustedSpeed: " + speed + "\t");
            Vector desiredVelocity = toTarget.scale(speed / dist);
            System.out.print("desiredVelocity: " + desiredVelocity + "\t");
            return desiredVelocity.subtract(entity.getVelocity());
        }
        return Vector.ZERO;
    }

    public void terminate() {
        entity.stop();
    }

}
