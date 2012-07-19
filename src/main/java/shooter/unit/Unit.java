package shooter.unit;

public class Unit extends Entity {

    protected Army army;

    public Unit(Army army) {
        this.army = army;
    }

    public Army army() {
        return army;
    }

}
