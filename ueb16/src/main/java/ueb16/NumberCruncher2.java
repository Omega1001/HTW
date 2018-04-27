package ueb16;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ueb16.opperations.NumberCruncherOpperationSum;

public class NumberCruncher2 {

	private static final Map<String,
			NumberCruncherOpperation> opperations = new HashMap<>();
	private static final Random random = new Random();

	static {
		opperations.put("SUM", new NumberCruncherOpperationSum());
		
	}

	private float[] numbers;

	public NumberCruncher2(int length) {
		numbers = new float[length];
		for (int i = 0; i < length; i++) {
			numbers[i] = random.nextFloat();
		}
	}

	public void crunch(String[] operations) {
		for (String operation : operations) {
			NumberCruncherOpperation o = opperations.get(operation
					.toUpperCase());
			if (o != null) {
				numbers = o.apply(numbers);
			} else {
				// TODO do something bad
			}
		}
	}

	/**
	 * @return the numbers
	 */
	public float[] getNumbers() {
		return numbers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NumberCruncher [numbers=");
		builder.append(Arrays.toString(numbers));
		builder.append("]");
		return builder.toString();
	}	

}
