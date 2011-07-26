package shooter.world;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import shooter.goals.Roam;
import shooter.goals.UserControl;
import shooter.steering.Direction;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.unit.Bullet;
import shooter.unit.Entity;
import shooter.unit.MovingEntity;
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
    private final Collection<Vehicle> vehicles;
    private final Collection<Entity> entities;

    public ShooterWorld() {
        vehicle = new Vehicle(100, 100, 0.1, new UserControl(), new Steering(this));
        wanderer = new Vehicle(300, 300, 0.3, new Roam(), new Steering(this));
        vehicles = newArrayList(vehicle, wanderer,
                new Vehicle((int) (random() * 300), (int) (random() * 300), 0.3, new Roam(), new Steering(this)),
                new Vehicle((int) (random() * 300), (int) (random() * 300), 0.3, new Roam(), new Steering(this)),
                new Vehicle((int) (random() * 300), (int) (random() * 300), 0.3, new Roam(), new Steering(this)),
                new Vehicle((int) (random() * 300), (int) (random() * 300), 0.3, new Roam(), new Steering(this)));
        signpost = new Signpost("Sign", 10, 50);
        watchTower = new WatchTower(300, 500, this, new Steering(this));
        entities = Lists.<Entity>newArrayList(vehicles);
        entities.add(signpost);
        entities.add(watchTower);
    }

    public void update() {
        for (Vehicle vehicle : vehicles) {
            vehicle.update();
        }
        watchTower.update();
        for (Bullet bullet : bullets) {
            bullet.update();
        }
    }

    public void renderWith(GameRenderer renderer) {
        for (Vehicle vehicle : vehicles) {
            renderer.render(vehicle);
        }
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

    public Signpost getSignpost() {
        return signpost;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

    public Collection<Entity> getEntities() {
        return entities;
    }

    public void shotFired(MovingEntity shooter, MovingEntity target) {
        addBullet(new Bullet(shooter.position(), shooter.heading()));
    }

    private void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

}
