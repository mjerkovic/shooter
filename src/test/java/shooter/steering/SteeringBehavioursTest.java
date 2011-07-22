package shooter.steering;

import static org.hamcrest.MatcherAssert.assertThat;
import static shooter.geom.VectorMatchers.hasCoordinates;

import org.testng.annotations.Test;
import org.testng.collections.Lists;

@Test
public class SteeringBehavioursTest {

    public void itShouldCalculateZeroForceWhenThereAreNoSteeringBehaviours() {
        assertThat(new SteeringBehaviours(Lists.<SteeringBehaviour>newArrayList()).calculateForce(), hasCoordinates(0, 0));
    }

}
