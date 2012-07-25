package shooter.ui;

import shooter.unit.Bullet;
import shooter.unit.Miner;
import shooter.unit.Obstacle;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;
import shooter.unit.Wall;
import shooter.unit.WatchTower;
import shooter.unit.Weapon;
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

    void render(Weapon weapon);
}
