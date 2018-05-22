package ueb17;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AandPMethodsAnonymTest {
	
	@Test
	void applyITest1() {
		assertEquals(1, AandPMethodsAnonym.I.apply(1));
	}
	
	@Test
	void applyITest2() {
		assertEquals(25, AandPMethodsAnonym.I.apply(5));
	}
	
	@Test
	void applyIITest1() {
		assertEquals(1, AandPMethodsAnonym.II.apply(1));
	}
	
	@Test
	void applyIITest2() {
		assertEquals(120, AandPMethodsAnonym.II.apply(5));
	}
	
	@Test
	void applyIIITest1() {
		assertEquals(1, AandPMethodsAnonym.III.apply(1));
	}
	
	@Test
	void applyIIITest2() {
		assertEquals(3125, AandPMethodsAnonym.III.apply(5));
	}
	
	@Test
	void applyIVTest1() {
		assertEquals(1, AandPMethodsAnonym.IV.apply(1));
	}
	
	@Test
	void applyIVTest2() {
		assertEquals(102334155, AandPMethodsAnonym.IV.apply(40));
	}

}
