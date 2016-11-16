import org.junit.* ;
import static org.junit.Assert.* ;

public class CuboidUnitTest {
    private double delta = 1e-5;

    @SuppressWarnings("unused")
	@Test(timeout=1000)
    public void testEmptyConstructor(){
        Cuboid c = new Cuboid();
    }

    @Test(timeout=1000)
    public void testNormalConstructor(){
        Cuboid c = new Cuboid(3, 3, 3);
        assertEquals("The return value is not equals the input to the constructor", 3, c.getLength(), delta);
    }

    @Test(timeout=1000)
    public void testHierarchy1(){
        Cuboid c = new Cuboid(3, 3, 3);
        assertTrue("Cuboid does not inherit from Body", c instanceof Body);
    }

    @Test(timeout=1000)
    public void testCalculateVolume(){
        Cuboid c = new Cuboid(3, 3, 3);
        assertEquals("calculateVolume returns the wrong value", 27, c.calculateVolume(), delta);
    }

    @Test(timeout=1000)
    public void testCalculateSurface(){
        Cuboid c = new Cuboid(3, 3, 3);
        assertEquals("calculateSurface returns the wrong value", 54, c.calculateSurface(), delta);
    }
}
