package shooter.unit;

import shooter.geom.Vector;
import shooter.goals.DoNothing;
import shooter.goals.Goal;

public abstract class Entity implements Unit {

    protected Vector position = new Vector(2, 2);
    protected Vector heading = new Vector(1, 0);
    protected Vector velocity = new Vector(0, 0);
    protected final double boundingRadius;
    protected final double mass;
    protected final Goal brain;
    protected double health = 1.0;

    protected Entity(Orientation orientation, Goal brain) {
        this.position = orientation.getPosition();
        this.heading = orientation.getHeading();
        this.boundingRadius = orientation.getRadius();
        this.mass = orientation.getMass();
        this.brain = brain;
    }

    protected Entity(Orientation orientation) {
        this(orientation, new DoNothing());
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

    public Vector heading() {
        return heading;
    }

    public double boundingRadius() {
        return boundingRadius;
    }

    public double getHealth() {
        return health;
    }

    public Vector velocity() {
        return velocity;
    }

    public Vector side() {
        return heading().side();
    }

    public boolean intersects(Entity other) {
        return other.position.subtract(position).length() < (boundingRadius + other.boundingRadius);
    }

    public void update() {
        brain.process(this);
    }

}
