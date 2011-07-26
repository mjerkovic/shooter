package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class UserControlledBehaviour {

    public Vector calculate(MovingEntity entity, Direction direction) {
        if (Direction.NONE.equals(direction)) {
            entity.steering().directionOff();
            entity.stop();
            return Vector.ZERO;
        }
        Vector toPosition = entity.position().add(direction.heading().scale(100));
        return new SeekBehaviour().calculate(entity, toPosition);
    }

}
