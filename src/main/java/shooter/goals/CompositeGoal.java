package shooter.goals;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class CompositeGoal<T> implements Goal<T> {

    private final Deque<Goal<T>> goals = new ArrayDeque<Goal<T>>();

    public void process(T entity) {
    }

    public void addGoal(Goal<T> goal) {
        goals.push(goal);
    }

    protected abstract void processGoal(T entity);

}
