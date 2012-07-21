package shooter.unit.structure;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;
import shooter.unit.Entity;

public class StorageTank extends Entity {

    public StorageTank(Vector position, double radius) {
        super(position, radius);
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
