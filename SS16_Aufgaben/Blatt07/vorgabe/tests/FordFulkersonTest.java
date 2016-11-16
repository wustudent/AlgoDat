import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class FordFulkersonTest
{
	
	private Network g1;
	
	@Test
	public void testFordFulkersonGraphFF1(){
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/graphFF_1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertEquals("Error Calculating Max Flow on Graph: graphFF_1 . ", 8, g1.fordFulkerson());
	}
 }

