package shooter.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(0.333, 0.333);
        for (Entity entity : world.getEntities()) {
            g2d.drawRect((int) entity.position().x() - 5, (int) entity.position().y() - 5, 10, 10);
        }
    }

}
