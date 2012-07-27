package shooter.unit.structure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import shooter.ui.Renderer;
import shooter.unit.Entity;
import shooter.unit.Miner;
import shooter.unit.Orientation;

public class BaseCamp extends Entity {

    Map<Miner, Long> minersUnloading = new ConcurrentHashMap<Miner, Long>();
    AtomicInteger energy = new AtomicInteger(0);

    public BaseCamp(Orientation orientation) {
        super(orientation);
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
    }
    public void startUnloading(Miner miner) {
        minersUnloading.put(miner, System.currentTimeMillis());
    }

    public void unload(Miner miner) {
        long lastUnload = minersUnloading.get(miner);
        long now = System.currentTimeMillis();
        if (miner.unload(now - lastUnload)) {
            energy.incrementAndGet();
            minersUnloading.put(miner, now);
        }
    }

    public void unloadingCompleted(final Miner miner) {
        minersUnloading.remove(miner);
    }

}
