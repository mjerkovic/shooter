package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.ui.Renderer;

public class Signpost extends Entity {

    private final String label;

    public Signpost(Orientation orientation, MessageDispatcher radio, String label) {
        super(orientation, radio);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }
}
