package shooter.world;

import java.awt.*;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;
import shooter.unit.Vehicle;

public interface GameWorld {

    void update(double timeDiff);

    void moveVehicleTo(Vector vector);

    void renderVehiclesWith(GameRenderer renderer);
}
