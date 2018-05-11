package ueb17;

/**
 * Class to apply a passed function to all natural numbers in between two passed natural numbers.
 * <p>
 * @author Adam
 * @author Fromm-Borys
 */
public class SequenceFunctionsLambda extends AbstractSequenceFunctions{

	public static void setFunctions() {
		
		setFunction("i", i -> i*i);
		
		setFunction("ii", i -> {
			
			int result = 1;
			
			for(int j = 2; j <= i; j++) {
				result = result * j;
			}
			return result;
		});
		
		setFunction("iii", i -> (int) Math.pow(i, i+1));
		
		setFunction("iv", i -> {
			
			int fib1 = 0;
			int fib2 = 1;
			
			for(int j = 1; j <= i; j++) {
				fib1 += fib2;
				fib2 -= fib1;
			}
			return fib1;
		});
	}
}
