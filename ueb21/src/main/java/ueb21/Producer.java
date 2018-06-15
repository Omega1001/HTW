package ueb21;

import java.util.Random;

public class Producer {

	public static Integer produce() {
		
		Random rng = new Random();
		
		return rng.nextInt(1001);
	}
	
}
