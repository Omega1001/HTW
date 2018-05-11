package ueb17;

/**
 * Class to apply a passed function to all natural numbers in between two passed natural numbers.
 * <p>
 * @author Adam
 * @author Fromm-Borys
 */
public class SequenceFunctionsAnonyme extends AbstractSequenceFunctions{
	
	public static void setFunctions() {
		
		setFunction("i", new MyFunction() {
			@Override
			public int apply(int i) {
				return i*i;
			}
		});
			
		setFunction("ii", new MyFunction() {
			
			@Override
			public int apply(int i) {
				
				int result = 1;
				
				for(int j = 2; j <= i; j++) {
					result = result * j;
				}
				return result;
			}
		});
			
		setFunction("iii", new MyFunction() {
			
			@Override
			public int apply(int i) {
				
				return (int) Math.pow(i, i++);
			}
		});
			
		setFunction("iv", new MyFunction() {
			
			@Override
			public int apply(int i) {
				
				int fib1 = 0;
				int fib2 = 1;
				
				for(int j = 1; j <= i; j++) {
					fib1 += fib2;
					fib2 -= fib1;
					}
					return fib1;
			}
		});
	}
}