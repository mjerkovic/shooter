package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class Pursuit implements SteeringBehaviour {

    private final MovingEntity pursuer;
    private final MovingEntity evader;

    public Pursuit(MovingEntity pursuer, MovingEntity evader) {
        this.pursuer = pursuer;
        this.evader = evader;
    }

    public Vector calculate() {
        Vector toEvader = evader.getPosition().subtract(pursuer.getPosition());
        double relativeHeading = pursuer.heading().dot(evader.heading());
        if ((toEvader.dot(pursuer.heading()) > 0) && (relativeHeading < 0.95)) {
            return new Seek(pursuer, evader.getPosition()).calculate();
        }
        double lookAheadTime = toEvader.length() / (pursuer.getMaxSpeed() + evader.velocity().length());
        return new Seek(pursuer, evader.getPosition().add((evader.velocity().scale(lookAheadTime)))).calculate();
    }

}
