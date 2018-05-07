package ueb16;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NumberCruncherTest {

	private NumberCruncher underTest;
	private float[] testValues = new float[]
	// Test Values
	{ 1f, 2f, 5.5f, 9f, 9.99999f };

	@BeforeClass
	public static void sInit() {
		NumberCruncher.addDefaultOpperations();
	}

	@Before
	public void init() {
		underTest = new NumberCruncher(testValues);
	}

	@Test
	public void NumberCruncherTest1() {
		underTest = new NumberCruncher(5);
		assertNotNull(underTest.getNumbers());
		assertEquals(5, underTest.getNumbers().length);
	}

	@Test
	public void NumberCruncherSumTest1() {
		underTest.crunch("SUM");
		arrayEquals(underTest.getNumbers(), 1f, 3f, 8.5f, 17.5f,
				27.49999f);
	}

	@Test
	public void NumberCruncherDivideTest1() {
		underTest.crunch("DIVIDE");
		fail("Unfinished Test");
	}

	@Test
	public void NumberCruncherSubtractTest1() {
		underTest.crunch("SUBTRACT");
		fail("Unfinished Test");
	}

	@Test
	public void NumberCruncherAverageTest1() {
		underTest.crunch("AVERAGE");
		fail("Unfinished Test");
	}

	@Test
	public void NumberCruncherSwirlTest1() {
		underTest.crunch("SWIRL");
		fail("Unfinished Test");
	}

	private void arrayEquals(float[] numbers, float... expected) {
		if ((numbers == null && expected.length == 0)
				|| numbers.length == expected.length) {
			for (int i = 0; i < numbers.length; i++) {
				assertEquals(expected[i], numbers[i], 1E-5);
			}
		} else {
			fail("Array Length differs");
		}
	}
}
