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
        double relativeHeading = pursuer.getHeading().dot(evader.getHeading());
        if ((toEvader.dot(pursuer.getHeading()) > 0) && (relativeHeading < 0.95)) {
            return new Seek(pursuer, evader.getPosition()).calculate();
        }
        double lookAheadTime = toEvader.length() / (pursuer.getMaxSpeed() + evader.getVelocity().length());
        return new Seek(pursuer, evader.getPosition().add((evader.getVelocity().scale(lookAheadTime)))).calculate();
    }

}
