package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.watchtower.WatchtowerDuty;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.world.ShooterWorld;

public class WatchTower extends MovingEntity {

    private final TargetingSystem targetingSystem;

    public WatchTower(Vector position, double radius, MessageDispatcher radio, ShooterWorld world, Steering steering,
                      Weapon weapon) {
        super(position, radius, radio, new WatchtowerDuty(), steering);
        this.targetingSystem = new TargetingSystem(world, this, weapon);
        steering.setOwner(this);
        heading = new Vector(1, 0);
    }

    @Override
    public void update() {
        brain.process(this);
        Vector steeringForce = steering.calculate();
        steeringForce = restrictTurnRate(steeringForce);
        if (steeringForce.length() > 0.0001) {
            heading = steeringForce.normalise();
            side = heading.perp();
        }
    }

    public void startTracking() {
        steering.pursuitOn(targetingSystem.getTarget());
    }

    public void stopTracking() {
        steering.pursuitOff();
        targetingSystem.stopTracking();
    }

    public void fire() {
        targetingSystem.fire();
    }

    public boolean acquireTarget() {
        return targetingSystem.acquireTarget();
    }

    public boolean targetAcquired() {
        return targetingSystem.getTarget() != null;
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

    public boolean targetInRange() {
        return targetAcquired() && targetingSystem.isInRange();
    }
}
