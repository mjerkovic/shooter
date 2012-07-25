package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public interface Unit {

    public Vector position();

    public Vector heading();    

    void update();

    void renderWith(GameRenderer renderer);

}
