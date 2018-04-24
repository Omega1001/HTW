package ueb15;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeIterativeTest {

	@Test
	public void test() {
		PalindromeIterative tester = new PalindromeIterative();
		assertEquals(true, tester.isPalindrome("Otto"));
	}
	
	@Test
	public void test2() {
		PalindromeIterative tester = new PalindromeIterative();
		assertEquals(true, tester.isPalindrome("Reittier"));
	}
	
	@Test
	public void test3() {
		PalindromeIterative tester = new PalindromeIterative();
		assertEquals(true, tester.isPalindrome("Rentner"));
	}
	
	@Test
	public void test4() {
		PalindromeIterative tester = new PalindromeIterative();
		assertEquals(false, tester.isPalindrome("Hund"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test5() {
		PalindromeIterative tester = new PalindromeIterative();
		tester.isPalindrome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test6() {
		PalindromeIterative tester = new PalindromeIterative();
		tester.isPalindrome("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test7() {
		PalindromeIterative tester = new PalindromeIterative();
		tester.isPalindrome("0tto");
	}


}
