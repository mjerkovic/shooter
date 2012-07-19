package shooter.world;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import shooter.geom.Vector;
import shooter.goals.UserControl;
import shooter.states.StateMachine;
import shooter.states.miner.Collect;
import shooter.states.miner.GoToMine;
import shooter.steering.Direction;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.unit.Army;
import shooter.unit.Bullet;
import shooter.unit.Entity;
import shooter.unit.Miner;
import shooter.unit.MovingEntity;
import shooter.unit.Obstacle;
import shooter.unit.Signpost;
import shooter.unit.Vehicle;
import shooter.unit.Wall;
import shooter.unit.WatchTower;
import shooter.unit.structure.Mine;

public class ShooterWorld implements GameWorld {

    private Vehicle vehicle;
    private Signpost signpost;
    private WatchTower watchTower;
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private final Collection<Vehicle> vehicles;
    private final Collection<Entity> entities;
    private final Collection<Obstacle> obstacles;
    private final Collection<Wall> walls = newArrayList();
    private Army army;

    public ShooterWorld() {
        army = new Army();
        vehicle = new Vehicle(army, new Vector(100, 100), new Vector(1, 0), 0.1, new UserControl(), new Steering(this));
        vehicle.steering().obstacleAvoidanceOn();
        //Vehicle wanderer = new Vehicle(army, new Vector(300, 300), new Vector(1, 0), 0.3, new Roam(), new Steering(this));
        vehicles = newArrayList(vehicle); //, wanderer);
        signpost = new Signpost("Sign", 10, 250);
        watchTower = new WatchTower(army, 300, 500, this, new Steering(this));
        obstacles = newArrayList();
        addObstacles();
        addVehicles();
        addWalls();
        entities = Lists.<Entity>newArrayList(vehicles);
        entities.add(signpost);
        entities.add(watchTower);
        new Miner(army, new Vector(20, 50), new Vector(1, 0), new Steering(this), 100,
                new StateMachine<Miner>(new GoToMine(new Collect())));
        new Mine(army, new Vector(450, 60), 50.0, 3000);
    }

    private void addVehicles() {
/*
        Random rand = new Random();
        double headingX = rand.nextDouble() - rand.nextDouble();
        double headingY = rand.nextDouble() - rand.nextDouble();
        for (int i=0; i < 1; i++) {
            Vehicle v = new Vehicle(new Vector(rand.nextInt(600), rand.nextInt(600)), new Vector(headingX, headingY), 0.3, new Roam(), new Steering(this));
            v.steering().obstacleAvoidanceOn();
            vehicles.add(v);
        }
*/
    }

    private void addObstacles() {
/*
        Random rand = new Random();
        for (int i=0; i < 5; i++) {
            obstacles.add(new Obstacle(rand.nextInt(600), rand.nextInt(600), rand.nextDouble() * 50));
        }
*/
    }

    private void addWalls() {
    }

    public void update() {
        army.update();
        watchTower.update();
        for (Bullet bullet : bullets) {
            bullet.update();
        }
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            if (bulletIterator.next().outOfRange()) {
                bulletIterator.remove();
            }
        }

    }

    public void renderWith(GameRenderer renderer) {
        army.renderWith(renderer);
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

    public void moveTo(Vector position) {
        vehicle.steering().directionOff();
        vehicle.stop();
        vehicle.steering().arriveOn(position);
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
        addBullet(new Bullet(shooter.army(), shooter.position(), shooter.heading()));
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
