package shooter.world;

import shooter.geom.Vector;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;
import shooter.unit.WatchTower;

public class ShooterWorld implements GameWorld {

    private int x =  0;
    private int y = 0;

    private Vehicle vehicle = new Vehicle(0, 0, new Steering());
    private Vehicle wanderer = new Vehicle(300, 300, new Steering());
    private Signpost signpost = new Signpost("Sign", 100, 100);
    private WatchTower watchTower = new WatchTower(300, 500, new Steering());

    public ShooterWorld() {
        watchTower.track(wanderer);
    }

    public void update() {
        vehicle.update();
        wanderer.update();
        watchTower.update();
    }

    public void moveVehicleTo(Vector position) {
        vehicle.move(position);
        wanderer.wander();
    }

    public void renderVehiclesWith(GameRenderer renderer) {
        renderer.render(vehicle);
        renderer.render(wanderer);
        renderer.render(signpost);
        renderer.render(watchTower);
    }

}
