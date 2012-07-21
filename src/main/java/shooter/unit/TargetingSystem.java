package shooter.unit;

import shooter.geom.Vector;
import shooter.world.ShooterWorld;

public class TargetingSystem {

    private Vehicle target = null;
    private final ShooterWorld world;
    private final WatchTower watchTower;
    private long lastShot;

    public TargetingSystem(ShooterWorld world, WatchTower watchTower) {
        this.world = world;
        this.watchTower = watchTower;
    }

    public boolean acquireTarget(double range) {
        for (Vehicle vehicle : world.getVehicles()) {
            if (inRange(vehicle, range)) {
                target = vehicle;
                return true;
            }
        }
        return false;
    }

    private boolean inRange(Vehicle target, double range) {
        return target.position().subtract(watchTower.position()).lengthSquared() <= range;
    }

    public boolean isInRange(double range) {
        return target != null && inRange(target, range);
    }

    public MovingEntity getTarget() {
        return target;
    }

    public void fire() {
        Vector toTarget = target.position().subtract(watchTower.position);
        double relativeHeading = toTarget.normalise().dot(watchTower.heading);
        double range = toTarget.length();
        if (relativeHeading >= 0.99 && range < 200) {
            shoot();
        }
    }

    private void shoot() {
        long timeDiff = System.currentTimeMillis() - lastShot;
        if (lastShot == 0 || timeDiff >= 1000) {
            world.shotFired(watchTower, target);
            lastShot += timeDiff;
        }
    }

    public void stopTracking() {
        target = null;
    }
}
