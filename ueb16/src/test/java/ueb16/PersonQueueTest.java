package ueb16;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonQueueTest extends AbstractQueueTest<Person> {

	private PersonenQueue underTest;

	@Before
	public void inti() {
		underTest = new PersonenQueue(maxSize());
	}

	@Override
	protected Person getTestObject(int no) {
		return new Person("fn" + no, "sn" + no);
	}

	@Override
	protected PersonenQueue ut() {
		return underTest;
	}

	@Override
	protected int maxSize() {
		return 5;
	}

	@Test
	public void testSmallest() {
		assertTrue(ut().empty());
		Person smalest = getTestObject(0);
		ut().addLast(getTestObject(1));
		ut().addLast(smalest);
		ut().addLast(getTestObject(2));
		assertEquals(smalest.getFirstName(), ut().smallest());
	}

}
