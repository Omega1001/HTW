package ueb21;

import java.util.Random;

public class MyProducer implements java.util.function.Supplier<Integer> {

	private static final Random rng = new Random();


	@Override
	public Integer get() {
		return rng.nextInt(1001);
	}
	
}
