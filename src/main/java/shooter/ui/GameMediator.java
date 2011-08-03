package shooter.ui;

import java.awt.*;

import shooter.steering.Direction;
import shooter.world.GameWorld;

public class GameMediator {

    private final GameWorld gameWorld;
    private boolean showFeelers;
    private boolean showWallNormals;

    public GameMediator(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void update() {
        gameWorld.update();
    }

    public void renderUsing(Graphics2D graphics) {
        GameRenderer renderer = new GameRenderer(graphics, showFeelers, showWallNormals);
        gameWorld.renderWith(renderer);
    }

    public void moveVehicle(Direction direction) {
        gameWorld.moveVehicle(direction);
    }

    public void toggleFeelers() {
        showFeelers = !showFeelers;
    }

    public void toggleWallNormals() {
        showWallNormals = !showWallNormals;
    }
}
