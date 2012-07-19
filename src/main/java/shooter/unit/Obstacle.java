package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public class Obstacle extends Entity {

    public Obstacle(Vector position, double boundingRadius) {
        super(position, boundingRadius);
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
