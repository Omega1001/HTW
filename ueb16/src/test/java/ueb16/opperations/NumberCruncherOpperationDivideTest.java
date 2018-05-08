package ueb16.opperations;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberCruncherOpperationDivideTest {

	private NumberCruncherOpperationDivide underTest;

	private float [] testArrayOdd = {1, 2, 3, 4, 5};
	private float [] testArrayEven = {1, 2, 3, 4, 5, 6};
	private float [] solutionOdd = {1, 2, 3, 2, 5};
	private float [] solutionEven = {1, 2, 3, 4f/3f, 5f/2f, 6};	
	
	@Test
	public void applyTest1() {
		underTest = new NumberCruncherOpperationDivide();
		underTest.apply(testArrayOdd);
		assertArrayEquals(solutionOdd, testArrayOdd,0.001f);
	}
	
	@Test
	public void applyTest2() {
		underTest = new NumberCruncherOpperationDivide();
		underTest.apply(testArrayEven);
		assertArrayEquals(solutionEven, testArrayEven,0.001f);
	}

}
