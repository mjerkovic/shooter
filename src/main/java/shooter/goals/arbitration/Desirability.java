package shooter.goals.arbitration;

import shooter.goals.Goal;

public class Desirability<T> {

    private final Goal<T> goal;
    private final double score;

    public Desirability(Goal<T> goal, double score) {
        this.goal = goal;
        this.score = score;
    }

    public Goal<T> getGoal() {
        return goal;
    }

    public double getScore() {
        return score;
    }

}
