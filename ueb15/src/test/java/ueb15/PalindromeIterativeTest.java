package ueb15;

import org.junit.Before;

public class PalindromeIterativeTest extends AbstractPalimdromTest{

	private PalindromeIterative underTest;
	
	@Before
	public void init() {
		underTest = new PalindromeIterative();
	}
	
	@Override
	public Palindrom getUnderTest() {
		return underTest;
	}

}
