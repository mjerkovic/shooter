package shooter.ui;

import static shooter.geom.Geometry.createFeelersFor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import shooter.geom.Rotation;
import shooter.geom.Vector;
import shooter.unit.Bullet;
import shooter.unit.Entity;
import shooter.unit.Health;
import shooter.unit.Miner;
import shooter.unit.Obstacle;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;
import shooter.unit.Wall;
import shooter.unit.WatchTower;
import shooter.unit.Weapon;
import shooter.unit.structure.BaseCamp;
import shooter.unit.structure.Mine;
import shooter.unit.structure.StorageTank;

public class GameRenderer {

    private final Graphics2D graphics;
    private final Vector viewPoint;
    private final boolean showFeelers;
    private final boolean showWallNormals;

    public GameRenderer(Graphics2D graphics, Vector viewPoint, boolean showFeelers, boolean showWallNormals) {
        this.graphics = graphics;
        this.viewPoint = new Vector(viewPoint);
        this.showFeelers = showFeelers;
        this.showWallNormals = showWallNormals;
    }

    public void render(Vehicle vehicle) {
        Vector adjustedPosition = vehicle.position().add(viewPoint);
        int x = (int) adjustedPosition.x();
        int y = (int) adjustedPosition.y();

        Vector tip = new Vector(x, y - vehicle.boundingRadius());
        Vector left = new Vector(x - 5, y + 5);
        Vector right = new Vector(x + 5, y + 5);

        int[] xPos = {(int) tip.x(), (int) left.x(), (int) right.x()};
        int[] yPos = {(int) tip.y(), (int) left.y(), (int) right.y()};

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
                graphics.drawLine(x, y, (int) feeler.x(), (int) feeler.y());
            }
        }
        renderHealthBar(vehicle, x, y);
    }

    public void render(Miner miner) {
        Vector adjustedPosition = miner.position().add(viewPoint);
        int x = (int) adjustedPosition.x();
        int y = (int) adjustedPosition.y();

        AffineTransform orig = graphics.getTransform();
        Vector up = new Vector(0, -1);
        double angle = up.angle(miner.heading());
        Rotation rotation = up.rotationTo(miner.heading());

        AffineTransform rot = AffineTransform.getRotateInstance(rotation.rotate(angle), x, y);
        graphics.transform(rot);
        graphics.drawRect(x - ((int) miner.boundingRadius() / 2), y - ((int) miner.boundingRadius() / 2),
                (int) miner.boundingRadius(), (int) miner.boundingRadius() * 2);
        graphics.setTransform(orig);
        renderHealthBar(miner, x, y);
    }

    private void renderHealthBar(Entity entity, int x, int y) {
        int yPos = y - ((int) entity.boundingRadius() + 10);
        graphics.drawRect(x - 15, yPos, 30, 7);
        Color originalColor = graphics.getColor();
        double health = entity.getHealth();
        graphics.setColor(getColorFor(health));
        double healthBarLength = 29.0 * health;
        graphics.fillRect(x - 14, yPos + 1, (int) healthBarLength, 6);
        graphics.setColor(originalColor);
    }

    private Color getColorFor(double health) {
        switch (Health.getHealthFor(health)) {
            case POOR: return Color.RED;
            case AVERAGE: return Color.YELLOW;
            default: return Color.GREEN;
        }
    }

    public void render(Signpost signpost) {
        Vector pos = signpost.position().add(viewPoint);
        graphics.drawString(signpost.getLabel(), (int)pos.x(), (int)pos.y());
    }

    public void render(WatchTower watchTower) {
        Vector pos = watchTower.position().add(viewPoint);
        int x1 = (int) pos.x();
        int y1 = (int) pos.y();
        graphics.drawOval(x1 - 5, y1 - 5, 10, 10);
/*
        Vector heading = watchTower.heading();
        int x2 = x1 + (int) (heading.x() * 20);
        int y2 = y1 + (int) (heading.y() * 20);
        graphics.drawLine(x1 + (int) (heading.x() * 5), y1 + (int) (heading.y() * 5), x2, y2);
*/
    }

    public void render(Bullet bullet) {
        Vector adjustedPosition = bullet.position().add(viewPoint);
        int x1 = (int) adjustedPosition.x();
        int y1 = (int) adjustedPosition.y();
        Vector tail = bullet.heading().reverse();
        int x2 = x1 + (int) tail.x() * 10;
        int y2 = y1 + (int) tail.y() * 10;
        graphics.drawLine(x1, y1, x2, y2);
    }

    public void render(Obstacle obstacle) {
        Vector adjustedPosition = obstacle.position().add(viewPoint);
        graphics.drawOval((int) (adjustedPosition.x() - obstacle.boundingRadius()),
                (int) (adjustedPosition.y() - obstacle.boundingRadius()),
                (int) (obstacle.boundingRadius() * 2), (int) (obstacle.boundingRadius() * 2));
    }

    public void render(Wall wall) {
        Stroke stroke = graphics.getStroke();
        graphics.setStroke(new BasicStroke(10));
        graphics.drawLine((int) wall.from().x(), (int) wall.from().y(), (int) wall.to().x(), (int) wall.to().y());
        graphics.setStroke(stroke);

        if (showWallNormals) {
            Vector centre = wall.centre();
            Vector normal = wall.normal();
            int x = (int) centre.x();
            int y = (int) centre.y();
            int toX = x + (int) normal.x() * 10;
            int toY = y + (int) normal.y() * 10;
            graphics.drawLine(x, y, toX, toY);
        }
    }

    public void render(Mine mine) {
        Vector pos = mine.position().add(viewPoint);
        int radius = (int) mine.boundingRadius();
        int length = radius * 2;
        graphics.drawRect((int) pos.x() - radius, (int) pos.y() - radius, length, length);
        graphics.drawString(String.valueOf(mine.yield()), (int)pos.x() - 10, (int)pos.y());
    }

    public void render(StorageTank storageTank) {

    }

    public void render(BaseCamp baseCamp) {
        Vector pos = baseCamp.position().add(viewPoint);
        int radius = (int) baseCamp.boundingRadius();
        int length = radius * 2;
        graphics.drawRect((int) pos.x() - radius, (int) pos.y() - radius, length, length);
        graphics.drawString("Base", (int)pos.x() - 10, (int)pos.y());
    }

    public void render(Weapon weapon) {
        Vector pos = weapon.position().add(viewPoint);
        int x = (int) pos.x();
        int y = (int) pos.y();
        double radius = weapon.owner().boundingRadius();
/*
        Vector heading = watchTower.heading();
        int x2 = x1 + (int) (heading.x() * 20);
        int y2 = y1 + (int) (heading.y() * 20);
        graphics.drawLine(x1 + (int) (heading.x() * 5), y1 + (int) (heading.y() * 5), x2, y2);
*/
        graphics.drawLine(
                x + (int) (weapon.heading().x() * radius),
                y + (int) (weapon.heading().y() * radius),
                x + (int) (weapon.heading().x() * 20),
                y + (int) (weapon.heading().y() * 20));
    }
}
