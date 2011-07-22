package shooter.ui;

import java.awt.*;
import java.util.Collection;

import shooter.unit.Vehicle;
import shooter.world.GameWorld;
import shooter.world.Renderable;

public class GameRenderer {

    private final Graphics2D graphics;

    public GameRenderer(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void render(Vehicle vehicle) {
        int[][] vehicleShape = vehicle.getShape();
        graphics.drawPolygon(vehicleShape[0], vehicleShape[1], 3);
    }

}
