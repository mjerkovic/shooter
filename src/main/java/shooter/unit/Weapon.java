package shooter.unit;

public interface Weapon<T extends Entity & Armed> extends Unit {

    boolean acquireTarget();

    boolean targetAcquired();

    boolean targetInRange();

    MovingEntity getTarget();

    public void fire();

    void startTracking();

    void stopTracking();

    Entity owner();

}
