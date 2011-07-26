package shooter.world;

import shooter.steering.Direction;
import shooter.ui.GameRenderer;

public interface GameWorld {

    void update();

    void renderWith(GameRenderer renderer);

    void moveVehicle(Direction direction);
    
}
