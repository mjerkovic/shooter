package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public class Signpost extends Entity {

    private final String label;

    public Signpost(Vector position, double boundingRadius, MessageDispatcher radio, String label) {
        super(position, boundingRadius, radio);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }
}
