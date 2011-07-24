package shooter.world;

import shooter.geom.Vector;
import shooter.steering.Direction;
import shooter.ui.GameRenderer;

public interface GameWorld {

    void update();

    void moveVehicleTo(Vector vector);

    void renderWith(GameRenderer renderer);

    void moveVehicle(Direction direction);
}
