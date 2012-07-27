package shooter.goals;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;
import static shooter.goals.GoalState.INACTIVE;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class CompositeGoal<T> implements Goal<T> {

    protected final Deque<Goal<T>> subGoals = new ArrayDeque<Goal<T>>();
    protected final String mainDescription;
    protected GoalState state = INACTIVE;

    public CompositeGoal(String mainDescription) {
        this.mainDescription = mainDescription;
    }

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

    public void addSubGoalToFront(Goal<T> goal) {
        subGoals.push(goal);
    }

    @Override
    public void addSubGoalToEnd(Goal<T> goal) {
        subGoals.addLast(goal);
    }

    @Override
    public void terminate(T entity) {
        if (!subGoals.isEmpty()) {
            subGoals.peek().terminate(entity);
        }
        if (state == ACTIVE) {
            state = INACTIVE;
        }
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
        state = (!subGoals.isEmpty()) ? ACTIVE : subGoalState;
        return state;
    }

    protected void clearSubGoals() {
        subGoals.clear();
    }

    @Override
    public String description() {
        return mainDescription + " - " + getSubGoalDescription();
    }

    private String getSubGoalDescription() {
        return (subGoals.isEmpty() ? "N/A" : subGoals.peek().description());
    }

}
