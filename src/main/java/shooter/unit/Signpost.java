package shooter.unit;

import shooter.ui.Renderer;

public class Signpost extends Entity {

    private final String label;

    public Signpost(Orientation orientation, String label) {
        super(orientation);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }
}
