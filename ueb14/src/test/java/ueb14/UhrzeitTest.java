package ueb14;

import static org.junit.Assert.*;

import org.junit.Test;

public class UhrzeitTest {

	@Test
	public void test() {
		Uhrzeit underTest = new Uhrzeit(12, 0);
		assertEquals("12:00", underTest.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test2() {
		new Uhrzeit(-1, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test3() {
		new Uhrzeit(24, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test4() {
		new Uhrzeit(12, -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test5() {
		new Uhrzeit(-1, 60);
	}

}
