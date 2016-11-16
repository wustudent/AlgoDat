
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Represents the match of two wave files
 * 
 * Implements the comparison and matching methods according
 * to the optimal dynamic programming presented in the tutorial
 */
public class Matcher implements IMatcher {

	/**
	 * Signal X
	 */
	protected ISignal signalX;
	/**
	 * Signal Y
	 */
	protected ISignal signalY;

	
	/**
	 * The distance/dissimilarity score between
	 * the two wav files. The score is calculated in the
	 * compute method
	 * 
	 *  @see Matcher.compute
	 *  @see Matcher.computeDistance
	 */
	protected double distance = -1.0;

	/**
	 * The path of minimal distance between the two
	 * wave files.
	 * 
	 * The first integer of the pair denotes the
	 * frame number of WavFile w, the second integer
	 * the frame number of WavFile v
	 * 
	 * It will be stored here by the compute method
	 * 
	 * @see Matcher.compute
	 */
	protected List<Pair<Integer, Integer>> matchingPath = null;

	/**
	 * The accumulated distance/dissimilarity matrix
	 * will be stored here by the compute method
	 * 
	 * @see Matcher.compute
	 */
	protected double accumulatedDistance[][] = null;
	
	
		/**
	 * Construct a WavDistance object
	 *
	 * With this constructor, you can use signals defined by a buffer
	 * 
	 * For both passed ISignal buffers we have to check that
	 * they only have one channel and that they do not
	 * exceed the maximum number of frames MAX_FRAMES
	 * 
	 * @param dataX Array with signal values
	 * @param dataY Array with signal values
	 * @param sampleRate in samples per second
	 */
	public Matcher(double[] dataX, double[] dataY, int sampleRate) {
		this.signalX = new SignalFromBuffer(dataX, sampleRate);
		this.signalY = new SignalFromBuffer(dataY, sampleRate);
	}


	/**
	 * Construct a Matcher object
	 *
	 * With this constructor, you can use signals defined by a buffer
	 * 
	 * For both passed ISignal buffers we have to check that
	 * they only have one channel and that they do not
	 * exceed the maximum number of frames MAX_FRAMES
	 * 
	 * @param x 1st Object with ISignal interface
	 * @param y 2nd Object with ISignal interface
	 */
	public Matcher(ISignal x,ISignal y) {
		this.signalX = x;
		this.signalY = y;
	}
	
	/**
	 * Warps each signal to mimic the other one
	 *
	 * @return Pair of warped signals
	 * @throws IOException, WavFileException
	 */
	public Pair<ISignal, ISignal> warpSignals()  {
		
		 ISignal resultX = new SignalFromBuffer(matchingPath.size(), signalX.getSampleRate()); 
		 ISignal resultY = new SignalFromBuffer(matchingPath.size(), signalY.getSampleRate()); 
		
		//Construct signals:
		Iterator<Pair<Integer, Integer>> it = matchingPath.iterator();
		int indexX=0;
		int indexY=0;
		for (int matchcounter = 0; matchcounter < matchingPath.size(); matchcounter++) {
			Pair<Integer, Integer> pair = it.next();
			indexX = pair.getLeft();
			indexY = pair.getRight();
			resultX.setFrame(matchcounter, this.signalX.getFrame(indexX));
			resultY.setFrame(matchcounter, this.signalY.getFrame(indexY));
		}
		//trim the signal to the actual length of the signal:
		resultX.trimTo(indexX);
		resultY.trimTo(indexY);
		
		Pair <ISignal, ISignal> result = new Pair<ISignal, ISignal>(resultX, resultY);
		return result;
	}

	/**
	 * Returns the computed distance
	 * 
	 * @return Calculated distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Returns the computed mapping 
	 * 
	 * @return Calculated mapping
	 */
	public List<Pair<Integer, Integer>> getMappingPath() {
		return matchingPath;
	}

	/**
	 * 
	 * @return the computed mapping between the two signals as an array
	 * 		row: matches
	 * 		column: signals
	 */
	public int[][] getMappingPathAsArray() {
		int[][] array = new int[matchingPath.size()][2];
		int count = 0;
		for (Pair<Integer, Integer> pair : matchingPath) {
			array[count][0] = pair.getLeft();
			array[count][1] = pair.getRight();					
			count++;
		}
		return array;
	}

	
	/**
	 * Compute the distance score and the match between
	 * the two WavFiles
	 * 
	 * The result of this computation is stored in score
	 * 
	 * @throws WavFileException
	 */
	public void compute() throws RuntimeException {
		System.out.println("------");
		
		//Check size of the distance matrix to avoid bombing the memory heap
		Long heapsize = ((long)signalX.getNumFrames()) * ((long)signalX.getNumFrames()) * Double.SIZE / 1048576;
		System.out.println("Allocating " + heapsize.toString() + "MB");
		if (heapsize >  4000) {  // 4000 MB
			throw new RuntimeException("Signals are too long - I would gobble up too much memory when matching them!");
		} 
		
		// initialize the accumulated distance matrix properly
		accumulatedDistance = initializeAccDistanceMatrix(signalX, signalY);
	
		// use dynamic programming to compute accumulated distance matrix 
		computeAccDistanceMatrix(accumulatedDistance, signalX, signalY);
		// compute and store the distance score for the two files 
		distance = computeDistance(accumulatedDistance);
		// compute the mapping between the two files
		matchingPath = computeMatchingPath(accumulatedDistance);
		
		System.out.println("Distance: " + distance);
		System.out.println("------");
		
	}
	
	/**
	 * Create an accumulated distance matrix for the wave file  
	 * and initialize correctly
	 * 
	 * @return Preinitialized accumulated distance matrix with correct dimensions
	 */
	protected double[][] initializeAccDistanceMatrix(ISignal signalX, ISignal wavfileY) {
		// TODO Your implementation here
		double[][] AccMatrix=new double[signalX.getNumFrames()+1][wavfileY.getNumFrames()+1];	
		for(int i = 0; i <= signalX.getNumFrames(); i++){
			for(int j = 0; j <= wavfileY.getNumFrames(); j++){
				if(i==0&&j==0)
					AccMatrix[i][j] = 0;
				else if(i==0||j==0)
					AccMatrix[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		return AccMatrix;
	}
	
	/**
	 * Read the frames from the two WavFiles and use them
	 * to compute the accumulated distance matrix
	 * 
	 * @param signalX A double array of frames of file wavfileX 
	 * @param signalY A double array of frames of file wavfileY
	 * @param accDistance Preinitialized accumulated distance matrix to fill/compute
	 */
	protected void computeAccDistanceMatrix(double [][] accDistance, ISignal signalX, ISignal signalY) {
		if (accDistance == null) throw new RuntimeException("Called before initializing the distance matrix!");
		System.out.print("Computing...");
		// TODO Your implementation here	
		for(int i = 1; i <= signalX.getNumFrames(); i++){
			for(int j = 1; j <= signalY.getNumFrames(); j++){
				PriorityQueue<Double> pq=new PriorityQueue<>();
				pq.offer(accDistance[i-1][j-1]);
				pq.offer(accDistance[i][j-1]);
				pq.offer(accDistance[i-1][j]);
				double D=pq.poll();
				//System.out.println(D);
				accDistance[i][j]=D+Math.abs(signalX.getFrame(i-1)-signalY.getFrame(j-1));
			}	
		}
		System.out.println("done");		
		return;
	}
	
	/**
	 * Compute the distance of the final accumulated distance
	 * of the two input WavFiles and return it
	 * 
	 * @see distance
	 */
	protected double computeDistance(double[][] accDistance) throws RuntimeException {
		if(accDistance == null) {throw new RuntimeException("Called before computing the distance matrix!");}
		// TODO Your implementation here
		double x=accDistance[this.signalX.getNumFrames()][this.signalY.getNumFrames()];
		return x;
	}
	
	/**
	 * Compute the path that matches the two Signals
	 * and store it in the class variable "match".
	 * 
	 * We have to take care that the path is as short as
	 * possible: If we have the opportunity to go diagonally,
	 * we should do this 
	 * (i.e. take the lexicographically smallest pair) 
	 * 
	 * Also make sure that the indexes contained in the pair
	 * correspond to the indexes of the frames, i.e. that they
	 * range from 0 ... fileSize-1 (of the respective file)
	 * 
	 * Finally make sure that the path is in the correct order,
	 * from start to end!
	 * 
	 * @return pairs of frame indexes [ (x_i1, y_i1), ..., (x_in, y_in) ]  
	 */
	protected LinkedList<Pair<Integer,Integer>> computeMatchingPath(double[][] accDistance) throws RuntimeException {
		if(accDistance == null) {throw new RuntimeException("Called before computing the distance matrix!");}		
		System.out.print("Computing mapping path...");		
		// TODO Your implementation here
		Stack<Pair<Integer,Integer>> st=new Stack<Pair<Integer,Integer>>();
		LinkedList<Pair<Integer,Integer>> result=new LinkedList<Pair<Integer,Integer>>();
		int x=this.signalX.getNumFrames();
		int y=this.signalY.getNumFrames();
		while(!(x==0&&y==0)){
			Pair<Integer,Integer> p=new Pair<Integer,Integer>(x-1,y-1);
			st.push(p);
			if(accDistance[x-1][y-1]<=accDistance[x][y-1]&&accDistance[x-1][y-1]<=accDistance[x-1][y]){
				x--;
				y--;
			}
			else {
				if(accDistance[x][y-1]>=accDistance[x-1][y])
					x--;
				else
					y--;
			}
		}
		//st.push(new Pair<Integer,Integer>(0,0));
		while(st.size()!=0)
			result.add(st.pop());
		System.out.println("done");
		return result;
	}
	
}

