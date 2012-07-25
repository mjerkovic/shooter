package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.ui.Renderer;

public class Obstacle extends Entity {

    public Obstacle(Vector position, MessageDispatcher radio, double boundingRadius) {
        super(position, boundingRadius, radio);
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
