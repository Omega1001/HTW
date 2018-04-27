package ueb15;

import org.junit.Before;

public class PalindromeRecursiveTest extends AbstractPalimdromTest{

	private PalindromeRecursive underTest;
	
	@Before
	public void init() {
		underTest = new PalindromeRecursive();
	}

	@Override
	public Palindrom getUnderTest() {
		return underTest;
	}

}
