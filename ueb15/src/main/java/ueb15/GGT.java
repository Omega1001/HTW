package ueb15;

public class GGT {

	public long ggTCalc(long a, long b) {

		if (a < b) {

			long aMem = a;
			a = b;
			b = aMem;
			
		}

		return ggTCalculate(a,b);

	}

	private static long ggTCalculate (long a, long b) {
		
		if (b == 0) {

			return Math.abs(a);

		}

		return ggTCalculate(b, (a % b));
		
	}
	
}
