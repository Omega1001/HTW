package ueb17;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public abstract class AbstractSequenceFunctionsTest {

	public abstract AbstractSequenceFunctions getUnderTest();
	
	@Test
	public void test() {
		//assertEquals(0, getUnderTest().applyAndPrint(0, 0, null)); void...
	}
}