package shooter.ui.map;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WorldMap extends JFrame {

    public WorldMap(JPanel worldDisplay) throws HeadlessException {
        super("Map");
        getContentPane().add(worldDisplay);
        setLocation(0, 400);
        Dimension preferredSize = new Dimension(600, 600);
        setSize(preferredSize);
        setPreferredSize(preferredSize);
        pack();
        setVisible(true);
        validate();
    }

}
