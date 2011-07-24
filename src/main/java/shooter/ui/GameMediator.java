package shooter.ui;

import shooter.geom.Vector;
import shooter.steering.Direction;
import shooter.world.GameWorld;

public class GameMediator {

    private final GameWorld gameWorld;

    public GameMediator(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void update() {
        gameWorld.update();
    }

    public void renderUsing(GameRenderer renderer) {
        gameWorld.renderWith(renderer);
    }

    public void clickRecordedAt(double x, double y) {
        gameWorld.moveVehicleTo(new Vector(x, y));
    }

    public void moveVehicle(Direction direction) {
        gameWorld.moveVehicle(direction);
    }

}
