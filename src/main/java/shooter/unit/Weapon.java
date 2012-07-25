package shooter.unit;

public interface Weapon {

    boolean acquireTarget();

    boolean targetAcquired();

    boolean targetInRange();

    MovingEntity getTarget();

    public void fire();

    void startTracking();

    void stopTracking();

}
