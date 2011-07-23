package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class Fire implements SteeringBehaviour {

    private final MovingEntity shooter;
    private final MovingEntity target;

    public Fire(MovingEntity shooter, MovingEntity target) {
        this.shooter = shooter;
        this.target = target;
    }

    public Vector calculate() {
        Vector toTarget = target.getPosition().subtract(shooter.getPosition());
        double relativeHeading = toTarget.normalise().dot(shooter.heading());
        double range = toTarget.length();
        if (relativeHeading >= 0.99 && range < 200) {
            System.out.println("BANG!");
        }
        return Vector.ZERO;
    }

}
