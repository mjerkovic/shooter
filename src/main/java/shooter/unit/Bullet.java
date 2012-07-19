package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public class Bullet extends MovingEntity {

    private static final double MAX_RANGE_SQUARED = 10000;

    private final Vector initialPosition;

    public Bullet(Army army, Vector position, Vector heading) {
        super(army, null, null);
        this.position = position;
        this.heading = heading;
        this.initialPosition = position;
        this.boundingRadius = 5;
    }

    @Override
    public void update() {
        position = position.add(heading.scale(10));
    }

    public boolean outOfRange() {
        return position.subtract(initialPosition).lengthSquared() > MAX_RANGE_SQUARED;
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
