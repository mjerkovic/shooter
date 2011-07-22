package shooter.unit;

import shooter.geom.Vector;

public abstract class Entity {
    public Vector position = new Vector(2, 2);

    public Vector getPosition() {
        return position;
    }
}
