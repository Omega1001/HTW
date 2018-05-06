package ueb16.opperations;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class NumberCruncherOpperationSubtractTest {

	private NumberCruncherOpperationSubtract underTest;

	private float [] testArrayOdd = {1, 2, 3, 4, 5};
	private float [] testArrayEven = {1, 2, 3, 4, 5, 6};
	private float [] solutionOdd = {1, -1, -4, -8, -13};
	private float [] solutionEven = {1, -1, -4, -8, -13, -19};	
	
	@Test
	public void applyTest1() {
		underTest = new NumberCruncherOpperationSubtract();
		underTest.apply(testArrayOdd);
		assertTrue(Arrays.equals(testArrayOdd, solutionOdd));
	}
	
	@Test
	public void applyTest2() {
		underTest = new NumberCruncherOpperationSubtract();
		underTest.apply(testArrayEven);
		assertTrue(Arrays.equals(testArrayEven, solutionEven));
	}

}
