package shooter.unit.structure;

import shooter.comms.MessageDispatcher;
import shooter.ui.Renderer;
import shooter.unit.Entity;
import shooter.unit.Orientation;

public class StorageTank extends Entity {

    public StorageTank(Orientation orientation, MessageDispatcher radio) {
        super(orientation, radio);
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
