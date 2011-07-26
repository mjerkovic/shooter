package shooter.ui;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;
import static shooter.geom.Transformations.pointToLocalSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import shooter.geom.Vector;
import shooter.unit.Entity;
import shooter.unit.Vehicle;
import shooter.world.ShooterWorld;

public class CompassPanel extends JPanel {

    private final ShooterWorld world;

    public CompassPanel(ShooterWorld world) {
        this.world = world;
        setBackground(BLACK);
        setForeground(YELLOW);
        setSize(new Dimension(200, 200));
        setPreferredSize(new Dimension(200, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawOval(0, 0, 200, 200);

        AffineTransform origTransform = graphics.getTransform();
        AffineTransform transform = AffineTransform.getTranslateInstance(100,100);
        transform.rotate(Math.toRadians(-90));
        transform.scale(0.1, 0.1);
        graphics.transform(transform);

        Vehicle vehicle = world.getVehicle();
        for (Entity entity : world.getEntities()) {
            double distance = entity.position().subtract(vehicle.position()).lengthSquared();
            //if (distance <= 1000) {
                Vector point = pointToLocalSpace(entity.position(), vehicle.heading(), vehicle.side(), vehicle.position());
                setForeground(RED);
                graphics.fillRect((int) point.X() - 20, (int) point.Y() - 20, 40, 40);
            //}
        }

        setForeground(YELLOW);
        graphics.fillRect(-20, -20, 40, 40);

        graphics.setTransform(origTransform);
    }

}
