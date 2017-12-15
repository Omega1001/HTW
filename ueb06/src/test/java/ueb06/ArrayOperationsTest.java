package ueb06;

import static org.junit.Assert.*;
import static ueb06.ArrayOperations.*;

import java.util.Arrays;

import org.junit.Test;

public class ArrayOperationsTest {

	@Test
	public void testAnalyseMeasurements() {
		double[][] res = analyseMeasurements(new double[] { 1, 2, 5, 8,
				9 });
		assertNotNull(res);
		assertEquals("Result Array is too short", res.length, 3);
		assertArrayEquals(new double[] { 5 }, res[ARITHMETIC_AVERAGE],
				0.0);
		assertArrayEquals(new double[] { 1, 9 }, res[FURTHEST_AWAY], 0.0);
		assertArrayEquals(new double[] { 5 }, res[NEAREST], 0.0);
	}

	@Test
	public void testAnalyseMeasurements2() {
		double[][] res = analyseMeasurements(new double[] { 1, 2, 8, 9 });
		assertNotNull(res);
		assertEquals("Result Array is too short", res.length, 3);
		assertArrayEquals(new double[] { 5 }, res[ARITHMETIC_AVERAGE],
				0.0);
		assertArrayEquals(new double[] { 1, 9 }, res[FURTHEST_AWAY], 0.0);
		assertArrayEquals(new double[] { 2, 8 }, res[NEAREST], 0.0);
	}

	@Test
	public void testAnalyseMeasurements3() {
		double[][] res = analyseMeasurements(new double[] { 1, 2.5, 2.7, 8,
				9 });
		assertNotNull(res);
		assertEquals("Result Array is too short", res.length, 3);
		assertArrayEquals(new double[] { 4.64 }, res[ARITHMETIC_AVERAGE],
				0.0);
		assertArrayEquals(new double[] { 9 }, res[FURTHEST_AWAY], 0.0);
		assertArrayEquals(new double[] { 2.7 }, res[NEAREST], 0.0);
	}

	@Test
	public void testAnalyseMeasurements4() {
		double[][] res = analyseMeasurements(new double[] { -2, -1, 8,
				9 });
		assertNotNull(res);
		assertEquals("Result Array is too short", res.length, 3);
		assertArrayEquals(new double[] { 3.5 }, res[ARITHMETIC_AVERAGE],
				0.0);
		assertArrayEquals(new double[] { -2, 9 }, res[FURTHEST_AWAY], 0.0);
		assertArrayEquals(new double[] { -1, 8 }, res[NEAREST], 0.0);
	}

	@Test
	public void testAnalyseMeasurements5() {
		double[][] res = analyseMeasurements(new double[] { 0.01234,
				0.00004, 4.076, 9 });
		assertNotNull(res);
		assertEquals("Result Array is too short", res.length, 3);
		assertArrayEquals(new double[] { 3.272095 },
				res[ARITHMETIC_AVERAGE], 0.000001);
		assertArrayEquals(new double[] { 9 }, res[FURTHEST_AWAY], 0.0);
		assertArrayEquals(new double[] { 4.076 }, res[NEAREST], 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAnalyseMeasurementsX1() {
		ArrayOperations.analyseMeasurements(new double[] {});
	}

	@Test
	public void testCountLetterOnlyStrings() {
		int res = countLetterOnlyStrings(new String[] { "ASD", "asd",
				"AsD" });
		assertEquals(3, res);
	}

	@Test
	public void testCountLetterOnlyStrings2() {
		int res = countLetterOnlyStrings(new String[] { "ASD", "asd",
				"AsD", "123", "asd123", "123asd" });
		assertEquals(3, res);
	}

	@Test
	public void testCountLetterOnlyStrings3() {
		int res = countLetterOnlyStrings(new String[] {});
		assertEquals(0, res);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountLetterOnlyStrings4() {
		int res = countLetterOnlyStrings(null);
	}

	@Test
	public void testInsertionSort() {
		checkArraySorted(insertionSort(new int[] { 1, 2, 3 }));
	}

	@Test
	public void testInsertionSort2() {
		checkArraySorted(insertionSort(new int[] { 3, 2, 3 }));
	}

	@Test
	public void testInsertionSort3() {
		checkArraySorted(insertionSort(new int[] { 3, 2, 1 }));
	}

	@Test
	public void testInsertionSort4() {
		checkArraySorted(insertionSort(new int[] { 3, 2, 3, 23, 45, 235,
				6787, 43, 2, 1, 6, 78, 3, 2, 4, 5, 6, 48, 3 }));
	}

	private boolean checkArraySorted(int[] arr) {
		if (arr.length > 1) {
			for (int i = 1; i < arr.length; i++) {
				if (arr[i - 1] > arr[i]) {
					StringBuilder sb = new StringBuilder();
					sb.append("Array not correctly sorted:\r\n").append(
							"Value '").append(arr[i - 1]).append(
									"' at index '").append(i - 1).append(
											"' is bigger than Value '")
							.append(arr[i]).append("' at index '").append(
									i).append("' Full Array is \r\n")
							.append(Arrays.toString(arr));
					fail(sb.toString());
				}
			}
		}
		return true;
	}

}
