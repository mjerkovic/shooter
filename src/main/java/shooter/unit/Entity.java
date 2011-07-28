package shooter.unit;

import shooter.geom.Vector;

public abstract class Entity {

    protected Vector position = new Vector(2, 2);
    protected double boundingRadius;

    public Vector position() {
        return position;
    }

    public double boundingRadius() {
        return boundingRadius;
    }

}
