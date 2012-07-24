package shooter.ui;

import static java.awt.Color.WHITE;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform origTransform = g2d.getTransform();
        AffineTransform transform = AffineTransform.getScaleInstance(0.333, 0.333);
        g2d.setTransform(transform);

        for (Entity entity : world.getEntities()) {
            g2d.fillRect((int) entity.position().x() - 2, (int) entity.position().y() - 2, 4, 4);
        }

        g2d.setTransform(origTransform);
    }

}
