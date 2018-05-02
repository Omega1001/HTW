package ueb16.opperations;

import ueb16.NumberCruncherOpperation;

public class NumberCruncherOpperationAverage implements NumberCruncherOpperation{
	
	public float[] apply(float[] input) {
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
	}

}
