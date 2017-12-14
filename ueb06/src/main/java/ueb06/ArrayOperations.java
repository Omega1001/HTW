package ueb06;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayOperations {

	public static final int ARITHMETIC_AVERAGE = 0;
	public static final int FURTHEST_AWAY = 1;
	public static final int NEAREST = 2;

	private static final Pattern TRUE_DIDGETS_ONLY = Pattern.compile(
			"[a-zA-Z]");

	public double[] analyseMeasurements(double[] data) {
		double[] result = new double[3];
		Arrays.fill(result, 0.0);
		for (double d : data) {
			result[ARITHMETIC_AVERAGE] += d;
		}
		result[ARITHMETIC_AVERAGE] = result[ARITHMETIC_AVERAGE]
				/ data.length;
		double currentFurthest = 0d;
		double currentClosest = Double.MAX_VALUE;

		for (double d : data) {
			double distance = Math.abs(result[ARITHMETIC_AVERAGE] - d);
			if (distance > currentFurthest) {
				currentFurthest = distance;
				result[FURTHEST_AWAY] = d;
			}
			if (distance < currentClosest) {
				currentFurthest = distance;
				result[NEAREST] = d;
			}
		}
		return result;
	}

	public int countLetterOnlyStrings(String[] strings) {
		int result = 0;
		for (String s : strings) {
			Matcher m = TRUE_DIDGETS_ONLY.matcher(s);
			if (m.matches()) {
				result++;
			}
		}
		return result;
	}

	public int[] insertionSort(int[] arr) {
		
		return arr;
	}
}
