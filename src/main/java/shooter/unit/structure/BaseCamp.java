package shooter.unit.structure;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.ui.Renderer;
import shooter.unit.Entity;
import shooter.unit.Miner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseCamp extends Entity {

    Map<Miner, Long> minersUnloading = new ConcurrentHashMap<Miner, Long>();
    AtomicInteger energy = new AtomicInteger(0);

    public BaseCamp(Vector position, double boundingRadius, MessageDispatcher radio) {
        super(position, boundingRadius, radio);
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
