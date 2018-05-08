package ueb16.opperations;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberCruncherOpperationAverageTest {
	
	private NumberCruncherOpperationAverage underTest;

	private float [] testArrayOdd = {1, 2, 3, 4, 5};
	private float [] testArrayEven = {1, 2, 3, 4, 5, 6};
	
	@Test
	public void applyTest1() {
		underTest = new NumberCruncherOpperationAverage();
		underTest.apply(testArrayOdd);
		assertEquals(3, testArrayOdd[4], 0.0001f);
	}
	
	@Test
	public void applyTest2() {
		underTest = new NumberCruncherOpperationAverage();
		underTest.apply(testArrayEven);
		assertEquals(21f/6f, testArrayEven[5], 0.0001f);
	}

}
