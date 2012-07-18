package shooter.geom;

import static org.apache.commons.math3.util.FastMath.cos;
import static org.apache.commons.math3.util.FastMath.sin;

import org.ejml.simple.SimpleMatrix;

public class Matrix {

    private SimpleMatrix matrix = SimpleMatrix.identity(3);

    public Matrix() {
    }

    public Matrix(double[][] matrix) {
        this.matrix = new SimpleMatrix(new double[][] {
                matrix[0],
                matrix[1],
                matrix[2]
        });
    }

    public void rotate(Vector heading, Vector side) {
        matrix = matrix.mult(new SimpleMatrix(new double[][] {
                {heading.x(), heading.y(), 0},
                {side.x(), side.y(), 0},
                {0, 0, 1}
        }));
    }

    public void rotate(double angle) {
        double sin = sin(angle);
        double cos = cos(angle);

        matrix = matrix.mult(new SimpleMatrix(new double[][] {
                {cos, sin, 0},
                {-sin, cos, 0},
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
        double tempX = (matrix.get(0, 0) * point.x()) + (matrix.get(1, 0) * point.y() + matrix.get(2, 0));
        double tempY = (matrix.get(0, 1) * point.x()) + (matrix.get(1, 1) * point.y() + matrix.get(2, 1));
        return new Vector(tempX, tempY);
    }

}
