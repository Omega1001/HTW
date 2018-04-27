package ueb15;

import org.junit.Before;

public class PalindromeRecursive2Test extends AbstractPalimdromTest{

	private PalindromeRecursive2 underTest;
	
	@Before
	public void init() {
		underTest = new PalindromeRecursive2();
	}

	@Override
	public Palindrom getUnderTest() {
		return underTest;
	}

	

}