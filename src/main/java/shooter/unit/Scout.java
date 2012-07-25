package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.Scouting;
import shooter.steering.Steering;
import shooter.world.ShooterWorld;

public class Scout extends Vehicle {

    private final TargetingSystem targetingSystem;

    public Scout(Vector position, double radius, Vector heading, double maxTurnRate, MessageDispatcher radio,
                 Steering steering, ShooterWorld world, Weapon weapon) {
        super(position, radius, heading, maxTurnRate, radio, new Scouting(), steering);
        targetingSystem = new TargetingSystem(world, this, weapon);
    }

}
