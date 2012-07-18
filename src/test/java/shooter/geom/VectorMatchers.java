package shooter.geom;

import static java.lang.String.format;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.number.IsCloseTo;

public class VectorMatchers {

    public static CoordinateMatcher hasCoordinates(double x, double y) {
        return new CoordinateMatcher(x, y);
    }

    public static FuzzyCoordinateMatcher hasCoordinatesCloseTo(double x, double y, double errorMargin) {
        return new FuzzyCoordinateMatcher(x, y, errorMargin);
    }

    private static class CoordinateMatcher extends TypeSafeMatcher<Vector> {

        private final double x;
        private final double y;

        public CoordinateMatcher(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean matchesSafely(Vector vector) {
            return vector.x() == x && vector.y() == y;
        }

        public void describeTo(Description description) {
            description.appendValue(format("[%f, %f]", x, y));
        }

    }

    private static class FuzzyCoordinateMatcher extends TypeSafeMatcher<Vector> {

        private final double x;
        private final double y;
        private final double errorMargin;
        private IsCloseTo xMatcher;
        private IsCloseTo yMatcher;

        public FuzzyCoordinateMatcher(double x, double y, double errorMargin) {
            this.x = x;
            this.y = y;
            this.errorMargin = errorMargin;
        }

        @Override
        public boolean matchesSafely(Vector vector) {
            return new IsCloseTo(x, errorMargin).matchesSafely(vector.x()) &&
                    new IsCloseTo(y, errorMargin).matchesSafely(vector.y());
        }

        public void describeTo(Description description) {
            description.appendValue(format("[%f, %f] with error margin of %f", x, y, errorMargin));
        }

    }

}
