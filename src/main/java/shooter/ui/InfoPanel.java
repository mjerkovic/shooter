package shooter.ui;

import javax.swing.*;
import java.awt.*;

import shooter.world.ShooterWorld;

public class InfoPanel extends JPanel {

    public InfoPanel(ShooterWorld world) {
        setSize(new Dimension(200, 600));
        setPreferredSize(new Dimension(200, 600));
        setFocusable(true);
        setLayout(new BorderLayout());
        add(new CompassPanel(world), BorderLayout.NORTH);
        add(new StatusPanel(world), BorderLayout.CENTER);
        validate();
    }

}
