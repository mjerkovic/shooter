package shooter.geom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static shooter.geom.VectorMatchers.hasCoordinates;
import static shooter.geom.VectorMatchers.hasCoordinatesCloseTo;

import org.testng.annotations.Test;

@Test
public class VectorTest {

    public void itShouldCreateANewVectorWithXAndYCoordinates() {
        assertThat( new Vector(1, 2), hasCoordinates(1, 2));
    }

    public void itShouldCreateANewVectorFromAnExistingVector() {
        Vector v = new Vector(1, 2);
        assertThat(new Vector(v), is(not(sameInstance(v))));
    }

    public void itShouldCreateANewVectorFromAnExistingVectorWithTheSameCoordinates() {
        Vector v = new Vector(1, 2);
        assertThat(new Vector(v), hasCoordinates(1, 2));
    }

    public void itShouldOverrideToString() {
        assertThat(new Vector(1, 2).toString(), is(equalTo("[1.000000, 2.000000]")));
    }

    public void itShouldReturnANewVectorAsAResultOfAddingAVector() {
        Vector v1 = new Vector(1, 2);
        Vector v2 = new Vector(4, 5);
        Vector v3 = v1.add(v2);

        assertThat(v3, is(not(sameInstance(v1))));
        assertThat(v3, is(not(sameInstance(v2))));
        assertThat(v3, hasCoordinates(5, 7));
    }

    public void itShouldReturnANewVectorScaledByGivenValue() {
        Vector v1 = new Vector(1, 2);
        Vector v2 = v1.scale(3);

        assertThat(v2, is(not(sameInstance(v1))));
        assertThat(v2, hasCoordinates(3, 6));
    }

    public void itShouldTruncateTheVectorWhenLengthIsGreatherThanTheGivenValueAndReturnANewVector() {
        Vector v1 = new Vector(3, 4);

        Vector v2 = v1.truncate(3);

        assertThat(v2, is(not(sameInstance(v1))));
        assertThat(v2, hasCoordinatesCloseTo(1.8, 2.4, 0.000001));
    }

    public void itShouldNotTruncateTheVectorWhenGivenValueIsGreaterThanTheLengthOfTheVector() {
        Vector v1 = new Vector(3, 4);

        Vector v2 = v1.truncate(9);

        assertThat(v2, is(sameInstance(v1)));
        assertThat(v2, hasCoordinates(3, 4));
    }

    public void itShouldReturnTheLengthOfTheVector() {
        assertThat(new Vector(3, 4).length(), is(equalTo(5.0)));
    }

    public void itShouldReturnTheSquaredLengthOfTheVector() {
        assertThat(new Vector(3, 4).lengthSquared(), is(equalTo(25.0)));
    }

    public void itShouldReturnANormalisedVector() {
        Vector v1 = new Vector(3, 4);

        Vector v2 = v1.normalise();

        assertThat(v2, is(not(sameInstance(v1))));
        assertThat(v2, hasCoordinatesCloseTo(0.6, 0.8, 0.000001));
    }

    public void itShouldReturnANewVectorDividedByTheDivisor() {
        Vector v1 = new Vector(3, 6);

        Vector v2 = v1.dividedBy(3);

        assertThat(v2, is(not(sameInstance(v1))));
        assertThat(v2, hasCoordinates(1, 2));
    }

    public void itShouldReturnANewVectorPerpendicularToTheCurrentOne() {
        Vector v1 = new Vector(3, 6);

        Vector v2 = v1.perp();

        assertThat(v2, is(not(sameInstance(v1))));
        assertThat(v2, hasCoordinates(-6, 3));
    }

    public void itShouldReturnANewVectorWithTheResultOfSubtractingTwoVectors() {
        Vector v1 = new Vector(3, 6);
        Vector v2 = new Vector(2, 2);

        Vector v3 = v1.subtract(v2);

        assertThat(v3, is(not(sameInstance(v1))));
        assertThat(v3, is(not(sameInstance(v2))));
        assertThat(v3, hasCoordinates(1, 4));
    }

    public void itShouldReturnANewVectorThatIsTheReverseOfTheOriginalVector() {
        Vector v1 = new Vector(4, 3);

        Vector v2 = v1.reverse();

        assertThat(v2, is(not(sameInstance(v1))));
        assertThat(v2, hasCoordinates(-4, -3));
    }

}
