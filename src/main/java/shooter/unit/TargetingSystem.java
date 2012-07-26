package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;
import shooter.ui.Renderer;

import java.util.Collection;

import static shooter.geom.Transformations.rotateAroundOrigin;

public class TargetingSystem extends MovingEntity {

    private final Entity owner;
    private final int rangeSquared;
    private MovingEntity target = null;

    public TargetingSystem(Entity owner, double boundingRadius, MessageDispatcher radio,
                           Goal brain, Steering steering, double maxTurnRate, int rangeSquared) {
        super(owner.position(), boundingRadius, radio, brain, steering);
        this.owner = owner;
        this.heading = owner.heading();
        this.rangeSquared = rangeSquared;
        this.maxTurnRate = maxTurnRate;
    }

    @Override
    public void update() {
        brain.process(this);
    }

    public boolean acquireTarget(Collection<Vehicle> vehicles) {
        heading = rotateAroundOrigin(heading, maxTurnRate);
        for (Vehicle vehicle : vehicles) {
            if (vehicle != owner && inRange(vehicle)) {
                target = vehicle;
                return true;
            }
        }
        return false;
    }

    public boolean isInRange() {
        return hasTarget() && inRange(target);
    }

    public boolean hasTarget() {
        return target != null;
    }

    public void fire() {
        Vector toTarget = target.position().subtract(owner.position());
        double relativeHeading = toTarget.normalise().dot(owner.position());
        double range = toTarget.length();
        if (hasTarget() && relativeHeading >= 0.99 && range < 200) {
            radio.shotFired(owner, heading, target);
        }
    }

    private boolean inRange(Entity target) {
        return target.position().subtract(owner.position()).lengthSquared() <= rangeSquared;
    }

    public void startTracking() {
        steering.pursuitOn(target);
    }

    public void stopTracking() {
        target = null;
    }

    @Override
    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

    @Override
    public Vector position() {
        return owner.position();
    }

}
