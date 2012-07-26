package shooter.comms;

import shooter.geom.Vector;
import shooter.unit.Entity;

public interface EventListener {

    void shotFired(Entity shooter, Vector heading, Entity target);

}
