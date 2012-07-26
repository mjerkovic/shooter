package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.steering.Steering;
import shooter.ui.Renderer;
import shooter.world.ShooterWorld;

public class WatchTower extends Entity {

    public WatchTower(Vector position, Vector heading, double radius, MessageDispatcher radio) {
        super(position, radius, radio);
        this.heading = heading;
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
