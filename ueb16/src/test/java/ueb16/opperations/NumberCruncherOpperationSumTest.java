package ueb16.opperations;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Test;

public class NumberCruncherOpperationSumTest {
	
	private NumberCruncherOpperationSum underTest;

	private float [] testArrayOdd = {1, 2, 3, 4, 5};
	private float [] testArrayEven = {1, 2, 3, 4, 5, 6};
	private float [] solutionSumOdd = {1, 3, 6, 10, 15};
	private float [] solutionSumEven = {1, 3, 6, 10, 15, 21};	
	
	@Test
	public void applyTest1() {
		underTest = new NumberCruncherOpperationSum();
		underTest.apply(testArrayOdd);
		assertTrue(Arrays.equals(testArrayOdd, solutionSumOdd));
	}
	
	@Test
	public void applyTest2() {
		underTest = new NumberCruncherOpperationSum();
		underTest.apply(testArrayEven);
		assertTrue(Arrays.equals(testArrayEven, solutionSumEven));
	}

}
