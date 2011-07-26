package shooter.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameDisplay extends JFrame implements WindowListener {

    public static final Dimension DISPLAY_SIZE = new Dimension(800, 600);

    private final GamePanel gamePanel;

    public GameDisplay(GamePanel gamePanel, InfoPanel infoPanel) {
        super("");
        setLocation(600, 600);
        setPreferredSize(DISPLAY_SIZE);
        setSize(DISPLAY_SIZE);
        addWindowListener(this);
        this.gamePanel = gamePanel;
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(infoPanel, BorderLayout.EAST);
        c.add(gamePanel, BorderLayout.CENTER);
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
