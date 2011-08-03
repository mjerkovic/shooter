package shooter.steering;

import static shooter.geom.Transformations.rotateAroundOrigin;

import shooter.geom.Rotation;
import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class UserControlledBehaviour {

    private static final double FOURTY_FIVE_DEGREES = Math.PI / 2.0 * 0.5;

    public Vector calculate(MovingEntity entity, Direction direction, Rotation rotation) {
        if (Direction.NONE.equals(direction)) {
            entity.steering().directionOff();
            entity.stop();
            return Vector.ZERO;
        }
        Vector toPosition;
        if (entity.heading().angle(direction.heading()) > 1.5) {
            toPosition = entity.position().add(restrictTurnRate(entity, rotation).scale(100));
        } else {
            toPosition = entity.position().add(direction.heading().scale(100));
        }
        return new SeekBehaviour().calculate(entity, toPosition);
    }

    private Vector restrictTurnRate(MovingEntity entity, Rotation rotation) {
        return rotateAroundOrigin(entity.heading(), rotation.rotate(FOURTY_FIVE_DEGREES));
    }

}
