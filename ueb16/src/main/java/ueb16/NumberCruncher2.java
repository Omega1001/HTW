package ueb16;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ueb16.opperations.NumberCruncherOpperationAverage;
import ueb16.opperations.NumberCruncherOpperationDivide;
import ueb16.opperations.NumberCruncherOpperationSubtract;
import ueb16.opperations.NumberCruncherOpperationSum;
import ueb16.opperations.NumberCruncherOpperationSwirl;

public class NumberCruncher2 {

	private static final Map<String,
			NumberCruncherOpperation> opperations = new HashMap<>();
	private static final Random random = new Random();

	public static void addDefaultOpperations() {
		setOpperation("SUM", new NumberCruncherOpperationSum());
		setOpperation("DIVIDE", new NumberCruncherOpperationDivide());
		setOpperation("SUBTRACT", new NumberCruncherOpperationSubtract());
		setOpperation("SWIRL", new NumberCruncherOpperationSwirl());
		setOpperation("AVERAGE", new NumberCruncherOpperationAverage());
	}

	public static void setOpperation(String opperationName,
			NumberCruncherOpperation op) {
		opperations.put(opperationName, op);
	}

	private float[] numbers;

	public NumberCruncher2(int length) {
		if(length < 1) {
			throw new IllegalArgumentException("Must have at least one value");
		}
		numbers = new float[length];
		for (int i = 0; i < length; i++) {
			numbers[i] = random.nextFloat();
		}
	}

	public void crunch(String ... operations) {
		for (String operation : operations) {
			if(operation == null) {
				throw new IllegalArgumentException("Operation must not be null!");
			}
			NumberCruncherOpperation o = opperations.get(operation
					.toUpperCase());
			if (o != null) {
				numbers = o.apply(numbers);
			} else {
				throw new IllegalArgumentException("Invalid operation!");
			}
		}
	}

	/**
	 * @return the numbers
	 */
	public float[] getNumbers() {
		return numbers;
	}

	/*
	 * (non-Javadoc)
	 * 
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
