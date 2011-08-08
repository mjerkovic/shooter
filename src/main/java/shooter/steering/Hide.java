package shooter.steering;

import java.util.Collection;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;
import shooter.unit.Obstacle;

public class Hide implements SteeringBehaviour {

    private final MovingEntity entity;
    private final MovingEntity hunter;
    private final Collection<Obstacle> obstacles;

    public Hide(MovingEntity entity, MovingEntity hunter, Collection<Obstacle> obstacles) {
        this.entity = entity;
        this.hunter = hunter;
        this.obstacles = obstacles;
    }

    public Vector calculate() {
        double distToClosest = Double.MAX_VALUE;
        Vector bestHidingSpot = Vector.ZERO;

        for (Obstacle obstacle : obstacles) {
            Vector hidingSpot = getHidingSpot(obstacle.position(), obstacle.boundingRadius(), hunter.position());
            double dist = hidingSpot.subtract(hunter.position()).lengthSquared();
            if (dist < distToClosest) {
                distToClosest = dist;
                bestHidingSpot = hidingSpot;
            }
        }
        if (distToClosest == Double.MAX_VALUE) {
            return new Evade(entity, hunter).calculate();
        }
        return new Arrive(entity, bestHidingSpot).calculate();
    }

    private Vector getHidingSpot(Vector obstaclePos, double radius, Vector hunterPos) {
        double distFromBoundary = 30.0;
        double distAway = radius + distFromBoundary;
        Vector toObstacle = obstaclePos.subtract(hunterPos).normalise();
        return toObstacle.scale(distAway).add(obstaclePos);
    }
}
