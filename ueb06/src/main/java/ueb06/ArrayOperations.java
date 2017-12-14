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
			"[a-zA-Z]");

	/**
	 * Methode die die Werte eines Arrays die am nächsten und am weitesten 
	 * entfernt von dessen arithmetischen Mittel liegen als Array ausgibt.
	 * <p>
	 * @param data
	 * 		Zu übergebendes Array.
	 * @return
	 * 		Array das das arithmeitschen Mittel, die dem Mittel am nähesten und 
	 * 		am entferntesten Werte enthält.
	 */
	public double[][] analyseMeasurements(double[] data) {
		if (data.length == 0) {
			throw new IllegalArgumentException(
					"Array to Analyse must contain at least 1 value");
		}
		double[][] result = new double[3][];
		result[ARITHMETIC_AVERAGE] = new double[1];
		result[FURTHEST_AWAY] = new double[2];
		result[NEAREST] = new double[3];
		for (double d : data) {
			result[ARITHMETIC_AVERAGE][0] += d;
		}
		result[ARITHMETIC_AVERAGE][0] = result[ARITHMETIC_AVERAGE][0]
				/ data.length;
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
				currentFurthest = distance;
				result[NEAREST][0] = d;
				result[NEAREST][1] = Double.NaN;
			} else if (distance == currentFurthest
					&& d != result[NEAREST][0]) {
				result[NEAREST][1] = d;
			}
		}
		if (result[FURTHEST_AWAY][1] == Double.NaN) {
			result[FURTHEST_AWAY] = new double[] {
					result[FURTHEST_AWAY][0] };
		}
		if (result[NEAREST][1] == Double.NaN) {
			result[NEAREST] = new double[] { result[NEAREST][0] };
		}
		return result;
	}

	/**
	 * Methode zum ermitteln der Anzahl an Strings aus einem Array die nur aus 
	 * Klein- und Grosbuchstaben bestehehen.
	 * <p>
	 * @return
	 * 		Anzahl der Buchstabenstrings
	 */
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

	/**
	 * Insertionsort...
	 * <p>
	 * @return
	 * 		Sortietes Array.
	 */
	public int[] insertionSort(int[] arr) {
		
		for (int i = 0; i < arr.length(); i++) {
			for ( int j = i-1; j > 0; j--) {
				if (arr[j] > arr[j+1]) {
					int mem = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = mem;
				}
			}
		}

		return arr;
	}
}
