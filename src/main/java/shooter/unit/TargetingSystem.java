package shooter.unit;

import shooter.geom.Vector;
import shooter.world.ShooterWorld;

public class TargetingSystem {

    private final ShooterWorld world;
    private final Entity owner;
    private final Weapon weapon;
    private MovingEntity target = null;

    public TargetingSystem(ShooterWorld world, Entity owner, Weapon weapon) {
        this.world = world;
        this.owner = owner;
        this.weapon = weapon;
    }

    public boolean acquireTarget() {
        for (Vehicle vehicle : world.getVehicles()) {
            if (inRange(vehicle)) {
                target = vehicle;
                return true;
            }
        }
        return false;
    }

    private boolean inRange(Entity target) {
        double rangeToTarget = target.position().subtract(owner.position()).lengthSquared();
        return weapon.inRange(rangeToTarget); //target.position().subtract(watchTower.position()).lengthSquared() <= range;
    }

    public boolean isInRange() {
        return target != null && inRange(target);
    }

    public MovingEntity getTarget() {
        return target;
    }

    public void fire() {
        Vector toTarget = target.position().subtract(owner.position());
        double relativeHeading = toTarget.normalise().dot(owner.position());
        double range = toTarget.length();
        if (relativeHeading >= 0.99 && range < 200 && weapon.fire()) {
            world.shotFired(owner, target);
        }
    }

    public void stopTracking() {
        target = null;
    }

}
