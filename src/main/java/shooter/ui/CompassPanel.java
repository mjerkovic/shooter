package shooter.ui;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;
import static shooter.geom.Transformations.pointToLocalSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import shooter.geom.Vector;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;
import shooter.world.ShooterWorld;

public class CompassPanel extends JPanel {

    private final ShooterWorld world;

    public CompassPanel(ShooterWorld world) {
        this.world = world;
        setBackground(BLACK);
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
        graphics.transform(transform);

        Signpost signpost = world.getSignpost();
        Vehicle vehicle = world.getVehicle();
        Vector point = pointToLocalSpace(signpost.position(), vehicle.heading(), vehicle.side(), vehicle.position());

        setForeground(RED);
        graphics.fillRect((int) point.X() - 2, (int) point.Y() - 2, 4, 4);

        setForeground(YELLOW);
        graphics.fillRect(-2, -2, 4, 4);

        graphics.setTransform(origTransform);
    }

}
