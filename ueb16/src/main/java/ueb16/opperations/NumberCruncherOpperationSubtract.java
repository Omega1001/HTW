package ueb16.opperations;

import ueb16.NumberCruncherOpperation;

public class NumberCruncherOpperationSubtract implements NumberCruncherOpperation{
	
	@Override
	public float[] apply(float[] input) {
		for (int i = 1; i < input.length; i = i + 1) {
			input[i] = input[i-1] - input[i];
		}
		return input;
	}
}
