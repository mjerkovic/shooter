package shooter.unit;

import shooter.geom.Vector;

public class Signpost extends Entity {

    private final String label;

    public Signpost(String label, int x, int y) {
        this.label = label;
        this.position = new Vector(x, y);
    }

    public String getLabel() {
        return label;
    }
    
}
