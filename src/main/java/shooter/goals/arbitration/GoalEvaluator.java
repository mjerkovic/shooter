package shooter.goals.arbitration;

import shooter.unit.Entity;

public interface GoalEvaluator<T extends Entity> {

    Desirability<T> evaluate(T entity);

}
