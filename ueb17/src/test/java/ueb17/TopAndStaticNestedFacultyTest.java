package ueb17;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TopAndStaticNestedFacultyTest {

	private MyFunction underTest;
	private MyFunction underTestStatic;

	@Before
	public void setUp() throws Exception {
		underTest = new TopAndStaticNestedFaculty();
		underTestStatic = new TopAndStaticNestedFaculty.NestedFaculty();
	}

	@Test
	public void testApply() {
		assertEquals(1, underTest.apply(1));
		assertEquals(120, underTest.apply(5));
	}
	
	@Test
	public void testStaticApply() {
		assertEquals(1, underTestStatic.apply(1));
		assertEquals(120, underTestStatic.apply(5));
	}

}
