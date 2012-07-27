package shooter.unit;

import shooter.ui.Renderer;

public class Obstacle extends Entity {

    public Obstacle(Orientation orientation) {
        super(orientation);
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
