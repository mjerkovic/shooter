package shooter.unit;

import java.awt.Rectangle;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public abstract class Entity {

    protected Vector position = new Vector(2, 2);
    protected double boundingRadius = 10;
    protected int health = 100;
    private Rectangle location;

    protected Entity(Vector position, double boundingRadius) {
        this.position = position;
        this.boundingRadius = boundingRadius;
        updateLocation();
    }

    public Vector position() {
        return position;
    }

    public double boundingRadius() {
        return boundingRadius;
    }

    public int getHealth() {
        return health;
    }

    protected void updateLocation() {
        this.location = new Rectangle((int) position.x(), (int) position.y(), (int) boundingRadius, (int) boundingRadius);
    }

    public abstract void renderWith(GameRenderer renderer);

}
