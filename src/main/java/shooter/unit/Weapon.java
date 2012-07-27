package shooter.unit;

public interface Weapon {

    boolean inRange(double rangeSquared);

    boolean fire();

}
