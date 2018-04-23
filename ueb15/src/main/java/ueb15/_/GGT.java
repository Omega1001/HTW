package ueb15._;

public class GGT {
	
	public long ggTBestimmen (long a, long b) {
		
		if (a < b) {
			
			throw new IllegalArgumentException ("A must be bigger than B!");
			
		}
		
		if (a >= 0) {
			
			long bMem = b;
			
			b = a % b;
			a = bMem;
			
			ggTBestimmen (a, b);
			
		}
		
		return a;
		
	}
	
}
