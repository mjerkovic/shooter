package shooter.unit;

import shooter.geom.Transformations;
import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;

public class Vehicle extends MovingEntity {

    public Vehicle(int x, int y, Goal goal, Steering steering) {
        super(goal, steering);
        steering.setOwner(this);
        position = new Vector(x, y);
    }

    public double X() {
        return position.X();
    }

    public double Y() {
        return position.Y();
    }

    public int[][] getShape() {
        Vector tip = new Vector(0, 5); //position.add(heading).scale(10);
        Vector left = new Vector(0, -5); //position.add(heading.perp()).scale(5);
        Vector right = new Vector(5, 0); //left.scale(-1);

        tip = Transformations.pointToWorldSpace(tip, heading, side, position);
        left = Transformations.pointToWorldSpace(left, heading, side, position);
        right = Transformations.pointToWorldSpace(right, heading, side, position);

        int[] xPos = { (int) tip.X(), (int) left.X(), (int) right.X() };
        int[] yPos = { (int) tip.Y(), (int) left.Y(), (int) right.Y() };


        return new int[][] { xPos, yPos };
    }

    public void wander() {
        steering.wanderOn();
    }

}
