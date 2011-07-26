package shooter.ui;

import java.awt.*;
import java.awt.geom.AffineTransform;

import shooter.geom.Rotation;
import shooter.geom.Vector;
import shooter.unit.Bullet;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;
import shooter.unit.WatchTower;

public class GameRenderer {

    private final Graphics2D graphics;

    public GameRenderer(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void render(Vehicle vehicle) {
        int x = (int) vehicle.X();
        int y = (int) vehicle.Y();

        Vector tip = new Vector(x, y -10);
        Vector left = new Vector(x -5, y+5);
        Vector right = new Vector(x +5, y+5);

        int[] xPos = { (int) tip.X(), (int) left.X(), (int) right.X() };
        int[] yPos = { (int) tip.Y(), (int) left.Y(), (int) right.Y() };

        AffineTransform orig = graphics.getTransform();
        Vector up = new Vector(0, -1);
        double angle = up.angle(vehicle.heading());
        Rotation rotation = up.rotationTo(vehicle.heading());

        AffineTransform rot = AffineTransform.getRotateInstance(rotation.rotate(angle), x, y);
        graphics.transform(rot);
        graphics.drawPolygon(xPos, yPos, 3);
        graphics.setTransform(orig);
    }

    public void render(Signpost signpost) {
        Vector pos = signpost.position();
        graphics.drawString(signpost.getLabel(), (int)pos.X(), (int)pos.Y());
    }

    public void render(WatchTower watchTower) {
        Vector pos = watchTower.position();
        int x1 = (int) pos.X();
        int y1 = (int) pos.Y();
        graphics.drawOval(x1 - 5, y1 - 5, 10, 10);
        Vector heading = watchTower.heading();
        int x2 = x1 + (int) (heading.X() * 20);
        int y2 = y1 + (int) (heading.Y() * 20);
        graphics.drawLine(x1, y1, x2, y2);
    }

    public void render(Bullet bullet) {
        int x1 = (int) bullet.position().X();
        int y1 = (int) bullet.position().Y();
        Vector tail = bullet.heading().reverse();
        int x2 = x1 + (int) tail.X() * 10;
        int y2 = y1 + (int) tail.Y() * 10;
        graphics.drawLine(x1, y1, x2, y2);
    }
    
}
