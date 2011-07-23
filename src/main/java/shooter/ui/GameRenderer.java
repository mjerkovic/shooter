package shooter.ui;

import java.awt.*;

import shooter.geom.Vector;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;
import shooter.unit.WatchTower;

public class GameRenderer {

    private final Graphics2D graphics;

    public GameRenderer(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void render(Vehicle vehicle) {
        int[][] vehicleShape = vehicle.getShape();
        graphics.drawPolygon(vehicleShape[0], vehicleShape[1], 3);
    }

    public void render(Signpost signpost) {
        Vector pos = signpost.getPosition();
        graphics.drawString(signpost.getLabel(), (int)pos.X(), (int)pos.Y());
    }

    public void render(WatchTower watchTower) {
        Vector pos = watchTower.getPosition();
        int x1 = (int) pos.X();
        int y1 = (int) pos.Y();
        graphics.drawOval(x1 - 5, y1 - 5, 10, 10);
        Vector heading = watchTower.heading();
        int x2 = x1 + (int) (heading.X() * 20);
        int y2 = y1 + (int) (heading.Y() * 20);
        graphics.drawLine(x1, y1, x2, y2);
    }
    
}
