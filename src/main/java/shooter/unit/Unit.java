package shooter.unit;

import shooter.geom.Vector;

public abstract class Unit extends Entity {

    protected Army army;

    public Unit(Vector position, double boundingRadius, Army army) {
        super(position, boundingRadius);
        this.army = army;
    }

    public Army army() {
        return army;
    }

}
