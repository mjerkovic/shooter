package shooter.unit;

import shooter.geom.Vector;
import shooter.steering.Steering;

public abstract class MovingEntity extends Entity {

    protected final Steering steering;
    public Vector velocity = new Vector(0, 0);
    protected Vector heading = new Vector(0, 1);
    protected Vector side = new Vector(-1, 0);
    double mass = 1;
    double maxSpeed = 3;
    //double maxForce = 2;
    //double maxTurnRate = 1;

    public MovingEntity(Steering steering) {
        this.steering = steering;
    }

    public void update() {
        Vector steeringForce = steering.calculate();
        System.out.print("steeringForce: " + steeringForce + "\t");
        Vector acceleration = steeringForce.dividedBy(mass);//.dividedBy(timeDiff);
        //System.out.print("acceleration: " + acceleration + "\t");
        velocity = velocity.add(acceleration).truncate(maxSpeed);
        //System.out.print("velocity: " + velocity + "\t");
        position = position.add(velocity);
        //System.out.println("position: " + position);

        if (velocity.lengthSquared() > 0.00000001) {
            heading = velocity.normalise();
            side = heading.perp();
        }
    }

    public void stop() {
        velocity = Vector.ZERO;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getHeading() {
        return heading;
    }

    public Vector getSide() {
        return side;
    }

    public void move(Vector toPosition) {
        //steeringBehaviours.move(this, toPosition);
    }

}
