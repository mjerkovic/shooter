package shooter.unit;

import shooter.geom.Vector;

public abstract class Entity {

    protected Vector position = new Vector(2, 2);
    protected double boundingRadius;
    protected int health = 100;

    public Vector position() {
        return position;
    }

    public double boundingRadius() {
        return boundingRadius;
    }

    public int getHealth() {
        return health;
    }

}
