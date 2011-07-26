package shooter.goals;

public interface Goal<T> {

    void process(T entity);

    void addGoal(Goal<T> goal);

}
