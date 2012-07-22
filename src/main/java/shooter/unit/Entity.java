package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public abstract class Entity {

    protected final double boundingRadius;
    protected final MessageDispatcher radio;
    protected Vector position = new Vector(2, 2);
    protected int health = 100;

    protected Entity(Vector position, double boundingRadius, MessageDispatcher radio) {
        this.position = position;
        this.boundingRadius = boundingRadius;
        this.radio = radio;
    }

    public Vector position() {
        return position;
    }

    public double x() {
        return position.x();
    }

    public double y() {
        return position.y();
    }

    public double boundingRadius() {
        return boundingRadius;
    }

    public int getHealth() {
        return health;
    }

    public boolean intersects(Entity other) {
        return other.position.subtract(position).length() < (boundingRadius + other.boundingRadius);
    }

    public abstract void renderWith(GameRenderer renderer);

}
