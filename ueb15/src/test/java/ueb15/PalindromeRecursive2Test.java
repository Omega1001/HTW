package ueb15;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeRecursive2Test {

	@Test
	public void test() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		assertEquals(true, tester.isPalindrome("Otto"));
	}

	@Test
	public void test2() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		assertEquals(true, tester.isPalindrome("Reittier"));
	}

	@Test
	public void test3() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		assertEquals(true, tester.isPalindrome("Rentner"));
	}

	@Test
	public void test4() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		assertEquals(false, tester.isPalindrome("Hund"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test5() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		tester.isPalindrome(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test6() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		tester.isPalindrome("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test7() {
		PalindromeRecursive2 tester = new PalindromeRecursive2();
		tester.isPalindrome("0tto");
	}

}