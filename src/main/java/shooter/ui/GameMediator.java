package shooter.ui;

import java.awt.Graphics2D;

import shooter.geom.Vector;
import shooter.steering.Direction;
import shooter.world.GameWorld;

public class GameMediator {

    private final GameWorld gameWorld;
    private final GameRenderer renderer;

    public GameMediator(GameWorld gameWorld, GameRenderer renderer) {
        this.gameWorld = gameWorld;
        this.renderer = renderer;
    }

    public void update() {
        gameWorld.update();
    }

    public void renderUsing(Graphics2D graphics) {
        renderer.renderWith(graphics);
        //GameRenderer renderer = new GameRenderer(graphics, viewPoint, showFeelers, showWallNormals);
        //gameWorld.renderWith(renderer);
    }

    public void moveVehicle(Direction direction) {
        gameWorld.moveVehicle(direction);
    }

    public void toggleFeelers() {
        renderer.toggleFeelers();
    }

    public void toggleWallNormals() {
        renderer.toggleWallNormals();
    }

    public void scrollView(Vector viewPoint) {
        renderer.updateView(viewPoint);
    }

}
