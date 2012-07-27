package shooter.unit;

import static shooter.geom.Transformations.rotateAroundOrigin;

import java.util.Collection;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.ui.Renderer;

public class TargetingSystem extends MovingEntity {

    private final Entity owner;
    private final Weapon weapon;
    private final int rangeOfSight;
    private MovingEntity target = null;

    public TargetingSystem(Entity owner, double boundingRadius, MessageDispatcher radio, Goal brain, Movement movement,
                           int rangeOfSight, Weapon weapon) {
        super(new Orientation(owner.position(), owner.heading(), boundingRadius), radio, brain, movement);
        this.owner = owner;
        this.weapon = weapon;
        this.heading = owner.heading();
        this.rangeOfSight = rangeOfSight;
    }

    public boolean acquireTarget(Collection<Vehicle> vehicles) {
        heading = rotateAroundOrigin(heading, maxTurnRate);
        for (Vehicle vehicle : vehicles) {
            if (vehicle != owner && withinRange(vehicle)) {
                target = vehicle;
                return true;
            }
        }
        return false;
    }

    public boolean isInRange() {
        return hasTarget() && withinRange(target);
    }

    public boolean hasTarget() {
        return target != null;
    }

    public void fire() {
        if (hasTarget() && inLineOfSight() && withinRange(target) && weapon.fire()) {
            radio.shotFired(owner, heading, target);
        }
    }

    private boolean inLineOfSight() {
        double angleToTarget = target.position().normalise().dot(heading);
        return withinRange(target) && angleToTarget <= 0.44;
    }

    private boolean withinRange(Entity target) {
        return target.position().subtract(owner.position()).lengthSquared() <= rangeOfSight;
    }

    public void startTracking() {
        stop();
        steering.pursuitOn(target);
    }

    public void stopTracking() {
        steering.pursuitOff();
        stop();
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
