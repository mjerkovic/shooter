package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public class Bullet extends MovingEntity {

    private static final double MAX_RANGE_SQUARED = 10000;

    private final Vector initialPosition;

    public Bullet(Vector position, Army army,  Vector heading) {
        super(position, 5, army, null, null);
        this.heading = heading;
        this.initialPosition = position;
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
