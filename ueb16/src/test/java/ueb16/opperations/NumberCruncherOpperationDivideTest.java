package ueb16.opperations;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class NumberCruncherOpperationDivideTest {

	private NumberCruncherOpperationDivide underTest;

	private float [] testArrayOdd = {1, 2, 3, 4, 5};
	private float [] testArrayEven = {1, 2, 3, 4, 5, 6};
	private float [] solutionOdd = {1, 3, 3/3, 4/3, 5/1};
	private float [] solutionEven = {1, 2, 3, 4/3, 5/2, 6/1};	
	
	@Test
	public void applyTest1() {
		underTest = new NumberCruncherOpperationDivide();
		underTest.apply(testArrayOdd);
		assertTrue(Arrays.equals(testArrayOdd, solutionOdd));
	}
	
	@Test
	public void applyTest2() {
		underTest = new NumberCruncherOpperationDivide();
		underTest.apply(testArrayEven);
		assertTrue(Arrays.equals(testArrayEven, solutionEven));
	}

}
