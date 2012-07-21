package shooter.unit.structure;

import shooter.geom.Vector;
import shooter.ui.GameRenderer;
import shooter.unit.Entity;
import shooter.unit.Miner;

import java.util.concurrent.atomic.AtomicInteger;

public class Mine extends Entity {

    private final AtomicInteger yield;

    public Mine(Vector position, double radius, int yield) {
        super(position, radius);
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
