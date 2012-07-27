package shooter.goals;

public interface Goal<T> {

    GoalState process(T entity);

    void addSubGoalToFront(Goal<T> goal);

    void addSubGoalToEnd(Goal<T> goal);

    void terminate(T entity);

    void activate(T entity);

    String description();

}
