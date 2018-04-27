package ueb15;

import static org.junit.Assert.*;

import org.junit.Test;

public abstract class AbstractPalimdromTest {
	
	public abstract Palindrom getUnderTest();

	@Test
	public void test() {
		
		assertEquals(true, getUnderTest().isPalindrome("Otto"));
	}
	
	@Test
	public void test2() {
		assertEquals(true, getUnderTest().isPalindrome("Reittier"));
	}
	
	@Test
	public void test3() {
		assertEquals(true, getUnderTest().isPalindrome("Rentner"));
	}
	
	@Test
	public void test4() {
		assertEquals(false, getUnderTest().isPalindrome("Hund"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test5() {
		getUnderTest().isPalindrome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test6() {
		getUnderTest().isPalindrome("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test7() {
		getUnderTest().isPalindrome("0tto");
	}

}
