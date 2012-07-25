package shooter.unit;

public class Gun implements Weapon {

    private static final int RANGE_SQUARED = 40000;

    private long lastShot;

    public boolean inRange(double rangeSquared) {
        return rangeSquared <= RANGE_SQUARED;
    }

    public double rateOfFire(int milliseconds) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean fire() {
        long timeDiff = System.currentTimeMillis() - lastShot;
        if (lastShot == 0 || timeDiff >= 1000) {
            lastShot += timeDiff;
            return true;
        }
        return false;
    }

}
