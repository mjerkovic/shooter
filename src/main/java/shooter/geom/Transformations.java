package shooter.geom;

public class Transformations {

    public static Vector pointToWorldSpace(Vector point, Vector heading, Vector side, Vector position) {
        Matrix matrix = new Matrix();
        matrix.rotate(heading, side);
        matrix.translate(position.X(), position.Y());
        return matrix.transform(point);
    }

    public static Vector pointToLocalSpace(Vector point, Vector heading, Vector side, Vector position) {
        double Tx = -position.dot(heading);
        double Ty = -position.dot(side);
        double[][] matrix = new double[][] {
                { heading.X(), side.X(), 0 },
                { heading.Y(), side.Y(), 0 },
                { Tx, Ty, 1}
        };
        return new Matrix(matrix).transform(point);
    }

    public static Vector vectorToWorldSpace(Vector point, Vector heading, Vector side) {
        Matrix matrix = new Matrix();
        matrix.rotate(heading, side);
        return matrix.transform(point);
    }

}
