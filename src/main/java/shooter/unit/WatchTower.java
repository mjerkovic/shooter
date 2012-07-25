package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.watchtower.WatchtowerDuty;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.world.ShooterWorld;

public class WatchTower extends Entity {

    public WatchTower(Vector position, double radius, MessageDispatcher radio, ShooterWorld world, Steering steering) {
        super(position, radius, radio);
    }

    @Override
    public void renderWith(GameRenderer renderer) {
    }
    
}
