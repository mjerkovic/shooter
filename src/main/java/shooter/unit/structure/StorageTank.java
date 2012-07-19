package shooter.unit.structure;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;
import shooter.unit.Army;
import shooter.unit.Unit;

public class StorageTank extends Unit {

    public StorageTank(Vector position, double radius, Army army) {
        super(position, radius, army);
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
