package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public class Signpost extends Entity {

    private final String label;

    public Signpost(String label, int x, int y) {
        this.label = label;
        this.position = new Vector(x, y);
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }
}
