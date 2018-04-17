package ueb14;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonTest {

	@Test
	public void testPerson() {
		Person underTest = new Person("test", "name");
		assertEquals("test", underTest.getVorname());
		assertEquals("name", underTest.getNachnahme());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPerson2() {
		 new Person(null, "name");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPerson3() {
		new Person("test", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPerson4() {
		 new Person("", "name");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPerson5() {
		new Person("test", "");
	}

}
