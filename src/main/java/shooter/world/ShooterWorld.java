package shooter.world;

import java.util.ArrayList;
import java.util.List;

import shooter.geom.Vector;
import shooter.goals.Roam;
import shooter.goals.Track;
import shooter.goals.UserControl;
import shooter.steering.Direction;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.unit.Bullet;
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
    private List<Bullet> bullets = new ArrayList<Bullet>();

    public ShooterWorld() {
        vehicle = new Vehicle(0, 0, new UserControl(), new Steering(this));
        wanderer = new Vehicle(300, 300, new Roam(), new Steering(this));
        signpost = new Signpost("Sign", 10, 50);
        watchTower = new WatchTower(300, 570, new Track(this), new Steering(this));
    }

    public void update() {
        vehicle.update();
        wanderer.update();
        watchTower.update();
        for (Bullet bullet : bullets) {
            bullet.update();
        }
    }

    public void moveVehicleTo(Vector position) {
        vehicle.move(position);
        wanderer.wander();
    }

    public void renderWith(GameRenderer renderer) {
        renderer.render(vehicle);
        renderer.render(wanderer);
        renderer.render(signpost);
        renderer.render(watchTower);
        for (Bullet bullet : bullets) {
            renderer.render(bullet);
        }
    }

    public void moveVehicle(Direction direction) {
        vehicle.steering().directionOn(direction);
    }

    public Vehicle getWanderer() {
        return wanderer;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public Signpost getSignpost() {
        return signpost;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
