package shooter.world;

import java.util.Collection;

import shooter.geom.Vector;
import shooter.steering.Direction;
import shooter.ui.Renderer;
import shooter.unit.Obstacle;
import shooter.unit.Vehicle;
import shooter.unit.Wall;

public interface GameWorld {

    void update();

    void renderWith(Renderer renderer);

    void moveVehicle(Direction direction);

    void moveTo(Vector position);

    Collection<Obstacle> getObstacles();

    Collection<Wall> getWalls();

    Collection<Vehicle> getVehicles();

}
