package shooter.unit.structure;

import java.util.concurrent.atomic.AtomicInteger;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.ui.Renderer;
import shooter.unit.Entity;
import shooter.unit.Miner;

public class Mine extends Entity {

    private final AtomicInteger yield;

    public Mine(Vector position, double radius, MessageDispatcher radio, int yield) {
        super(position, radius, radio);
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
