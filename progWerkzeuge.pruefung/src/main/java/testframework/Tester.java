package testframework;

import testframework.annotations.After;
import testframework.annotations.Before;
import testframework.annotations.TestMethod;
/**
 * This class is a dummy Test suit.<br>
 * This class provides methods that effectively do nothing.<br>
 * 
 * @author jannik
 *
 */
public class Tester {
	/**
	 * This dummy method is called before any tests are executed.<br>
	 */
	@Before
	public void setup() {
		System.out.println("Yay, setup was called");
	}
	
	/**
	 * This dummy method is a dummy test.<br>
	 * It is supposed to be called after all setup methods have exited
	 */
	@TestMethod
	public void testMethod1() {
		System.out.println("\t\tfirst interaction with class under test");
		ClassUnderTest cut = new ClassUnderTest();
		TestRunner.assertEquals(true, cut.doit());
	}

	/**
	 * This dummy method is a dummy test.<br>
	 * It is supposed to be called after all setup methods have exited
	 */
	@TestMethod
	public void testMethod2() {
		System.out.println("\t\tsecond interaction with class under test");
		ClassUnderTest cut = new ClassUnderTest();
		TestRunner.assertEquals(false, cut.doit());
	}
	
	/**
	 * This dummy method is called after all tests are completed.<br>
	 */
	@After
	public void tearDown() {
		System.out.println("Oh now! I'm going down!");
	}
}
