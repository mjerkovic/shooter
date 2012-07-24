package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;

public class Bullet extends MovingEntity {

    private static final double MAX_RANGE_SQUARED = 10000;

    private final Vector initialPosition;
    private final Entity shooter;
    private final Entity target;

    public Bullet(Entity shooter, Vector shooterHeading, Entity target) {
        super(shooter.position(), 5, null, null, null);
        this.heading = new Vector(shooterHeading);
        this.initialPosition = new Vector(shooter.position());
        this.shooter = shooter;
        this.target = target;
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
