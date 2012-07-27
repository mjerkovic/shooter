package shooter.goals;

public class Think<T> extends CompositeGoal<T> {

    private final Goal<T> initialGoal;

    public Think(Goal<T> initialGoal) {
        this.initialGoal = initialGoal;
    }

    @Override
    public GoalState process(T entity) {
        planGoal(entity);
        return processSubGoals(entity);
    }

    private void planGoal(T entity) {
        if (subGoals.isEmpty()) {
            addSubGoalToEnd(initialGoal);
        }
    }

}
