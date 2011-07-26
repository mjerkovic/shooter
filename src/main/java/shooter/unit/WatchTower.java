package shooter.unit;

import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;
import shooter.world.ShooterWorld;

public class WatchTower extends MovingEntity {

    private static final int RANGE_SQUARED = 40000;

    private final ShooterWorld world;
    private Vehicle target;

    public WatchTower(int x, int y, ShooterWorld world, Goal goal, Steering steering) {
        super(goal, steering);
        this.world = world;
        steering.setOwner(this);
        position = new Vector(x, y);
    }

    @Override
    public void update() {
        goal.process(this);
        Vector steeringForce = steering.calculate();
        heading = steeringForce.normalise();
        side = heading.perp();
    }

    public boolean findTarget() {
        for (Vehicle vehicle : world.getVehicles()) {
            if (inRange(vehicle)) {
                target = vehicle;
                return true;
            }
        }
        return false;
    }

    public boolean targetInRange(Vehicle target) {
        return target!= null && inRange(target);
    }

    private boolean inRange(Vehicle target) {
        return target.position().subtract(position).lengthSquared() <= RANGE_SQUARED;
    }

}
