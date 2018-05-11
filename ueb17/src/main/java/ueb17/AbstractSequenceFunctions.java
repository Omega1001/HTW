package ueb17;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to apply a passed function to all natural numbers in between two passed natural numbers.
 * <p>
 * @author Adam
 * @author Fromm-Borys
 */
public abstract class AbstractSequenceFunctions{
	
	protected static final Map<String, MyFunction> functions = new HashMap<>();
	
	public static void setFunction(String functionName, MyFunction function) {
		functions.put(functionName, function);
	}
	
	/**
	 * Method to apply a passed function to all natural numbers in between two passed natural numbers.
	 * 
	 * @param i
	 * 		one of the numbers determining the domain
	 * @param j
	 * 		one of the numbers determining the domain
	 * @param function
	 * 		the function to be applied to the sequence
	 */
	 public void applyAndPrint(int i, int j, String function) {
		if(i <= 0 || j <= 0) {
			throw new IllegalArgumentException ("i and j must be natural numbers!");
		}
		if(null == function) {
			throw new IllegalArgumentException ("Function must not be null!");
		}
		MyFunction f = functions.get(function);
		for(; i >= j; i++) {
			System.out.println(f.apply(i));
		}
	}
}
