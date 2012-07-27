package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.goals.Goal;

public class Scout extends Vehicle {

    public Scout(Orientation orientation, MessageDispatcher radio, Goal<MovingEntity> brain, Movement movement) {
        super(orientation, radio, brain, movement);
    }

}
