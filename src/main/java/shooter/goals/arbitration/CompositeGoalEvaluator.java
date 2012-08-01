package shooter.goals.arbitration;

import shooter.unit.Entity;

import java.util.Arrays;
import java.util.Collection;

public class CompositeGoalEvaluator<T extends Entity> implements GoalEvaluator<T> {

    private Collection<GoalEvaluator<T>> evaluators;

    public CompositeGoalEvaluator(GoalEvaluator<T>... evaluators) {
        this.evaluators = Arrays.asList(evaluators);
    }

    @Override
    public Desirability<T> evaluate(T entity) {
        Desirability<T> mostDesired = null;
        for (GoalEvaluator<T> evaluator : evaluators) {
            Desirability<T> desirability = evaluator.evaluate(entity);
            if (mostDesired == null || desirability.getScore() > mostDesired.getScore()) {
                mostDesired = desirability;
            }
        }
        return mostDesired;
    }

}
