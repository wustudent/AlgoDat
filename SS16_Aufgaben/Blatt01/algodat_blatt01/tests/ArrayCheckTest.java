import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;

//import org.junit.Before;
import org.junit.Test;


public class ArrayCheckTest {
	// NOTTODO DIESE ZEILE BITTE NICHT VERAENDERN!!
	// TODO Fuehrt eure Tests auf diesem ArrayCheck-Objekt aus!
	// Ansonsten kann eure Abgabe moeglicherweise nicht
	// gewertet werden.
	public ArrayCheck ArrayCheck = new ArrayCheck();


	@Test(timeout = 1000)
	public void testAllDivisibleBy() {
		ArrayList<Integer> arr1=new ArrayList<Integer>();
		ArrayList<Integer> arr2=new ArrayList<Integer>();
		Collections.addAll(arr1,0,2,4,6,8,10);
		int a=0, b=2, c=3;
		//ArrayCheck.allDivisibleBy(test1, a);
		assertEquals("AllDivisibleBy() Test divide by 0 Error"     , false, ArrayCheck.allDivisibleBy(arr1, a));
		assertEquals("AllDivisibleBy() Test contains 0"	           , true , ArrayCheck.allDivisibleBy(arr1, b));
		assertEquals("AllDivisibleBy() Test cannot work correctly" , false, ArrayCheck.allDivisibleBy(arr1, c));
		assertEquals("AllDivisibleBy() Test cannot recognize empty", false, ArrayCheck.allDivisibleBy(arr2, c));
		assertEquals("AllDivisibleBy() Test cannot recognize null" , false, ArrayCheck.allDivisibleBy(null, c));
		// TODO Schreibt verschiedene Testfaelle, die unterschiedliche Arrays
		// anlegen und an testAllDivisibleBy uebergeben.
		// Testet auch randfaelle wie z.B. leere arrays.
	}

	@Test(timeout = 1000)
	public void testIsFibonacci() {
		ArrayList<Integer> arr1=new ArrayList<Integer>();
		ArrayList<Integer> arr2=new ArrayList<Integer>();
		ArrayList<Integer> arr3=new ArrayList<Integer>();
		ArrayList<Integer> arr4=new ArrayList<Integer>();
		ArrayList<Integer> arr5=new ArrayList<Integer>();
		ArrayList<Integer> arr6=new ArrayList<Integer>();
		Collections.addAll(arr1,-1,1,0,1,1,2,3,5,8);
		Collections.addAll(arr2,2,5,7,12);
		Collections.addAll(arr3,0,1);
		Collections.addAll(arr4,0,1,1,2,3,5);
		Collections.addAll(arr5,2,3,5,8,13,21);
		assertEquals("IsFibonaccie() Test contains negative number"    , false, ArrayCheck.isFibonacci(arr1));
		assertEquals("IsFibonaccie() Test gived false sequence"        , false, ArrayCheck.isFibonacci(arr2));
		assertEquals("IsFibonaccie() Test contains less than 3 numbers", false, ArrayCheck.isFibonacci(arr3));
		assertEquals("IsFibonaccie() Test gived true sequence"         , true , ArrayCheck.isFibonacci(arr4));
		assertEquals("IsFibonaccie() Test gived true sequence"         , true , ArrayCheck.isFibonacci(arr5));
		assertEquals("IsFibonaccie() Test contains no number"          , false, ArrayCheck.isFibonacci(arr6));
		assertEquals("IsFibonaccie() Test is null"                     , false, ArrayCheck.isFibonacci(null));
		;// TODO Schreibt verschiedene Testfaelle, fuer testIsFibonacci.
	}


}

