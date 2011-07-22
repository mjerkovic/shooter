package shooter.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameDisplay extends JFrame implements WindowListener {

    private final GamePanel gamePanel;

    public GameDisplay(GamePanel gamePanel) {
        super("");
        setPreferredSize(new Dimension(600, 600));
        addWindowListener(this);
        Container c = getContentPane(); // default BorderLayout used
        this.gamePanel = gamePanel;
        c.add(gamePanel, "Center");
        pack();
        setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        gamePanel.stopGame();
    }

    public void windowIconified(WindowEvent e) {
        gamePanel.pauseGame();
    }

    public void windowDeiconified(WindowEvent e) {
        gamePanel.resumeGame();
    }

    public void windowActivated(WindowEvent e) {
        gamePanel.resumeGame();
    }

    public void windowDeactivated(WindowEvent e) {
        gamePanel.pauseGame();
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

}
