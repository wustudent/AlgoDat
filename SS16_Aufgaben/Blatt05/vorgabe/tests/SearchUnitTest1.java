import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SearchUnitTest1 {
	private Graph g1;

	public final static int DF = 0;
	public final static int BF = 1;
	public final static int SAME = 2;

	public static boolean printValues = true;
	
	private enum Tests {

		TestGraphBDS01("TestGraph01", SAME, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 
		TestGraphBS03("TestGraph02", BF, 0, 4, 2, 1, 3), 
		TestGraphDS03("TestGraph02", DF, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

		String name;
		int[] reihenfolge;
		int art;

		Tests(String name, int art, int... zahl) {
			this.name = name;
			this.art = art;
			this.reihenfolge = zahl;
		}
	}

	@Before
	public void setUp() throws Exception {
		// read graph from file
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/graphBFS_VS_DFS.txt");
			g1.setShowSteps(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDepthFirstOrderEmptyGraph() {
		Graph g2 = new DiGraph();
		boolean succeed=false;
		try{
			List<Node> nodeList = g2.depthFirstSearch(0);
			if(nodeList==null)
				succeed=true;
		}
		catch(RuntimeException e){
			succeed=true;
		}
		assertTrue("testDepthFirstOrderEmptyGraph failed",succeed);
	}

	@Test
	public void testDepthFirstOrderNonExistingNode() {
		boolean succeed=false;
		try{
			List<Node> nodeList = g1.depthFirstSearch(100);
			if(nodeList==null)
				succeed=true;
		}
		catch(RuntimeException e){
			succeed=true;
		}
		assertTrue("testDepthFirstOrderEmptyGraph failed",succeed);
	}

	@Test
	public void testDepthFirstOrder() {
		Tests test = Tests.TestGraphBDS01;
		g1 = loadTxt(test.name);
		if (!compareIDS(g1.depthFirstSearch(0), DF, test,printValues))
			fail("Reinfolge nicht korekkt");
	}

	@Test
	public void testBreadthFirstOrderEmptyGraph() {
		Graph g2 = new DiGraph();
		boolean succeed=false;
		try{
			List<Node> nodeList = g2.breadthFirstSearch(0);
			if(nodeList==null)
				succeed=true;
		}
		catch(RuntimeException e){
			succeed=true;
		}
		assertTrue("testDepthFirstOrderEmptyGraph failed",succeed);
	}

	@Test
	public void testBreadthFirstOrderNonExistingNode() {
		boolean succeed=false;
		try{
			List<Node> nodeList = g1.breadthFirstSearch(100);
			if(nodeList==null)
				succeed=true;
		}
		catch(RuntimeException e){
			succeed=true;
		}
		assertTrue("testDepthFirstOrderEmptyGraph failed",succeed);
	}

	@Test
	public void testBreadthFirstOrder() {
		Tests test = Tests.TestGraphBS03;
		g1 = loadTxt(test.name);
		if (!compareIDS(g1.breadthFirstSearch(0), BF, test, printValues))
			fail("Reinfolge nicht korekkt");
	}

	public static Graph loadTxt(String name) {
		Graph ret = null;
		try {
			ret = GraphIO.loadGraph("tests/testgraphen/" + name + ".txt");
			ret.setShowSteps(false);
		} catch (IOException e) {
			System.out.println("file not found: " + "tests/testgraphen/" + name);

			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Prints the list in the console
	 * 
	 * @param kind
	 *            what kind of search
	 * @param nodeList
	 *            the list
	 */
	public static void printNodeList(int kind, List<Node> nodeList) {
		if (nodeList == null || nodeList.size() == 0) {
			
			System.out.println("CompareList: " + nodeList);
			return;
		}
		
		
		System.out.println("\n" + (kind == 0 ? "depth first seach" : "breadth first seach"));
		for (Node node : nodeList) {
			System.out.print("ID: " + node.id + " edges --> ");
			for (Edge edge : node.edges) {
				System.out.print(edge.endnode.id + ", ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public static boolean compareIDS(List<Node> returnValue, int kindOfSearch, Tests testCase, boolean printCompare) {
		if (returnValue == null || testCase == null || returnValue.size() != testCase.reihenfolge.length) {
			
			if(printCompare)
				System.out.println("CompareList: " + returnValue);
			
			return false;
		}

		if (kindOfSearch != testCase.art && testCase.art != 2)
			System.out.println("Achtung du verwendest evtl die Falsche Suche");

		for (int i = 0; i < returnValue.size(); i++) {

			if (printCompare)
				System.out.println(returnValue.get(i).id + " == " + testCase.reihenfolge[i]);

			if (returnValue.get(i).id != testCase.reihenfolge[i])
				return false;
		}

		return true;
	}
}
