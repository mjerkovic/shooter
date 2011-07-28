package shooter.steering;

import static shooter.geom.Transformations.vectorToWorldSpace;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import shooter.geom.Rotation;
import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class UserControlledBehaviour {

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
        AffineTransform t = AffineTransform.getRotateInstance(rotation.rotate(entity.getMaxTurnRate()));
        t.translate(entity.position().X(), entity.position().Y());
        t.scale(1, -1);
        Point2D result = new Point2D.Double();
        t.transform(new Point2D.Double(entity.position().X() + entity.heading().X(),
                entity.position().Y() + entity.heading().Y()), result);
        return vectorToWorldSpace(new Vector(result.getX(), result.getY()), entity.heading(), entity.side());
    }

}
