package shooter.unit;

import shooter.geom.Vector;
import shooter.ui.Renderer;

public class Bullet extends Entity {

    private static final double MAX_RANGE_SQUARED = 10000;

    private final Vector initialPosition;
    private final Entity shooter;
    private final Entity target;

    public Bullet(Entity shooter, Entity target, Vector heading) {
        super(new Orientation(shooter.position(), heading, 8), null);
        this.initialPosition = shooter.position();
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

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
