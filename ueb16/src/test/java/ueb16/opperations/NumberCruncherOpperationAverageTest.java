package ueb16.opperations;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class NumberCruncherOpperationAverageTest {
	
	private NumberCruncherOpperationAverage underTest;

	private float [] testArrayOdd = {1, 2, 3, 4, 5};
	private float [] testArrayEven = {1, 2, 3, 4, 5, 6};
	private float [] solutionOdd = {1, 2, 3, 4, 3};
	private float [] solutionEven = {1, 2, 3, 4, 5, 21/6};	
	
	@Test
	public void applyTest1() {
		underTest = new NumberCruncherOpperationAverage();
		underTest.apply(testArrayOdd);
		assertTrue(Arrays.equals(testArrayOdd, solutionOdd));
	}
	
	@Test
	public void applyTest2() {
		underTest = new NumberCruncherOpperationAverage();
		underTest.apply(testArrayEven);
		assertTrue(Arrays.equals(testArrayEven, solutionEven));
	}

}
