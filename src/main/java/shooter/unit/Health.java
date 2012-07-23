package shooter.unit;

public enum Health {

    GOOD,
    AVERAGE,
    POOR;

    public static Health getHealthFor(double health) {
        if (health <= 0.33) {
            return POOR;
        }
        else if (health <= 0.66) {
            return AVERAGE;
        } else {
            return GOOD;
        }
    }

}
