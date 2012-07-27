package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.ui.Renderer;

public class WatchTower extends Entity {

    public WatchTower(Orientation orientation, MessageDispatcher radio) {
        super(orientation, radio);
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
