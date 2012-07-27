package shooter.unit;

import shooter.ui.Renderer;

public class WatchTower extends Entity {

    public WatchTower(Orientation orientation) {
        super(orientation);
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
