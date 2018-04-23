package ueb15;

import static org.junit.Assert.*;

import org.junit.Test;

public class GGTTest {
	
	@Test
	public void test() {
		GGT tester = new GGT();
		assertEquals(1, tester.ggTCalc(1,1));
	}
	
	@Test
	public void test2() {
		GGT tester = new GGT();
		assertEquals(1, tester.ggTCalc(-1,1));
	}
	
	@Test
	public void test3() {
		GGT tester = new GGT();
		assertEquals(1, tester.ggTCalc(1,-1));
	}
	
	@Test
	public void test4() {
		GGT tester = new GGT();
		assertEquals(1, tester.ggTCalc(-1,-1));
	}
	
	@Test
	public void test5() {
		GGT tester = new GGT();
		assertEquals(252, tester.ggTCalc(3780,3528));
	}
	
	@Test
	public void test6() {
		GGT tester = new GGT();
		assertEquals(252, tester.ggTCalc(3528,3780));
	}
	
	@Test
	public void test7() {
		GGT tester = new GGT();
		assertEquals(2, tester.ggTCalc(2,0));
	}
	
	@Test
	public void test8() {
		GGT tester = new GGT();
		assertEquals(2, tester.ggTCalc(0,2));
	}
	
	@Test
	public void test9() {
		GGT tester = new GGT();
		assertEquals(0, tester.ggTCalc(0,0));
	}

}
