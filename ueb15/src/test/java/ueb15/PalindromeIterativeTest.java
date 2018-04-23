package ueb15;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeIterativeTest {

	@Test
	public void test() {
		PalindromeIterative tester = new PalindromeIterative();
		assertEquals(true, tester._isPalindrom("Otto"));
	}
	
	@Test
	public void test2() {
		PalindromeIterative tester = new PalindromeIterative();
		assertEquals(true, tester._isPalindrom("Reittier"));
	}
	
	@Test
	public void test3() {
		PalindromeIterative tester = new PalindromeIterative();
		assertEquals(true, tester._isPalindrom("Rentner"));
	}
	
	@Test
	public void test4() {
		PalindromeIterative tester = new PalindromeIterative();
		assertEquals(false, tester._isPalindrom("Hund"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test5() {
		PalindromeIterative tester = new PalindromeIterative();
		tester._isPalindrom(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test6() {
		PalindromeIterative tester = new PalindromeIterative();
		tester._isPalindrom("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test7() {
		PalindromeIterative tester = new PalindromeIterative();
		tester._isPalindrom("0tto");
	}


}
