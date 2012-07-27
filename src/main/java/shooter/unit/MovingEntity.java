package shooter.unit;

import org.apache.commons.math3.util.FastMath;
import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;

public abstract class MovingEntity extends Entity {

    protected final Steering steering;
    protected double maxSpeed = 3.5;
    protected double maxTurnRate = 0.2;
    protected final double minConsumption;
    protected final double maxConsumption;
    //protected double maxForce = 2;

    public MovingEntity(Orientation orientation, Goal brain, Movement movement) {
        super(orientation, brain);
        this.steering = movement.getSteering();
        this.steering.setOwner(this);
        this.maxSpeed = movement.getMaxSpeed();
        this.maxTurnRate = movement.getMaxTurnRate();
        //this.maxForce = movement.getMaxForce();
        this.minConsumption = movement.getMinConsumption();
        this.maxConsumption = movement.getMaxConsumption();
    }

    public void update() {
        brain.process(this);
        calculateSteering();
        energy = energy - energyConsumption();
    }

    private double energyConsumption() {
        return FastMath.min(minConsumption + (velocity.length() / maxSpeed), maxConsumption);
    }

    protected void calculateSteering() {
        Vector steeringForce = steering.calculate();
        steeringForce = restrictTurnRate(steeringForce);
        Vector acceleration = steeringForce.dividedBy(mass);//.dividedBy(timeDiff);
        velocity = velocity.add(acceleration).truncate(maxSpeed);
        position = position.add(velocity);

        if (velocity.lengthSquared() > 0.00000001) {
            heading = velocity.normalise();
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

    public Steering steering() {
        return steering;
    }

    public double maxTurnRate() {
        return maxTurnRate;
    }

}
