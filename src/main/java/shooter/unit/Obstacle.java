package shooter.unit;

import shooter.geom.Vector;

public class Obstacle extends Entity {

    public Obstacle(int x, int y, double boundingRadius) {
        this.position = new Vector(x, y);
        this.boundingRadius = boundingRadius;
    }

}
