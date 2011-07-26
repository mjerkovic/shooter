package shooter.steering;

import shooter.geom.Vector;

public enum Direction {

    NONE(new Vector(0, 0)),
    NORTH(new Vector(0, -1)),
    NORTH_EAST(new Vector(1, -1)),
    EAST(new Vector(1, 0)),
    SOUTH_EAST(new Vector(1, 1)),
    SOUTH(new Vector(0, 1)),
    SOUTH_WEST(new Vector(-1, 1)),
    WEST(new Vector(-1, 0)),
    NORTH_WEST(new Vector(-1, -1));

    private final Vector heading;

    Direction(Vector heading) {
        this.heading = heading;
    }

    public Vector heading() {
        return heading;
    }

}
