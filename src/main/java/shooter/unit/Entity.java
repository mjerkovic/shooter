package shooter.unit;

import shooter.geom.Vector;

public abstract class Entity {

    protected Vector position = new Vector(2, 2);

    public Vector position() {
        return position;
    }
}
