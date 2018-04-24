package ueb15;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeRecursiveTest {

	@Test
	public void test() {
		Palindrom tester = new PalindromeRecursive();
		assertEquals(true, tester.isPalindrome("Otto"));
	}

	@Test
	public void test2() {
		PalindromeRecursive tester = new PalindromeRecursive();
		assertEquals(true, tester.isPalindrome("Reittier"));
	}

	@Test
	public void test3() {
		PalindromeRecursive tester = new PalindromeRecursive();
		assertEquals(true, tester.isPalindrome("Rentner"));
	}

	@Test
	public void test4() {
		PalindromeRecursive tester = new PalindromeRecursive();
		assertEquals(false, tester.isPalindrome("Hund"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test5() {
		PalindromeRecursive tester = new PalindromeRecursive();
		tester.isPalindrome(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test6() {
		PalindromeRecursive tester = new PalindromeRecursive();
		tester.isPalindrome("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test7() {
		PalindromeRecursive tester = new PalindromeRecursive();
		tester.isPalindrome("0tto");
	}

}
