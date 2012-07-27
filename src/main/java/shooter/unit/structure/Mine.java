package shooter.unit.structure;

import java.util.concurrent.atomic.AtomicInteger;

import shooter.ui.Renderer;
import shooter.unit.Entity;
import shooter.unit.Miner;
import shooter.unit.Orientation;

public class Mine extends Entity {

    private final AtomicInteger yield;

    public Mine(Orientation orientation, int yield) {
        super(orientation);
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

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }

}
