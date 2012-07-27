package shooter.unit;

public class Cannon implements Weapon {

    private final int rangeSquared;
    private final double rateOfFire;
    private double lastFired;

    public Cannon(int rangeSquared, double rateOfFire) {
        this.rangeSquared = rangeSquared;
        this.rateOfFire = rateOfFire;
    }

    @Override
    public boolean inRange(double rangeSquared) {
        return rangeSquared <= this.rangeSquared;
    }

    @Override
    public boolean fire() {
        long now = System.currentTimeMillis();
        double timeDiff = now - lastFired;
        if (timeDiff > rateOfFire) {
            lastFired = now;
            return true;
        }
        return false;
    }

}
