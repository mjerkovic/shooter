package shooter.world;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import shooter.geom.Vector;
import shooter.goals.Roam;
import shooter.goals.UserControl;
import shooter.steering.Direction;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.unit.Bullet;
import shooter.unit.Entity;
import shooter.unit.MovingEntity;
import shooter.unit.Obstacle;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;
import shooter.unit.Wall;
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
    private final Collection<Obstacle> obstacles;
    private final Collection<Wall> walls = newArrayList();

    public ShooterWorld() {
        vehicle = new Vehicle(new Vector(100, 100), new Vector(1, 0), 0.1, new UserControl(), new Steering(this));
        vehicle.steering().obstacleAvoidanceOn();
        vehicle.steering().wallAvoidanceOn();
        wanderer = new Vehicle(new Vector(300, 300), new Vector(1, 0), 0.3, new Roam(), new Steering(this));
        vehicles = newArrayList(vehicle, wanderer); //, random3, random4);
        signpost = new Signpost("Sign", 10, 50);
        watchTower = new WatchTower(300, 500, this, new Steering(this));
        obstacles = newArrayList();
        addObstacles();
        addVehicles();
        addWalls();
        entities = Lists.<Entity>newArrayList(vehicles);
        entities.add(signpost);
        entities.add(watchTower);
    }

    private void addVehicles() {
        Random rand = new Random();
        double headingX = rand.nextDouble() - rand.nextDouble();
        double headingY = rand.nextDouble() - rand.nextDouble();
        for (int i=0; i < 10; i++) {
            Vehicle v = new Vehicle(new Vector(rand.nextInt(600), rand.nextInt(600)), new Vector(headingX, headingY), 0.3, new Roam(), new Steering(this));
            v.steering().obstacleAvoidanceOn();
            v.steering().wallAvoidanceOn();
            vehicles.add(v);
        }
/*
        Vehicle random1 = new Vehicle(new Vector(random() * 300, random() * 300), new Vector(1, 0), 0.3, new Roam(), new Steering(this));
        random1.steering().obstacleAvoidanceOn(random1);
        random1.steering().wallAvoidanceOn();
        Vehicle random2 = new Vehicle(new Vector(random() * 300, random() * 300), new Vector(1, 0), 0.3, new Offset(random1, new Vector(20, 20)), new Steering(this));
        random2.steering().obstacleAvoidanceOn(random2);
        random2.steering().wallAvoidanceOn();
        vehicles.add(random1);
        vehicles.add(random2);
*/
    }

    private void addObstacles() {
        Random rand = new Random();
        for (int i=0; i < 5; i++) {
            obstacles.add(new Obstacle(rand.nextInt(600), rand.nextInt(600), rand.nextDouble() * 50));
        }
    }

    private void addWalls() {
        walls.add(new Wall(new Vector(0, 0), new Vector(600, 0)));
        walls.add(new Wall(new Vector(600, 0), new Vector(600, 600)));
        walls.add(new Wall(new Vector(600, 600), new Vector(0, 600)));
        walls.add(new Wall(new Vector(0, 600), new Vector(0, 0)));
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
        for (Obstacle obstacle : obstacles) {
            renderer.render(obstacle);
        }
        for (Wall wall : walls) {
            renderer.render(wall);
        }
    }

    public void moveVehicle(Direction direction) {
        vehicle.steering().directionOn(direction);
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

    public Collection<Obstacle> getObstacles() {
        return obstacles;
    }

    public Collection<Wall> getWalls() {
        return walls;
    }
}
