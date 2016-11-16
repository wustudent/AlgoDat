import org.junit.* ;
import static org.junit.Assert.* ;

public class CubeUnitTest {
    private double delta = 1e-5;

    @SuppressWarnings("unused")
	@Test(timeout=1000)
    public void testEmptyConstructor(){
        Cube c = new Cube();
    }

    @Test(timeout=1000)
    public void testNormalConstructor(){
        Cube c = new Cube(3);
        assertEquals("The return value is not equals the input to the constructor", 3, c.getLength(), delta);
    }

    @Test(timeout=1000)
    public void testHierarchy1(){
        Cube c = new Cube(3);
        assertTrue("Cube does not inherit from Cuboid", c instanceof Cuboid);
    }

    @Test(timeout=1000)
    public void testHierarchy2(){
        Cube c = new Cube(3);
        assertTrue("Cube does not inherit from Body", c instanceof Body);
    }

    @Test(timeout=1000)
    public void testCalculateVolume(){
        Cube c = new Cube(3);
        assertEquals("calculateVolume returns the wrong value", 27, c.calculateVolume(), delta);
    }

    @Test(timeout=1000)
    public void testCalculateSurface(){
        Cube c = new Cube(3);
        assertEquals("calculateSurface returns the wrong value", 54, c.calculateSurface(), delta);
    }
}
