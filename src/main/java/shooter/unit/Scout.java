package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.Scouting;
import shooter.steering.Steering;

public class Scout extends Vehicle {

    public Scout(Vector position, double radius, Vector heading, double maxTurnRate, MessageDispatcher radio, Steering steering) {
        super(position, radius, heading, maxTurnRate, radio, new Scouting(), steering);
    }

}
