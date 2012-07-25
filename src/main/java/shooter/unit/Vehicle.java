package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;

public class Vehicle extends MovingEntity {

    public Vehicle(Vector position, double radius, Vector heading, double maxTurnRate, MessageDispatcher radio,
                   Goal goal, Steering steering) {
        super(position, radius, radio, goal, steering);
        steering.setOwner(this);
        this.heading = heading;
    }

    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
