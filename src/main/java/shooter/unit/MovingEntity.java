package shooter.unit;

import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;

public abstract class MovingEntity extends Entity {

    protected final Goal goal;
    protected final Steering steering;
    protected Vector velocity = new Vector(0, 0);
    protected Vector heading = new Vector(1, 0);
    protected Vector side = new Vector(1, 0);
    double mass = 1;
    double maxSpeed = 3;
    //double maxForce = 2;
    double maxTurnRate = 0.2;

    public MovingEntity(Goal goal, Steering steering) {
        this.goal = goal;
        this.steering = steering;
        this.boundingRadius = 10;
    }

    public void update() {
        goal.process(this);
        Vector steeringForce = steering.calculate();
        steeringForce = restrictTurnRate(steeringForce);
        Vector acceleration = steeringForce.dividedBy(mass);//.dividedBy(timeDiff);
        velocity = velocity.add(acceleration).truncate(maxSpeed);
        position = position.add(velocity);

        if (velocity.lengthSquared() > 0.00000001) {
            heading = velocity.normalise();
            side = heading.perp();
        }
    }

    protected final Vector restrictTurnRate(Vector steeringForce) {
        double angleChange = heading.angle(steeringForce.normalise());
        return angleChange > maxTurnRate ? steeringForce.scale(maxTurnRate) : steeringForce;
    }

    public void stop() {
        velocity = Vector.ZERO;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public Vector velocity() {
        return velocity;
    }

    public Vector heading() {
        return heading;
    }

    public Vector side() {
        return side;
    }

    public Steering steering() {
        return steering;
    }

    public void move(Vector toPosition) {
        //steeringBehaviours.move(this, toPosition);
    }

    public double getMaxTurnRate() {
        return maxTurnRate;
    }
}
