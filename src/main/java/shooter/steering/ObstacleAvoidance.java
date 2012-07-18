package shooter.steering;

import static shooter.geom.Transformations.vectorToWorldSpace;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.util.FastMath;
import shooter.geom.Transformations;
import shooter.geom.Vector;
import shooter.unit.Entity;
import shooter.unit.MovingEntity;
import shooter.unit.Obstacle;

public class ObstacleAvoidance {

    private final MovingEntity entity;
    private final Collection<Obstacle> obstacles;

    public ObstacleAvoidance(MovingEntity entity, Collection<Obstacle> obstacles) {
        this.entity = entity;
        this.obstacles = obstacles;
    }

    public Vector calculate() {
        double boxLength = 50;
        Entity closestObstacle = null;
        double distanceToClosestIntersectingPoint = Double.MAX_VALUE;
        Vector localPositionOfClosestObstacle = null;

        for (Obstacle obstacle : tagObstaclesWithinViewRange(boxLength)) {
            Vector localPos = Transformations.pointToLocalSpace(obstacle.position(), entity.heading(), entity.side(), entity.position());
            if (localPos.x() <  0) {
                continue;
            }
            double expandedRadius = obstacle.boundingRadius() + entity.boundingRadius();
            if (FastMath.abs(localPos.y()) < expandedRadius) {
                double cX = localPos.x();
                double cY = localPos.y();
                double sqrtPart = FastMath.sqrt(expandedRadius * expandedRadius - cY*cY);
                double ip = cX - sqrtPart;
                if (ip <= 0) {
                    ip = cX + sqrtPart;
                }
                distanceToClosestIntersectingPoint = ip;
                closestObstacle = obstacle;
                localPositionOfClosestObstacle = localPos;
            }
        }
        Vector steeringForce = Vector.ZERO;
        if (closestObstacle != null) {
            double multiplier = 1.0 + (boxLength - localPositionOfClosestObstacle.x()) / boxLength;
            double sY = (closestObstacle.boundingRadius()-localPositionOfClosestObstacle.y()) * multiplier;
            double brakingWeight = 0.2;
            double sX = (closestObstacle.boundingRadius() - localPositionOfClosestObstacle.x()) * brakingWeight;
            steeringForce = new Vector(sX, sY);
        }
        return vectorToWorldSpace(steeringForce, entity.heading(), entity.side());

    }

    private Collection<Obstacle> tagObstaclesWithinViewRange(double boxLength) {
        Collection<Obstacle> obsts = new ArrayList<Obstacle>(obstacles.size());
        for (Obstacle obstacle : obstacles) {
            Vector to = obstacle.position().subtract(entity.position());
            double range = boxLength + obstacle.boundingRadius();
            if (to.lengthSquared() < range * range) {
                obsts.add(obstacle);
            }
        }
        return obsts;
    }
}
