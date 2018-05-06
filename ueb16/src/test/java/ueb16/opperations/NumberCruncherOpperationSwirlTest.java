package ueb16.opperations;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberCruncherOpperationSwirlTest {

	private NumberCruncherOpperationSwirl underTest;

	private float [] testArrayOdd = {1, 2, 3, 4, 5};
	private float [] testArrayValues = {1, 2, 3, 4, 5};
	
	@Test
	public void applyTest1() {
		underTest = new NumberCruncherOpperationSwirl();
		underTest.apply(testArrayOdd);
		assertTrue(compare(testArrayOdd, testArrayValues));
	}

	private boolean compare(float[] a, float[] b) {
		for(int i=0; i < a.length; i++) {
			for(int j=0; a[i] != b[j] && j < a.length; j++) {
				if(j == a.length-1 && a[i] != b[j]) {
					return false;
				}
			}
		}
		return true;
	}
}
