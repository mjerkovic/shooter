package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;
import shooter.ui.Renderer;
import shooter.world.ShooterWorld;

public class Gun<T extends Entity & Armed> extends MovingEntity implements Weapon {

    private static final int RANGE_SQUARED = 40000;

    private Entity owner;
    private final double maxTurnRate;
    private final int rangeSquared;
    private final int rateOfFireInMilliseconds;
    private ShooterWorld world;
    private final TargetingSystem targetingSystem;
    private long lastShot;

    public Gun(T owner, double maxTurnRate, MessageDispatcher radio, Goal brain, int rangeSquared,
               int rateOfFireInMilliseconds, ShooterWorld world, Steering steering) {
        super(owner.position(), 0, radio, brain, steering);

        steering.setOwner(this);
        owner.armWith(this);
        this.owner = owner;
        heading = owner.heading();
        this.maxTurnRate = maxTurnRate;
        this.rangeSquared = rangeSquared;
        this.rateOfFireInMilliseconds = rateOfFireInMilliseconds;
        this.world = world;
        targetingSystem = new TargetingSystem();
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

    public boolean acquireTarget() {
        return targetingSystem.acquireTarget(world.getVehicles(), owner.position(), rangeSquared);
    }

    public boolean targetInRange() {
        return targetingSystem.isInRange(owner.position(), rangeSquared);
    }

    public void fire() {
        long timeDiff = System.currentTimeMillis() - lastShot;
        if (lastShot == 0 || timeDiff >= rateOfFireInMilliseconds &&
                targetingSystem.canFire(owner.position())) {
            lastShot += timeDiff;
            world.shotFired(owner, targetingSystem.getTarget(), heading());
        }
    }

    public MovingEntity getTarget() {
        return targetingSystem.getTarget();
    }

    @Override
    public Vector position() {
        return owner.position();
    }

    public Entity owner() {
        return owner;
    }

    //---------------------------------------------------

    public void startTracking() {
        steering.pursuitOn(targetingSystem.getTarget());
    }

    public void stopTracking() {
        steering.pursuitOff();
        targetingSystem.stopTracking();
    }

    public boolean targetAcquired() {
        return targetingSystem.hasTarget();
    }

}