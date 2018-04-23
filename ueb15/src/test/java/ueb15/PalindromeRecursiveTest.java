package ueb15;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeRecursiveTest {

	@Test
	public void test() {
		PalindromeRecursive tester = new PalindromeRecursive();
		assertEquals(true, tester._isPalindrom("Otto"));
	}
	
	@Test
	public void test2() {
		PalindromeRecursive tester = new PalindromeRecursive();
		assertEquals(true, tester._isPalindrom("Reittier"));
	}
	
	@Test
	public void test3() {
		PalindromeRecursive tester = new PalindromeRecursive();
		assertEquals(true, tester._isPalindrom("Rentner"));
	}
	
	@Test
	public void test4() {
		PalindromeRecursive tester = new PalindromeRecursive();
		assertEquals(false, tester._isPalindrom("Hund"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test5() {
		PalindromeRecursive tester = new PalindromeRecursive();
		tester._isPalindrom(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test6() {
		PalindromeRecursive tester = new PalindromeRecursive();
		tester._isPalindrom("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test7() {
		PalindromeRecursive tester = new PalindromeRecursive();
		tester._isPalindrom("0tto");
	}


}
