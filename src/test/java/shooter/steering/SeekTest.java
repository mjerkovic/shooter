package shooter.steering;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shooter.geom.Vector;
import shooter.unit.MovingEntity;
import shooter.unit.Vehicle;

@Test
public class SeekTest {

    private Seek seek;
    private Vector targetPos;
    private Vector difference;
    private Vector normalised;
    private Vector desiredVelocity;
    private Vector result;

    @BeforeMethod
    public void givenASeekBehaviour() {
        targetPos = mock(Vector.class);
        difference = mock(Vector.class);
        normalised = mock(Vector.class);
        desiredVelocity = mock(Vector.class);
        result = mock(Vector.class);

        when(targetPos.subtract(any(Vector.class))).thenReturn(difference);
        when(difference.normalise()).thenReturn(normalised);
        when(normalised.scale(anyDouble())).thenReturn(desiredVelocity);
        when(desiredVelocity.subtract(any(Vector.class))).thenReturn(result);

        seek = new Seek(mock(MovingEntity.class), targetPos);
    }

    public void itShouldReturnAVectorToTheTargetPosition() {
        assertThat(seek.calculate(), is(equalTo(result)));

        verify(targetPos).subtract(any(Vector.class));
        verify(difference).normalise();
        verify(normalised).scale(anyDouble());
        verify(desiredVelocity).subtract(any(Vector.class));
    }

    public void testMe() {
        Vehicle vehicle = new Vehicle(0, 0, null);
        seek = new Seek(vehicle, new Vector(4,4));
        Vector result = seek.calculate();
        System.out.println("result = " + result);
    }
}