package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.DoNothing;
import shooter.goals.Goal;

public abstract class Entity implements Unit {

    protected Vector position = new Vector(2, 2);
    protected Vector heading = new Vector(1, 0);
    protected final double boundingRadius;
    protected double health = 1.0;
    protected final Goal brain;
    protected final MessageDispatcher radio;

    protected Entity(Vector position, double boundingRadius, MessageDispatcher radio, Goal brain) {
        this.position = position;
        this.boundingRadius = boundingRadius;
        this.radio = radio;
        this.brain = brain;
    }

    protected Entity(Vector position, double boundingRadius, MessageDispatcher radio) {
        this(position, boundingRadius, radio, new DoNothing());
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

    public boolean intersects(Entity other) {
        return other.position.subtract(position).length() < (boundingRadius + other.boundingRadius);
    }

    public void update() {
        brain.process(this);
    }

}
