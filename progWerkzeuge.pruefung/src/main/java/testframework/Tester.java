package testframework;

import testframework.annotations.After;
import testframework.annotations.Before;
import testframework.annotations.TestMethod;

public class Tester {

	@Before
	public void setup() {
		System.out.println("Yay, setup was called");
	}
	
	@TestMethod
	public void testMethod1() {
		System.out.println("\t\tfirst interaction with class under test");
		ClassUnderTest cut = new ClassUnderTest();
		TestRunner.assertEquals(true, cut.doit());
	}

	@TestMethod
	public void testMethod2() {
		System.out.println("\t\tsecond interaction with class under test");
		ClassUnderTest cut = new ClassUnderTest();
		TestRunner.assertEquals(false, cut.doit());
	}
	
	@After
	public void tearDown() {
		System.out.println("Oh now! I'm going down!");
	}
}
