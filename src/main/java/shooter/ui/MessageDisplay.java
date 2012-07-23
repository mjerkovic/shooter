package shooter.ui;

import javax.swing.*;
import java.awt.*;

public class MessageDisplay extends JFrame {

    public MessageDisplay(JTextArea messageArea) {
        super("Messages");
        Container c = getContentPane();
        c.add(messageArea);
        Dimension preferredSize = new Dimension(400, 200);
        setSize(preferredSize);
        setPreferredSize(preferredSize);
        pack();
        setVisible(true);
    }
}
