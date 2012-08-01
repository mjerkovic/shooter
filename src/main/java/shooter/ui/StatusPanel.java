package shooter.ui;

import shooter.world.ShooterWorld;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    private final ShooterWorld world;
    private JList list;

    public StatusPanel(ShooterWorld world) {
        this.world = world;
        this.list = new JList();
        setSize(new Dimension(200, 400));
        setPreferredSize(new Dimension(200, 400));
        add(this.list);
        setVisible(true);
    }

}
