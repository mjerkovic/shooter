package shooter.unit;

import shooter.geom.Vector;

public class Bullet extends MovingEntity {

    public Bullet(Vector position, Vector heading) {
        super(null, null);
        this.position = position;
        this.heading = heading;
    }

    @Override
    public void update() {
        position = position.add(heading.scale(10));
    }

}
