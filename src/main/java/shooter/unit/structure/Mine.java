package shooter.unit.structure;

import java.util.concurrent.atomic.AtomicInteger;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;
import shooter.unit.Army;
import shooter.unit.Miner;
import shooter.unit.Unit;

public class Mine extends Unit {

    private final AtomicInteger yield;

    public Mine(Vector position, double radius, Army army, int yield) {
        super(position, radius, army);
        army.add(this);
        this.yield = new AtomicInteger(yield);
    }

    public int yield() {
        return yield.get();
    }

    public void mine(Miner miner) {
        yield.decrementAndGet();
        miner.load();
    }

    public boolean isEmpty() {
        return yield.get() == 0;
    }

    @Override
    public void renderWith(GameRenderer renderer) {
        renderer.render(this);
    }

}
