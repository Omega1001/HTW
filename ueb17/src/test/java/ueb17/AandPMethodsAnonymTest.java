package ueb17;

import static org.junit.Assert.*;

import org.junit.*;

public class AandPMethodsAnonymTest {
	
	@Test
	public void applyITest1() {
		assertEquals(1, AandPMethodsAnonym.I.apply(1));
	}
	
	@Test
	public void applyITest2() {
		assertEquals(25, AandPMethodsAnonym.I.apply(5));
	}
	
	@Test
	public void applyIITest1() {
		assertEquals(1, AandPMethodsAnonym.II.apply(1));
	}
	
	@Test
	public void applyIITest2() {
		assertEquals(120, AandPMethodsAnonym.II.apply(5));
	}
	
	@Test
	public void applyIIITest1() {
		assertEquals(1, AandPMethodsAnonym.III.apply(1));
	}
	
	@Test
	public void applyIIITest2() {
		assertEquals(3125, AandPMethodsAnonym.III.apply(5));
	}
	
	@Test
	public void applyIVTest1() {
		assertEquals(1, AandPMethodsAnonym.IV.apply(1));
	}
	
	@Test
	public void applyIVTest2() {
		assertEquals(102334155, AandPMethodsAnonym.IV.apply(40));
	}

}
