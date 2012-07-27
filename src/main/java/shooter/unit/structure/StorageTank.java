package shooter.unit.structure;

import shooter.ui.Renderer;
import shooter.unit.Entity;
import shooter.unit.Orientation;

public class StorageTank extends Entity {

    public StorageTank(Orientation orientation) {
        super(orientation);
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
