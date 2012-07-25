package shooter.unit;

public interface Weapon {

    boolean inRange(double rangeSquared);

    double rateOfFire(int milliseconds);

    boolean fire();

}
