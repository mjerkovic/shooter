package shooter.geom;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Transformations {

    public static Vector pointToWorldSpace(Vector point, Vector heading, Vector side, Vector position) {
        Matrix matrix = new Matrix();
        matrix.rotate(heading, side);
        matrix.translate(position.x(), position.y());
        return matrix.transform(point);
    }

    public static Vector pointToLocalSpace(Vector point, Vector heading, Vector side, Vector position) {
        double Tx = -position.dot(heading);
        double Ty = -position.dot(side);
        double[][] matrix = new double[][] {
                { heading.x(), side.x(), 0 },
                { heading.y(), side.y(), 0 },
                { Tx, Ty, 1}
        };
        return new Matrix(matrix).transform(point);
    }

    public static Vector vectorToWorldSpace(Vector point, Vector heading, Vector side) {
        Matrix matrix = new Matrix();
        matrix.rotate(heading, side);
        return matrix.transform(point);
    }

    public static Vector rotateAroundOrigin(Vector origin, double angle) {
        AffineTransform t = AffineTransform.getRotateInstance(angle);
        Point2D result = new Point2D.Double();
        t.transform(new Point2D.Double(origin.x(), origin.y()), result);
        return new Vector(result.getX(), result.getY());
    }

}
