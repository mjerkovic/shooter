package shooter.geom;

import org.ejml.simple.SimpleMatrix;

public class Matrix {

    private SimpleMatrix matrix = SimpleMatrix.identity(3);

    public void rotate(Vector heading, Vector side) {
        matrix = matrix.mult(new SimpleMatrix(new double[][] {
                {heading.X(), heading.Y(), 0},
                {side.X(), side.Y(), 0},
                {0, 0, 1}
        }));
    }

    public void translate(double x, double y) {
        matrix = matrix.mult(new SimpleMatrix(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {x, y, 1}
        }));
    }

    public Vector transform(Vector point) {
        double tempX = (matrix.get(0, 0) * point.X()) + (matrix.get(1, 0) * point.Y() + matrix.get(2, 0));
        double tempY = (matrix.get(0, 1) * point.X()) + (matrix.get(1, 1) * point.Y() + matrix.get(2, 1));
        return new Vector(tempX, tempY);
    }

}
