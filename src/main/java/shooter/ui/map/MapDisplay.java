package shooter.ui.map;

import static java.awt.Color.WHITE;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import shooter.unit.Entity;
import shooter.world.ShooterWorld;

public class MapDisplay extends JPanel {

    private final ShooterWorld world;

    public MapDisplay(ShooterWorld world) {
        this.world = world;
        Dimension size = new Dimension(600, 600);
        setSize(size);
        setPreferredSize(size);
        setBackground(WHITE);
        setFocusable(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform origTransform = g2d.getTransform();
        double scaleX = 600 / world.getWorldArea().x();
        double scaleY = 600 / world.getWorldArea().y();
        AffineTransform transform = AffineTransform.getScaleInstance(scaleX, scaleY);
        g2d.setTransform(transform);

        for (Entity entity : world.getEntities()) {
            g2d.fillRect((int) entity.position().x() - 6, (int) entity.position().y() - 6, 12, 12);
        }

        g2d.setTransform(origTransform);
        repaint();
    }

}
