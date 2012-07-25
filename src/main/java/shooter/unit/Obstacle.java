package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public class Obstacle extends Entity {

    public Obstacle(Vector position, MessageDispatcher radio, double boundingRadius) {
        super(position, boundingRadius, radio);
    }

    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
