package ueb15;

public class GGT {

	public long ggTCalc(long a, long b) {

		if (a == 0 || b == 0) {

			throw new IllegalArgumentException("Value must not be 0!");
		}

		if (a < b) {

			long mem = 0;
			a = mem;
			a = b;
			b = mem;

		}
		if (b == 0) {

			return a;

		}

		return ggTCalc(b, (a % b));

	}

}
