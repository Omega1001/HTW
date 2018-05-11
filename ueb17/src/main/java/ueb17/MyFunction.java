package ueb17;

import java.util.function.Predicate;

/**
 * Interface
 * <p>
 * 
 * @author Adam
 * @author Fromm-Borys
 */
public interface MyFunction {

	/**
	 * Method to apply a function to a natural number.
	 * 
	 * @param i
	 *            the natural number
	 */
	public int apply(int i);

	public default MyFunction conditionateInput(Predicate<Integer> p) {
		return (i) -> p.test(i) ? apply(i) : 0;
	}

	public default MyFunction conditionateOutput(Predicate<Integer> p) {
		return (i) -> {
			int res = apply(i);
			return p.test(res) ? res : 0;
		};
	}
}
