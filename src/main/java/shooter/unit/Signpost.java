package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public class Signpost extends Entity {

    private final String label;

    public Signpost(Vector position, double boundingRadius, String label) {
        super(position, boundingRadius);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }
}
