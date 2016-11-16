import java.util.ArrayList;
import java.util.ListIterator;

//import javax.swing.text.html.HTMLDocument.Iterator;


/**
 * This class implements three methods. These test an array on a few
 * characteristics.
 *
 * @author AlgoDat-Tutoren
 *
 */
public class ArrayCheck {
	/**
	 * Tests all elements of the given array,
     * if they are divisible by the given divisor.
     *
     * @param arr
     *            array to be tested
     * @param divisor
     * 				number by which all elements of the given array should be divisible
     * @return true if all elements are divisible by divisor
     */
    public boolean allDivisibleBy(ArrayList<Integer> arr, int divisor) {
        if(arr==null||arr.size()==0)
        	return false;
        if(divisor==0)
        	return false;
        ListIterator<Integer> myIterator = arr.listIterator();
        while(myIterator.hasNext()) {        	
        	if(myIterator.next()%divisor!=0)
        		return false;
        }
        return true;
    }

    /**
     * Tests if the given array is a part of
     * the fibonacci sequence.
     *
     * @param arr
     *            array to be tested
     * @return true if the elements are part of
     *         the fibonacci sequence
     */
    public boolean isFibonacci(ArrayList<Integer> arr) {
    	if(arr==null||arr.size()<3)
    		return false;
    	ListIterator<Integer> arrIterator = arr.listIterator();
    	Integer a,b;
    	boolean flag;
    	do{   		
    		a=arrIterator.next();
    		b=arrIterator.next();
    		if(arrIterator.hasNext())
    			flag=true;
    		else
    			flag=false;
    		arrIterator.previous();
    		if(b<a)
    			return false;
    	} while(flag==true);   	
    	Integer x=0,y=1;
    	ArrayList<Integer> fibonacci=new ArrayList<Integer>();
    	fibonacci.add(x);
    	fibonacci.add(y);
    	while(y<b) {
    		y=x+y;
    		fibonacci.add(y);
    		x=y-x;
    	}
	    if(y!=b)
	    	return false; 
	    if(arr.size()>fibonacci.size())
	    	return false;
	    arrIterator = arr.listIterator();
	    ListIterator<Integer> fibIterator = fibonacci.listIterator();
	    while(arrIterator.hasNext())
	    	arrIterator.next();
	    while(fibIterator.hasNext())
	    	fibIterator.next();
	    while(arrIterator.hasPrevious()) {
	    	if(arrIterator.previous()!=fibIterator.previous())
	    		return false;
	    }
	    return true;
    }

}

