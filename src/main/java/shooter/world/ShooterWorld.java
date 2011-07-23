package shooter.world;

import shooter.geom.Vector;
import shooter.goals.Roam;
import shooter.goals.Track;
import shooter.goals.UserControl;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;
import shooter.unit.WatchTower;

public class ShooterWorld implements GameWorld {

    private int x =  0;
    private int y = 0;

    private Vehicle vehicle;
    private Vehicle wanderer;
    private Signpost signpost;
    private WatchTower watchTower;

    public ShooterWorld() {
        vehicle = new Vehicle(0, 0, new UserControl(), new Steering());
        wanderer = new Vehicle(300, 300, new Roam(), new Steering());
        signpost = new Signpost("Sign", 100, 100);
        watchTower = new WatchTower(300, 570, new Track(this), new Steering());
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

    public Vehicle getWanderer() {
        return wanderer;
    }

}
