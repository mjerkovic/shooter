package shooter.unit;

import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;

public class Vehicle extends MovingEntity {

    public Vehicle(Vector position, double radius, Vector heading, double maxTurnRate, Goal goal, Steering steering) {
        super(position, radius, goal, steering);
        steering.setOwner(this);
        this.heading = heading;
    }

    public void wander() {
        steering.wanderOn();
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
