package shooter.unit;

public abstract class Unit extends Entity {

    protected Army army;

    public Unit(Army army) {
        this.army = army;
    }

    public Army army() {
        return army;
    }

}
