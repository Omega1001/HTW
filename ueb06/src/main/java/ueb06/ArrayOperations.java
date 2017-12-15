package ueb06;

import java.util.regex.Matcher;

/**
 * Klasse zum verfarbeiten von Arrrays.
 * <p>
 * @author Jannik Adam
 * @author Fromm-Borys
 * @version 1.0
 */
import java.util.regex.Pattern;

public class ArrayOperations {

	public static final int ARITHMETIC_AVERAGE = 0;
	public static final int FURTHEST_AWAY = 1;
	public static final int NEAREST = 2;

	private static final Pattern TRUE_DIDGETS_ONLY = Pattern.compile(
			"[a-zA-Z]+");

	/**
	 * Calculates the arithemtic average and determines the value/s closest and
	 * furthest to it.
	 * <p>
	 * 
	 * @param data
	 * 		An array.
	 * @return
	 * 		An aray consisting of the arithmetic average as well as the
	 * 		value/s clostest and furthest to it.
	 */
	public static double[][] analyseMeasurements(double[] data) {
		if (data == null || data.length == 0) {
			throw new IllegalArgumentException(
					"Array to Analyse must contain at least 1 value");
		}
		double[][] result = new double[3][];
		result[ARITHMETIC_AVERAGE] = new double[1];
		result[FURTHEST_AWAY] = new double[2];
		result[NEAREST] = new double[2];
		for (double d : data) {
			result[ARITHMETIC_AVERAGE][0] += (d / ((double) data.length));
		}

		double currentFurthest = 0d;
		double currentClosest = Double.MAX_VALUE;
		for (double d : data) {
			double distance = Math.abs(result[ARITHMETIC_AVERAGE][0] - d);
			if (distance > currentFurthest) {
				currentFurthest = distance;
				result[FURTHEST_AWAY][0] = d;
				result[FURTHEST_AWAY][1] = Double.NaN;
			} else if (distance == currentFurthest
					&& d != result[FURTHEST_AWAY][0]) {
				result[FURTHEST_AWAY][1] = d;
			}
			if (distance < currentClosest) {
				currentClosest = distance;
				result[NEAREST][0] = d;
				result[NEAREST][1] = Double.NaN;
			} else if (distance == currentClosest
					&& d != result[NEAREST][0]) {
				result[NEAREST][1] = d;
			}
		}
		if (Double.isNaN(result[FURTHEST_AWAY][1])) {
			result[FURTHEST_AWAY] = new double[] {
					result[FURTHEST_AWAY][0] };
		}
		if (Double.isNaN(result[NEAREST][1])) {
			result[NEAREST] = new double[] { result[NEAREST][0] };
		}
		return result;
	}

	/**
	 * Counts the number of strings of an array solely consisting of letters.
	 * <p>
	 * 
	 * @return 
	 * 		Number of "letter-strings"
	 */
	public static int countLetterOnlyStrings(String[] strings) {
		if (strings == null) {
			throw new IllegalArgumentException(
					"Array must nut be undefined");
		}
		int result = 0;
		for (String s : strings) {
			Matcher m = TRUE_DIDGETS_ONLY.matcher(s);
			if (m.matches()) {
				result++;
			}
		}
		return result;
	}

	/**
	 * Performes insertion sort on a passed array
	 * <p>
	 * 
	 * @return 
	 * 		Sorted array
	 */
	public static int[] insertionSort(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[j + 1]) {
					int mem = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = mem;
				}
			}
		}
		return arr;
	}
}
