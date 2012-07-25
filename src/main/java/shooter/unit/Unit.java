package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.Renderer;

public interface Unit {

    public Vector position();

    public Vector heading();    

    void update();

    void renderWith(Renderer renderer);

}
