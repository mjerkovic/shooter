package shooter.unit;

import java.util.Collection;

import shooter.geom.Vector;

public class TargetingSystem {

    private MovingEntity target = null;

    public boolean acquireTarget(Collection<Vehicle> vehicles, Vector currentPosition, int rangeSquared) {
        for (Vehicle vehicle : vehicles) {
            if (inRange(vehicle, currentPosition, rangeSquared)) {
                target = vehicle;
                return true;
            }
        }
        return false;
    }

    public boolean isInRange(Vector currentPosition, int rangeSquared) {
        return hasTarget() && inRange(target, currentPosition, rangeSquared);
    }

    public boolean hasTarget() {
        return target != null;
    }

    public MovingEntity getTarget() {
        return target;
    }

    public boolean canFire(Vector currentPosition) {
        Vector toTarget = target.position().subtract(currentPosition);
        double relativeHeading = toTarget.normalise().dot(currentPosition);
        double range = toTarget.length();
        return (hasTarget() && relativeHeading >= 0.99 && range < 200);
    }

    private boolean inRange(Entity target, Vector currentPosition, int rangeSquared) {
        return target.position().subtract(currentPosition).lengthSquared() <= rangeSquared;
    }

    public void stopTracking() {
        target = null;
    }

}
