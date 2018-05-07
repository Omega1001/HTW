package ueb16;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NumberCruncher {

	private static final Map<String,
			NumberCruncherOpperation> opperations = new HashMap<>();
	private static final Random random = new Random();

	public static void addDefaultOpperations(){
		setOpperation("SUM", new NumberCruncherOpperation() {

			@Override
			public float[] apply(float[] input) {
				for (int i = 1; i < input.length; i++) {
					input[i] = input[i-1] + input[i];
				}
				return input;
			}
		});
		setOpperation("DIVIDE", new NumberCruncherOpperation() {

			@Override
			public float[] apply(float[] input) {
				int [] order = new int[input.length];
				Arrays.fill(order, -1);
				for(int i=0;i<order.length;i++) {
					for(int i2=0;i2<input.length;i2++) {
						if(order[i] == -1 || input[i2]<input[order[i]]) {
							if(i==0 || input[i2]>input[order[i-1]]) {
								order[i] = i2;
							}
						}
					}
				}
				
				for(int i=0;i<order.length/2;i++) {
					input[order[order.length-1]] = input[order[order.length-1]] / input[order[i]];
				}
				if(order.length%2 == 1) {
					input[order[(order.length/2)+1]] = 1;
				}
				
				return input;
			}
		});
		setOpperation("SUBTRACT", new NumberCruncherOpperation() {

			@Override
			public float[] apply(float[] input) {
				for (int i = 1; i < input.length; i++) {
					input[i] = input[i-1] - input[i];
				}
				return input;
			}
		});
		setOpperation("SWIRL", new NumberCruncherOpperation() {

			@Override
			public float[] apply(float[] input) {
				for (int n = 0; n < input.length; n++) {
					int first = random.nextInt(input.length);
					int second = random.nextInt(input.length);
					float buffer = input[first];
					input[first] = input[second];
					input[second] = buffer;
				}
				return input;
			}
		});
		setOpperation("AVERAGE", (input) -> {
			float avg = 0;
			int bigest = 0;
			for (int i = 0; i < input.length; i++) {
				if (input[i] > input[bigest]) {
					bigest = i;
				}
				avg += input[i];
			}
			input[bigest] = avg/input.length;
			return input;
		});
	}
	
	public static void setOpperation(String opperationName, NumberCruncherOpperation op) {
		opperations.put(opperationName, op);
	}

	private float[] numbers;

	public NumberCruncher(int length) {
		numbers = new float[length];
		for (int i = 0; i < length; i++) {
			numbers[i] = random.nextFloat();
		}
	}

	public NumberCruncher (float[] values) {
		this.numbers = values;
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
