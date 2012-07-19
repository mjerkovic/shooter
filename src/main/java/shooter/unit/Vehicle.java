package shooter.unit;

import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;

public class Vehicle extends MovingEntity {

    public Vehicle(Army army, Vector position, Vector heading, double maxTurnRate, Goal goal, Steering steering) {
        super(army, goal, steering);
        army.add(this);
        steering.setOwner(this);
        this.position = position;
        this.heading = heading;
    }

    public double x() {
        return position.x();
    }

    public double y() {
        return position.y();
    }

    public void wander() {
        steering.wanderOn();
    }

}
