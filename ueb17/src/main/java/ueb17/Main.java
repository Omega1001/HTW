package ueb17;

import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

	private static final Predicate<Integer> even = (i) -> i % 2 == 0;
	private static final Predicate<Integer> odd = (i) -> i % 2 != 0;

	public static void main(String[] args) {
		int a = 1; // 1 oder 0
		applyAndPrint(1, 5, AandPMethodsAnonym.I);
		applyAndPrint(1, 5, AandPMethodsAnonym.II);
		applyAndPrint(1, 5, AandPMethodsAnonym.III);
		applyAndPrint(1, 5, AandPMethodsAnonym.IV);
		applyAndPrint(1, 5, AandPMethodsAnonym.I.conditionateInput(even));
		applyAndPrint(1, 5, AandPMethodsAnonym.II.conditionateOutput(odd));
		System.out.println(iterate(1, 5, (i) -> 2 * i));
		System.out.println(iterate(1, 5, (i) -> 0.5 * i));
		System.out.println(iterate(1, 5, (i) -> a * i * (i - 1)));
	}

	public static void applyAndPrint(int i, int j, MyFunction function) {
		// Zwischen i und j = alle x mit i < x < j -> i und j exclusive
		for (i++; i < j; i++) {
			System.out.println(function.apply(i));
		}
	}

	public static double iterate(int start, int turns, Function<Double,
			Double> f) {
		double result = start;
		for (int i = 0; i < turns; i++) {
			result = f.apply(result);
		}
		return result;
	}

}
