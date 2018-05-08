package ueb16.opperations;

import java.util.Arrays;

import ueb16.NumberCruncherOpperation;

public class NumberCruncherOpperationDivide implements
		NumberCruncherOpperation {

	@Override
	public float[] apply(float[] input) {
		int[] order = new int[input.length];
		Arrays.fill(order, -1);
		for (int i = 0; i < order.length; i++) {
			for (int i2 = 0; i2 < input.length; i2++) {
				if (order[i] == -1 || input[i2] < input[order[i]]) {
					if (i == 0 || input[i2] > input[order[i - 1]]) {
						order[i] = i2;
					}
				}
			}
		}

		for (int i = 0; i < order.length / 2; i++) {
			input[order[order.length - 1 - i]] = input[order[order.length
					- 1 - i]] / input[order[i]];
		}

		// Aufgabenstellung unklar : n/2 wird in Java abgerundet, heisst
		// dass das bei ungeraden laengen der mittlere Wert 1 wird?
		// Interpretiere als 'nein' da so bei wiederholter Ausfuerung
		// lauter 1sen erzeugt werden

		// if(order.length%2 == 1) {
		// input[order[(order.length/2)]] = 1;
		// }

		return input;
	}

}
