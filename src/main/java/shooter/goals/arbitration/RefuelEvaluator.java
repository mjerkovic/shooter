package shooter.goals.arbitration;

import shooter.unit.MovingEntity;
import shooter.world.ShooterWorld;

public class RefuelEvaluator<T extends MovingEntity> implements GoalEvaluator<T> {

    private final ShooterWorld world;

    public RefuelEvaluator(ShooterWorld world) {
        this.world = world;
    }

    @Override
    public Desirability<T> evaluate(T entity) {
        return new Desirability<T>(null, 1 - (distanceWithFuelRemaining(entity) / distanceToBase(entity)));
    }

    private double distanceWithFuelRemaining(T entity) {
        return (entity.energy() / entity.maxConsumption()) * entity.getMaxSpeed();
    }

    private double distanceToBase(T entity) {
        return world.getBase().position().subtract(entity.position()).length();
    }

}
