package shooter.ui;

import java.awt.*;

import shooter.geom.Vector;
import shooter.steering.Direction;
import shooter.world.GameWorld;

public class GameMediator {

    private final GameWorld gameWorld;
    private final GameRenderer renderer;
    private boolean showFeelers;
    private boolean showWallNormals;
    private Vector viewPoint = Vector.ZERO;

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

    public void moveTo(Vector position) {
        gameWorld.moveTo(position);
    }

    public void toggleFeelers() {
        renderer.toggleFeelers(); showFeelers = !showFeelers;
    }

    public void toggleWallNormals() {
        renderer.toggleWallNormals();showWallNormals = !showWallNormals;
    }

    public void scrollView(Vector viewPoint) {
        renderer.updateView(viewPoint);
    }

}
