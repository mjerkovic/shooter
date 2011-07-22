package shooter.geom;

public class Transformations {

    public static Vector pointToWorldSpace(Vector point, Vector heading, Vector side, Vector position) {
        Matrix matrix = new Matrix();
        matrix.rotate(heading, side);
        matrix.translate(position.X(), position.Y());
        return matrix.transform(point);
    }

}
