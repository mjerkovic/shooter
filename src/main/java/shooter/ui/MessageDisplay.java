package shooter.ui;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Container;
import java.awt.Dimension;

public class MessageDisplay extends JFrame {

    public MessageDisplay(JTextArea messageArea) {
        super("Messages");
        Container c = getContentPane();
        c.add(messageArea);
        Dimension preferredSize = new Dimension(400, 200);
        setSize(preferredSize);
        setPreferredSize(preferredSize);
        setFocusable(false);
        pack();
        setVisible(true);
    }
}
