package shooter.ui;

import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import shooter.world.ShooterWorld;

public class StatusPanel extends JPanel {

    private final ShooterWorld world;
    private JList list;

    public StatusPanel(ShooterWorld world) {
        this.world = world;
        this.list = new JList();
        setSize(new Dimension(200, 400));
        setPreferredSize(new Dimension(200, 400));
        setLayout(new BorderLayout());
        add(this.list, BorderLayout.CENTER);
        setVisible(true);
    }

}
