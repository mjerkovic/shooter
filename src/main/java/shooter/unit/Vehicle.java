package shooter.unit;

import shooter.goals.Goal;
import shooter.ui.Renderer;

public class Vehicle extends MovingEntity {

    public Vehicle(Orientation orientation, Goal brain, Movement movement) {
        super(orientation, brain, movement);
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
