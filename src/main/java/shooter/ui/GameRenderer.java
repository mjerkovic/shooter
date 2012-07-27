package shooter.ui;

import static shooter.geom.Geometry.createFeelersFor;

import java.awt.*;
import java.awt.geom.AffineTransform;

import shooter.geom.Rotation;
import shooter.geom.Vector;
import shooter.unit.Bullet;
import shooter.unit.Entity;
import shooter.unit.Health;
import shooter.unit.Miner;
import shooter.unit.Obstacle;
import shooter.unit.Signpost;
import shooter.unit.TargetingSystem;
import shooter.unit.Vehicle;
import shooter.unit.Wall;
import shooter.unit.WatchTower;
import shooter.unit.structure.BaseCamp;
import shooter.unit.structure.Mine;
import shooter.unit.structure.StorageTank;
import shooter.world.GameWorld;

public class GameRenderer implements Renderer {

    private final GameWorld world;
    private Vector viewPoint = Vector.ZERO;
    private boolean showFeelers;
    private boolean showWallNormals;
    private Graphics2D graphics;

    public GameRenderer(GameWorld world) {
        this.world = world;
    }

    public void renderWith(Graphics2D graphics) {
        this.graphics = graphics;
        world.renderWith(this);
    }

    public void toggleFeelers() {
        showFeelers = !showFeelers;
    }

    public void toggleWallNormals() {
        showWallNormals = !showWallNormals;
    }

    public void updateView(Vector viewPoint) {
        this.viewPoint = viewPoint.add(viewPoint);
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
        renderEnergyBar(vehicle, x, y);
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
        renderEnergyBar(miner, x, y);
    }

    private void renderHealthBar(Entity entity, int x, int y) {
        int yPos = y - ((int) entity.boundingRadius() + 10);
        graphics.drawRect(x - 15, yPos, 30, 7);
        Color originalColor = graphics.getColor();
        double health = entity.health();
        graphics.setColor(getColorFor(health));
        double healthBarLength = 29.0 * health;
        graphics.fillRect(x - 14, yPos + 1, (int) healthBarLength, 6);
        graphics.setColor(originalColor);
    }

    private void renderEnergyBar(Entity entity, int x, int y) {
        int yPos = y + ((int) entity.boundingRadius() + 10);
        graphics.drawRect(x - 15, yPos, 30, 7);
        Color originalColor = graphics.getColor();
        graphics.setColor(Color.BLUE);
        double energyBarLength = 29.0 * entity.energy();
        graphics.fillRect(x - 14, yPos + 1, (int) energyBarLength, 6);
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
        Vector from = wall.from().add(viewPoint);
        Vector to = wall.to().add(viewPoint);
        graphics.drawLine((int) from.x(), (int) from.y(), (int) to.x(), (int) to.y());
        graphics.setStroke(stroke);

        if (showWallNormals) {
            Vector centre = wall.centre().add(viewPoint);
            Vector normal = wall.normal();
            int x = (int) centre.x();
            int y = (int) centre.y();
            int toX = x + (int) (normal.x() * 10);
            int toY = y + (int) (normal.y() * 10);
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

    public void render(TargetingSystem targetingSystem) {
        Vector pos = targetingSystem.position().add(viewPoint);
        int x = (int) pos.x();
        int y = (int) pos.y();
        int radius = (int) targetingSystem.boundingRadius();
        int diameter = radius * 2;
        graphics.drawOval(x - radius, y - radius, diameter, diameter);
        double hx = targetingSystem.heading().x();
        double hy = targetingSystem.heading().y();
        graphics.drawLine(x + (int) (hx * radius), y + (int) (hy * radius),
                x + (int) (hx * diameter), y + (int) (hy * diameter));
    }

}
