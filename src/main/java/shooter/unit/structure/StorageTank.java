package shooter.unit.structure;

import shooter.ui.GameRenderer;
import shooter.unit.Army;
import shooter.unit.Unit;

public class StorageTank extends Unit {

    public StorageTank(Army army) {
        super(army);
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
