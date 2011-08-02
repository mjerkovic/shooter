package shooter.steering;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;
import shooter.geom.Transformations;
import shooter.geom.Vector;

@Test
public class DefaultSteeringBehaviourTest {

    @Test(enabled = false)
    public void itShouldAlwaysReturnAZeroVector() {
        assertThat(new DefaultSteeringBehaviour().calculate(), is(equalTo(Vector.ZERO)));
    }

    public void testme() {
        Vector vector = Transformations.vectorToWorldSpace(new Vector(-0.70713, -0.70713), new Vector(0.70713, 0.70713), new Vector(-0.70713, 0.70713));
        System.out.println("vector = " + vector);
    }
    
}
