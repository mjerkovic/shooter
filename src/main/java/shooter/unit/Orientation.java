package shooter.unit;

import shooter.geom.Vector;

public class Orientation {

    private final Vector position;
    private final Vector heading;
    private final double radius;
    private final double mass;

    public Orientation(Vector position, Vector heading, double radius, double mass) {
        this.position = position;
        this.heading = heading;
        this.radius = radius;
        this.mass = mass;
    }

    public Orientation(Vector position, Vector heading, double radius) {
        this(position, heading, radius, 1);
    }

    public Orientation(Vector position, double radius) {
        this(position, new Vector(0, 1), radius, 1);
    }

    public Orientation(Vector position, Vector heading) {
        this(position, heading, 10, 1);
    }

    public Orientation(Vector position) {
        this(position, new Vector(0, 1), 10, 1);
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getHeading() {
        return heading;
    }

    public double getRadius() {
        return radius;
    }

    public double getMass() {
        return mass;
    }

}
