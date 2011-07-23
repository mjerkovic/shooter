package shooter.ui;

import java.awt.*;

import shooter.geom.Vector;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;

public class GameRenderer {

    private final Graphics2D graphics;

    public GameRenderer(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void render(Vehicle vehicle, int x, int y) {
        int[][] vehicleShape = vehicle.getShape();
        graphics.drawPolygon(vehicleShape[0], vehicleShape[1], 3);
    }

    public void render(Signpost signpost, int x, int y) {
        Vector pos = signpost.getPosition();
        graphics.drawString(signpost.getLabel(), (int)pos.X(), (int)pos.Y());
    }

}
