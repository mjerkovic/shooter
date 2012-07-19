package shooter.states;

public interface State<T> {

    void enter(T entity);

    State<T> execute(T entity);

    void terminate(T entity);

}
