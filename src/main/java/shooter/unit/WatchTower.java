package shooter.unit;

import shooter.geom.Vector;
import shooter.states.StateMachine;
import shooter.states.WatchTowerStateMachine;
import shooter.steering.Steering;
import shooter.world.ShooterWorld;

public class WatchTower extends MovingEntity {

    private static final int RANGE_SQUARED = 40000;

    private final ShooterWorld world;
    private final StateMachine<WatchTower> stateMachine;
    private long lastShot;

    public WatchTower(int x, int y, ShooterWorld world, Steering steering) {
        super(null, steering);
        this.world = world;
        this.stateMachine = new WatchTowerStateMachine(this);
        steering.setOwner(this);
        position = new Vector(x, y);
        heading = new Vector(1, 0);
    }

    @Override
    public void update() {
        stateMachine.update();
        Vector steeringForce = steering.calculate();
        if (steeringForce.length() > 0.0001) {
            heading = steeringForce.normalise();
            side = heading.perp();
        }
    }

    public Vehicle findTarget() {
        for (Vehicle vehicle : world.getVehicles()) {
            if (inRange(vehicle)) {
                return vehicle;
            }
        }
        return null;
    }

    public boolean inRange(Vehicle target) {
        return target.position().subtract(position).lengthSquared() <= RANGE_SQUARED;
    }

    public void startTracking(Vehicle target) {
        steering.pursuitOn(target);
    }

    public void stopTracking() {
        steering.pursuitOff();
    }

    public void fireAt(Vehicle target) {
        Vector toTarget = target.position().subtract(position);
        double relativeHeading = toTarget.normalise().dot(heading);
        double range = toTarget.length();
        if (relativeHeading >= 0.99 && range < 200) {
            shootAt(target);
        }
    }

    private void shootAt(Vehicle target) {
        long timeDiff = System.currentTimeMillis() - lastShot;
        if (lastShot == 0 || timeDiff >= 1000) {
            world.shotFired(this, target);
            lastShot += timeDiff;
        }
    }
}
