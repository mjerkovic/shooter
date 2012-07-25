package shooter;

import shooter.ui.GameDisplay;
import shooter.ui.GameMediator;
import shooter.ui.GamePanel;
import shooter.ui.GameRenderer;
import shooter.world.FlockWorld;

public class Flocking {

    public static void main(String[] args) {
        FlockWorld world = new FlockWorld();
        new GameDisplay(new GamePanel(new GameMediator(world, new GameRenderer(world))), null);
    }

}
