package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public class Bullet extends MovingEntity {

    private static final double MAX_RANGE_SQUARED = 10000;

    private final Vector initialPosition;
    private final Entity shooter;
    private final Entity target;

    public Bullet(Entity shooter, Entity target) {
        super(shooter.position(), 8, null, null, null);
        this.shooter = shooter;
        this.target = target;
        this.heading = shooter.heading();
        this.initialPosition = shooter.position();
    }

    @Override
    public void update() {
        position = position.add(heading.scale(10));
    }

    public boolean outOfRange() {
        return position.subtract(initialPosition).lengthSquared() > MAX_RANGE_SQUARED;
    }

    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
