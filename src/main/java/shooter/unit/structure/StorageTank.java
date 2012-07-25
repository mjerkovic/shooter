package shooter.unit.structure;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.ui.Renderer;
import shooter.unit.Entity;

public class StorageTank extends Entity {

    public StorageTank(Vector position, double radius, MessageDispatcher radio) {
        super(position, radius, radio);
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
