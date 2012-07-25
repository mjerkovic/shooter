package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.Scouting;
import shooter.steering.Steering;
import shooter.world.ShooterWorld;

public class Scout extends Vehicle {

    public Scout(Vector position, double radius, Vector heading, double maxTurnRate, MessageDispatcher radio,
                 Steering steering, ShooterWorld world) {
        super(position, radius, heading, maxTurnRate, radio, new Scouting(), steering);
    }

}
