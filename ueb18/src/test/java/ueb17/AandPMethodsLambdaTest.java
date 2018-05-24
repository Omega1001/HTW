package ueb17;

import static org.junit.Assert.*;

import org.junit.*;

public class AandPMethodsLambdaTest {
	
	@Test
	public void applyITest1() {
		assertEquals(1, AandPMethodsLambda.I.apply(1));
	}
	
	@Test
	public void applyITest2() {
		assertEquals(25, AandPMethodsLambda.I.apply(5));
	}
	
	@Test
	public void applyIITest1() {
		assertEquals(1, AandPMethodsLambda.II.apply(1));
	}
	
	@Test
	public void applyIITest2() {
		assertEquals(120, AandPMethodsLambda.II.apply(5));
	}
	
	@Test
	public void applyIIITest1() {
		assertEquals(1, AandPMethodsLambda.III.apply(1));
	}
	
	@Test
	public void applyIIITest2() {
		assertEquals(3125, AandPMethodsLambda.III.apply(5));
	}
	
	@Test
	public void applyIVTest1() {
		assertEquals(1, AandPMethodsLambda.IV.apply(1));
	}
	
	@Test
	public void applyIVTest2() {
		assertEquals(102334155, AandPMethodsLambda.IV.apply(40));
	}

}
