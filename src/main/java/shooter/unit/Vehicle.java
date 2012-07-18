package shooter.unit;

import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;

public class Vehicle extends MovingEntity {

    public Vehicle(Vector position, Vector heading, double maxTurnRate, Goal goal, Steering steering) {
        super(goal, steering);
        steering.setOwner(this);
        this.position = position;
        this.heading = heading;
    }

    public double X() {
        return position.x();
    }

    public double Y() {
        return position.y();
    }

    public void wander() {
        steering.wanderOn();
    }

}
