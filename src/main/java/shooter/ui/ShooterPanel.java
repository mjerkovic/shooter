package shooter.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import shooter.steering.Direction;

public class ShooterPanel extends GamePanel {

    public ShooterPanel(final GameMediator mediator) {
        super(mediator);
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediator.clickRecordedAt(e.getX(), e.getY());
            }
        };
        addMouseListener(mouseListener);
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_NUMPAD8: mediator.moveVehicle(Direction.NORTH); break;
                    case KeyEvent.VK_NUMPAD6: mediator.moveVehicle(Direction.EAST); break;
                    case KeyEvent.VK_NUMPAD2: mediator.moveVehicle(Direction.SOUTH); break;
                    case KeyEvent.VK_NUMPAD4: mediator.moveVehicle(Direction.WEST); break;
                    case KeyEvent.VK_NUMPAD9: mediator.moveVehicle(Direction.NORTH_EAST); break;
                    case KeyEvent.VK_NUMPAD3: mediator.moveVehicle(Direction.SOUTH_EAST); break;
                    case KeyEvent.VK_NUMPAD1: mediator.moveVehicle(Direction.SOUTH_WEST); break;
                    case KeyEvent.VK_NUMPAD7: mediator.moveVehicle(Direction.NORTH_WEST); break;
                    default : ;
                }
            }
        };
        addKeyListener(keyListener);

    }
    
}
