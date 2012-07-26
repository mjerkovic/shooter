package shooter.ui;

import shooter.unit.*;
import shooter.unit.structure.BaseCamp;
import shooter.unit.structure.Mine;
import shooter.unit.structure.StorageTank;

public interface Renderer {

    void render(Vehicle vehicle);

    void render(Miner miner);

    void render(Signpost signpost);

    void render(WatchTower watchTower);

    void render(Bullet bullet);

    void render(Obstacle obstacle);

    void render(Wall wall);

    void render(Mine mine);

    void render(StorageTank storageTank);

    void render(BaseCamp baseCamp);

    void render(TargetingSystem targetingSystem);

}
