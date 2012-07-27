package shooter.unit;

import shooter.steering.Steering;

public class Movement {

    private final Steering steering;
    private final double maxTurnRate; // = 0.2;
    private final double maxSpeed; // = 3.5;
    private final double maxForce; // = 2;
    private final double minConsumption = 0.0001;
    private final double maxConsumption = 0.0005;

    public Movement(Steering steering, double maxTurnRate, double maxSpeed, double maxForce) {
        this.steering = steering;
        this.maxTurnRate = maxTurnRate;
        this.maxSpeed = maxSpeed;
        this.maxForce = maxForce;
    }

    public Movement(Steering steering, double maxTurnRate, double maxSpeed) {
        this(steering, maxTurnRate, maxSpeed, 2);
    }

    public Movement(Steering steering, double maxTurnRate) {
        this(steering, maxTurnRate, 3.5, 2);
    }

    public Movement(Steering steering) {
        this(steering, 0.2, 3.5, 2);
    }

    public Steering getSteering() {
        return steering;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMaxTurnRate() {
        return maxTurnRate;
    }

    public double getMaxForce() {
        return maxForce;
    }

    public double getMinConsumption() {
        return minConsumption;
    }

    public double getMaxConsumption() {
        return maxConsumption;
    }
}
