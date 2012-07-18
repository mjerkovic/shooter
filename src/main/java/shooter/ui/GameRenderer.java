package shooter.ui;

import static shooter.geom.Geometry.createFeelersFor;

import java.awt.*;
import java.awt.geom.AffineTransform;

import shooter.geom.Rotation;
import shooter.geom.Vector;
import shooter.unit.Bullet;
import shooter.unit.Health;
import shooter.unit.Obstacle;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;
import shooter.unit.Wall;
import shooter.unit.WatchTower;

public class GameRenderer {

    private final Graphics2D graphics;
    private final Vector viewPoint;
    private final boolean showFeelers;
    private final boolean showWallNormals;

    public GameRenderer(Graphics2D graphics, Vector viewPoint, boolean showFeelers, boolean showWallNormals) {
        this.graphics = graphics;
        this.viewPoint = viewPoint;
        this.showFeelers = showFeelers;
        this.showWallNormals = showWallNormals;
    }

    public void render(Vehicle vehicle) {
        Vector adjustedPosition = vehicle.position().add(viewPoint);
        int x = (int) adjustedPosition.X();
        int y = (int) adjustedPosition.Y();

        Vector tip = new Vector(x, y - 10);
        Vector left = new Vector(x - 5, y + 5);
        Vector right = new Vector(x + 5, y + 5);

        int[] xPos = {(int) tip.X(), (int) left.X(), (int) right.X()};
        int[] yPos = {(int) tip.Y(), (int) left.Y(), (int) right.Y()};

        AffineTransform orig = graphics.getTransform();
        Vector up = new Vector(0, -1);
        double angle = up.angle(vehicle.heading());
        Rotation rotation = up.rotationTo(vehicle.heading());

        AffineTransform rot = AffineTransform.getRotateInstance(rotation.rotate(angle), x, y);
        graphics.transform(rot);
        graphics.drawPolygon(xPos, yPos, 3);
        graphics.setTransform(orig);

        if (showFeelers) {
            Vector[] feelers = createFeelersFor(vehicle);
            for (Vector feeler : feelers) {
                graphics.drawLine(x, y, (int) feeler.X(), (int) feeler.Y());
            }
        }
        renderHealthBar(x, y, vehicle.getHealth());
    }

    private void renderHealthBar(int x, int y, int health) {
        graphics.drawRect(x - 15, y - 20, 30, 7);
        Color originalColor = graphics.getColor();
        graphics.setColor(getColorFor(health));
        double healthBarLength = 29.0 * ((double) health / 100.0);
        graphics.fillRect(x - 14, y - 19, (int) healthBarLength, 6);
        graphics.setColor(originalColor);
    }

    private Color getColorFor(int health) {
        switch (Health.getHealthFor(health)) {
            case POOR: return Color.RED;
            case AVERAGE: return Color.YELLOW;
            default: return Color.GREEN;
        }
    }

    public void render(Signpost signpost) {
        Vector pos = signpost.position().add(viewPoint);
        graphics.drawString(signpost.getLabel(), (int)pos.X(), (int)pos.Y());
    }

    public void render(WatchTower watchTower) {
        Vector pos = watchTower.position().add(viewPoint);
        int x1 = (int) pos.X();
        int y1 = (int) pos.Y();
        graphics.drawOval(x1 - 5, y1 - 5, 10, 10);
        Vector heading = watchTower.heading();
        int x2 = x1 + (int) (heading.X() * 20);
        int y2 = y1 + (int) (heading.Y() * 20);
        graphics.drawLine(x1, y1, x2, y2);
    }

    public void render(Bullet bullet) {
        Vector adjustedPosition = bullet.position().add(viewPoint);
        int x1 = (int) adjustedPosition.X();
        int y1 = (int) adjustedPosition.Y();
        Vector tail = bullet.heading().reverse();
        int x2 = x1 + (int) tail.X() * 10;
        int y2 = y1 + (int) tail.Y() * 10;
        graphics.drawLine(x1, y1, x2, y2);
    }

    public void render(Obstacle obstacle) {
        Vector adjustedPosition = obstacle.position().add(viewPoint);
        graphics.drawOval((int) (adjustedPosition.X() - obstacle.boundingRadius()),
                (int) (adjustedPosition.Y() - obstacle.boundingRadius()),
                (int) (obstacle.boundingRadius() * 2), (int) (obstacle.boundingRadius() * 2));
    }

    public void render(Wall wall) {
        Stroke stroke = graphics.getStroke();
        graphics.setStroke(new BasicStroke(10));
        graphics.drawLine((int) wall.from().X(), (int) wall.from().Y(), (int) wall.to().X(), (int) wall.to().Y());
        graphics.setStroke(stroke);

        if (showWallNormals) {
            Vector centre = wall.centre();
            Vector normal = wall.normal();
            int x = (int) centre.X();
            int y = (int) centre.Y();
            int toX = x + (int) normal.X() * 10;
            int toY = y + (int) normal.Y() * 10;
            graphics.drawLine(x, y, toX, toY);
        }
    }
    
}
