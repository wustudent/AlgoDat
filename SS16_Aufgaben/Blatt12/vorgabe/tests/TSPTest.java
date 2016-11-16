import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.lang.Math;


public class TSPTest {

	private TokenGridGraph g1;
	Picture picture;
	
	@Before
	public void setUp() throws Exception, IOException {
		// read graph from file
		
		picture = new Picture("tests/testgrids/supermarket.png");
//		picture = new Picture("tests/testgrids/16x16_supersimple.png");
//		picture = new Picture("tests/testgrids/16x16_waypoints.png");
//		picture = new Picture("tests/testgrids/floorplan_waypoints1.png");
//		picture = new Picture("tests/testgrids/floorplan_waypoints2.png");
	}
    
	@Test
	public void testTSPDrawPathPicture() throws IOException {		
		// This is not an automatic test. It will always succeed. You have to visually inspect the written pictures 
		// found_path_tsp.png written in the project directory.
		// Grey pixels are visited nodes, white pixels are unvisited nodes, red pixels are nodes on the path
		// (black pixels are empty areas with no nodes) 
		// The start node is in the lower-left of the picture
		g1 = new TokenGridGraph(picture);
		g1.setShowSteps(false);

		//This start and end node can be connected in a straight line:
		//CellNode startNode = g1.getCellNode(0,0);
		CellNode startNode = null; //if startNode is set to null, computeTour should use any existing token to start from
		
		System.out.print("Running TSP...");
		List<Node> path_dijkstra = g1.computeTour(startNode);
		System.out.println("finished. Tour length: "+path_dijkstra.size()+" steps.");
		g1.toPicture(path_dijkstra).savePicture("found_path_tsp.png");

	}
}





