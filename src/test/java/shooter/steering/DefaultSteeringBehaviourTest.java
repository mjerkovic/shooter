package shooter.steering;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;
import shooter.geom.Vector;

@Test
public class DefaultSteeringBehaviourTest {

    public void itShouldAlwaysReturnAZeroVector() {
        assertThat(new DefaultSteeringBehaviour().calculate(), is(equalTo(Vector.ZERO)));
    }
    
}
