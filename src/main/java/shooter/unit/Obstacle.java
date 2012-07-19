package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public class Obstacle extends Entity {

    public Obstacle(int x, int y, double boundingRadius) {
        this.position = new Vector(x, y);
        this.boundingRadius = boundingRadius;
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
