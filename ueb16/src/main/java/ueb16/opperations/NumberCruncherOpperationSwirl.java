package ueb16.opperations;

import java.util.Random;

import ueb16.NumberCruncherOpperation;

public class NumberCruncherOpperationSwirl implements NumberCruncherOpperation{
	
	private static final Random random = new Random();
	
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

}
