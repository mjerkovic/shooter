package shooter.goals;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;
import static shooter.goals.GoalState.INACTIVE;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class CompositeGoal<T> implements Goal<T> {

    private final Deque<Goal<T>> subGoals = new ArrayDeque<Goal<T>>();
    protected GoalState state = INACTIVE;

    @Override
    public void activate(T entity) {
        state = ACTIVE;
    }

    public GoalState process(T entity) {
        if (state == INACTIVE) {
            activate(entity);
        }
        return processSubGoals(entity);
    }

    public void addSubGoal(Goal<T> goal) {
        subGoals.push(goal);
    }

    protected GoalState processSubGoals(T entity) {
        if (subGoals.isEmpty()) {
            return COMPLETED;
        }
        GoalState subGoalState = subGoals.peek().process(entity);
        if (subGoalState.equals(COMPLETED)) {
            Goal<T> subGoal = subGoals.removeFirst();
            subGoal.terminate(entity);
        }
        return (!subGoals.isEmpty()) ? ACTIVE : subGoalState;
    }

    protected void clearSubGoals() {
        subGoals.clear();
    }

}
