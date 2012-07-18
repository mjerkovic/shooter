package shooter.geom;

import static shooter.geom.Transformations.rotateAroundOrigin;

import shooter.unit.MovingEntity;

public class Geometry {
    
    public static LineIntersection lineIntersects(Vector position, Vector feeler, Vector from, Vector to) {
        double AyCy = position.y() - from.y();
        double DxCx = to.x() - from.x();
        double AxCx = position.x() - from.x();
        double DyCy = to.y() - from.y();

        double rTop = (AyCy * DxCx) - (AxCx * DyCy);

        double BxAx = feeler.x() - position.x();
        double ByAy = feeler.y() - position.y();

        double rBot = BxAx * DyCy - ByAy * DxCx;
        double sTop = AyCy * BxAx - AxCx * ByAy;
        double sBot = BxAx * DyCy - ByAy * DxCx;

        if (rBot == 0 || sBot == 0) {
            return new LineIntersection(false, 0, Vector.ZERO);  // lines are parallel
        }

        double r = rTop / rBot;
        double s = sTop / sBot;

        if (r > 0 && r < 1 && s > 0 && s < 1) {
            Vector point = feeler.subtract(position);
            point = point.scale(r);
            point = position.add(point);
            return new LineIntersection(true, position.subtract(feeler).length() * r, point);
        }

        return new LineIntersection(false, 0, Vector.ZERO);
    }

    public static Vector[] createFeelersFor(MovingEntity entity) {
        Vector[] feelers = new Vector[3];

        feelers[0] = entity.position().add(entity.heading().scale(30));

        Vector temp = rotateAroundOrigin(entity.heading(), ((Math.PI / 2) * 3.5));
        feelers[1] = entity.position().add(temp.scale(30));

        temp = rotateAroundOrigin(entity.heading(), ((Math.PI / 2) * 0.5));
        feelers[2] = entity.position().add(temp.scale(30));

        return feelers;
    }

}
