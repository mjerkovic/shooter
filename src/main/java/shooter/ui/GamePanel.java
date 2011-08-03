package shooter.ui;

import static java.awt.Color.WHITE;
import static java.lang.Math.max;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    private static final int PWIDTH = 600;
    private static final int PHEIGHT = 600;
    private static final int FRAMES_PER_SECOND = 80;
    private static final long DELAY = 41000000L;

    private static final long NANOS_PER_SECOND = 1000000000L;
    private volatile boolean running = false;

    private volatile boolean gameOver = false;
    private Thread animator;
    private Graphics dbg;
    private Image dbImage = null;
    protected final GameMediator mediator;

    public GamePanel(GameMediator gameMediator) {
        this.mediator = gameMediator;
        setBackground(WHITE);
        setSize(new Dimension(PWIDTH, PHEIGHT));
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        setFocusable(true);
        requestFocus();
        readyForTermination();
    }

    public void addNotify() {
        super.addNotify();
        startGame();
    }

    public void stopGame() {
        running = false;
    }

    public void run() {
        running = true;
        long timeDiff = 0;
        long startTime = System.nanoTime();
        while (running) {
            gameUpdate();
            gameRender();
            paintScreen();
            timeDiff = (startTime + DELAY) - System.nanoTime();
            try {
                //System.out.println("timeDiff/1000000L = " + timeDiff / 1000000L);
                Thread.sleep(max(0, (timeDiff/1000000L)));
            } catch (InterruptedException e) {
            }
            startTime = System.nanoTime();
        }
        System.exit(0);
    }

    private void paintScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if (g != null && dbImage != null) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        } catch (Exception e) {
            System.out.println("Graphics context error: " + e);
        }
    }

    private void startGame() {
        if (animator == null || !running) {
            animator = new Thread(this);
            animator.start();
        }
    }

    private void gameUpdate() {
        if (!gameOver) {
            mediator.update();
        }
    }

    private void gameRender() {
        if (dbImage == null) {
            dbImage = createImage(PWIDTH, PHEIGHT);
            if (dbImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                dbg = dbImage.getGraphics();
            }
        }
        dbg.setColor(WHITE);
        dbg.fillRect(0, 0, PWIDTH, PHEIGHT);
        Graphics2D g = (Graphics2D) dbg;
        g.setColor(Color.BLACK);

        mediator.renderUsing(g);
    }

    private void readyForTermination() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    running = false;
                }
            }
        });
    }

    public void pauseGame() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void resumeGame() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
