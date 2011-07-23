package shooter.world;

import shooter.geom.Vector;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;

public class ShooterWorld implements GameWorld {

    private int x =  0;
    private int y = 0;

    private Vehicle vehicle = new Vehicle(0, 0, new Steering());
    private Vehicle wanderer = new Vehicle(300, 300, new Steering());
    private Signpost signpost = new Signpost("Sign", 100, 100);

    public void update() {
        vehicle.update();
        wanderer.update();
    }

    public void moveVehicleTo(Vector position) {
        vehicle.move(position);
        wanderer.wander();
    }

    public void renderVehiclesWith(GameRenderer renderer) {
        Vector offset = vehicle.getPosition();
        renderer.render(vehicle, (int) offset.X(), (int) offset.Y());
        renderer.render(wanderer, (int) offset.X(), (int) offset.Y());
        renderer.render(signpost, (int) offset.X(), (int) offset.Y());
    }

}
