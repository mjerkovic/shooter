package shooter.steering;

import static shooter.geom.Geometry.lineIntersects;
import static shooter.geom.Transformations.rotateAroundOrigin;

import java.util.Collection;

import shooter.geom.LineIntersection;
import shooter.geom.Vector;
import shooter.unit.MovingEntity;
import shooter.unit.Wall;

public class WallAvoidance implements SteeringBehaviour {

    private final MovingEntity entity;
    private final Collection<Wall> walls;
    private Vector[] feelers = new Vector[3];

    public WallAvoidance(MovingEntity entity, Collection<Wall> walls) {
        this.entity = entity;
        this.walls = walls;
        createFeelers();
    }

    public Vector calculate() {
        double distanceToThisIP = 0.0;
        double distanceToClosestIP = Double.MAX_VALUE;
        Wall closestWall = null;

        Vector steeringForce = Vector.ZERO, closestPoint = Vector.ZERO;

        for (int flr=0; flr < feelers.length; flr++) {
            for (Wall wall : walls) {
                LineIntersection intersection = lineIntersects(entity.position(), feelers[flr], wall.from(), wall.to());
                if (intersection.intersects()) {
                    if (intersection.distance() < distanceToClosestIP) {
                        distanceToClosestIP = intersection.distance();
                        closestWall = wall;
                        closestPoint = intersection.intersectionPoint();
                    }
                }
            }
            if (closestWall != null) {
                Vector overShoot = feelers[flr].subtract(closestPoint);
                steeringForce = closestWall.normal().scale(overShoot.length());
            }
        }

        return steeringForce;
    }

    private void createFeelers() {
        feelers[0] = entity.position().add(entity.heading().scale(30));

        Vector temp = rotateAroundOrigin(entity.heading(), ((Math.PI / 2) * 3.5));
        feelers[1] = entity.position().add(temp.scale(15));

        temp = rotateAroundOrigin(entity.heading(), ((Math.PI / 2) * 0.5));
        feelers[2] = entity.position().add(temp.scale(15));
    }

}
