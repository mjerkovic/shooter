package shooter.ui.map;

import javax.swing.*;
import java.awt.*;

public class WorldMap extends JFrame {

    public WorldMap(JPanel worldDisplay) throws HeadlessException {
        super("Map");
        getContentPane().add(worldDisplay);
        setLocation(0, 400);
        Dimension preferredSize = new Dimension(600, 600);
        setSize(preferredSize);
        setPreferredSize(preferredSize);
        setFocusable(false);
        pack();
        setVisible(true);
        validate();
    }

}
