package shooter.unit;

public enum Health {

    GOOD,
    AVERAGE,
    POOR;

    public static Health getHealthFor(int health) {
        if (health <= 33) {
            return POOR;
        }
        else if (health <= 66) {
            return AVERAGE;
        } else {
            return GOOD;
        }
    }

}
