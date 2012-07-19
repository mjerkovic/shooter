package shooter.unit;

import shooter.geom.Vector;
import shooter.goals.Scan;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.world.ShooterWorld;

public class WatchTower extends MovingEntity {

    private static final int RANGE_SQUARED = 40000;

    private final ShooterWorld world;
    private TargetingSystem targetingSystem;

    public WatchTower(Vector position, double radius, Army army, ShooterWorld world, Steering steering) {
        super(position, radius, army, new Scan(), steering);
        this.world = world;
        this.targetingSystem = new TargetingSystem(world, this);
        steering.setOwner(this);
        heading = new Vector(1, 0);
    }

    @Override
    public void update() {
        goal.process(this);
        Vector steeringForce = steering.calculate();
        steeringForce = restrictTurnRate(steeringForce);
        if (steeringForce.length() > 0.0001) {
            heading = steeringForce.normalise();
            side = heading.perp();
        }
    }

    public boolean inRange(Vehicle target) {
        return target.position().subtract(position).lengthSquared() <= RANGE_SQUARED;
    }

    public void startTracking() {
        steering.pursuitOn(targetingSystem.getTarget());
    }

    public void stopTracking() {
        steering.pursuitOff();
    }

    public void fire() {
        targetingSystem.fire();
    }

    public boolean acquireTarget() {
        return targetingSystem.acquireTarget(RANGE_SQUARED);
    }

    public boolean targetAcquired() {
        return targetingSystem.getTarget() != null;
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
