package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.goals.Goal;
import shooter.ui.Renderer;

public class Vehicle extends MovingEntity {

    public Vehicle(Orientation orientation, MessageDispatcher radio, Goal brain, Movement movement) {
        super(orientation, radio, brain, movement);
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
