package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.Bullet;
import shooter.unit.MovingEntity;
import shooter.world.ShooterWorld;

public class Fire {

    private long lastShot;

    public Vector calculate(MovingEntity shooter, MovingEntity target, ShooterWorld world) {
        Vector toTarget = target.position().subtract(shooter.position());
        double relativeHeading = toTarget.normalise().dot(shooter.heading());
        double range = toTarget.length();
        if (relativeHeading >= 0.99 && range < 200) {
            shoot(shooter, world);
        }
        return Vector.ZERO;
    }

    private void shoot(MovingEntity shooter, ShooterWorld world) {
        long now = System.currentTimeMillis();
        if (lastShot == 0 || now - lastShot >= 1000) {
            world.addBullet(new Bullet(shooter.position(), shooter.heading()));
        }
    }

}
