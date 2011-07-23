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
        System.out.print("pos = "+pos+"\t");
        graphics.drawOval((int) pos.X() - 5, (int) pos.Y() - 5, 10, 10);
        Vector heading = watchTower.getHeading();
        System.out.println("heading = " +heading);
        int x1 = (int) pos.X();
        int y1 = (int) pos.Y();
        int x2 = (int) pos.X() + (int) (heading.X() * 20);
        int y2 = (int) pos.Y() + (int) (heading.Y() * 20);
        System.out.println("line = " + x1 + ", " + y1 + ", " + x2 + ", " + y2);
        graphics.drawLine(x1, y1, x2, y2);
    }
    
}
