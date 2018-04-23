package ueb15;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeRecursive2Test {

	@Test
	public void test() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		assertEquals(true, tester._isPalindrom("Otto"));
	}
	
	@Test
	public void test2() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		assertEquals(true, tester._isPalindrom("Reittier"));
	}
	
	@Test
	public void test3() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		assertEquals(true, tester._isPalindrom("Rentner"));
	}
	
	@Test
	public void test4() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		assertEquals(false, tester._isPalindrom("Hund"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test5() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		tester._isPalindrom(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test6() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		tester._isPalindrom("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test7() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		tester._isPalindrom("0tto");
	}


}