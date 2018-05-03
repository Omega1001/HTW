package ueb16;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public abstract class AbstractQueueTest<T> {

	protected abstract T getTestObject(int no);

	protected abstract AbstractQueue<T> ut();

	protected abstract int maxSize();

	@Test
	public void addLast() {
		int s = ut().size();
		T o0 = getTestObject(0);
		T o1 = getTestObject(1);

		ut().addLast(o0);
		assertEquals(s + 1, ut().size());
		assertTrue(ut().get(0).equals(o0));
		ut().addLast(o1);
		assertEquals(s + 2, ut().size());
		assertTrue(ut().get(0).equals(o0));
		assertTrue(ut().get(1).equals(o1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addLast2() {
		T o0 = getTestObject(0);
		ut().addLast(o0);
		ut().addLast(o0);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = IllegalArgumentException.class)
	public void addLast3() {
		Object t0 = getTestObject(0);
		if (!Object.class.equals(t0.getClass())) {
			((Queue<Object>) ut()).addLast(new Object());
		} else {
			System.out.println(
					"Implementation mannages Objects, test is invalid an will succedded");
			throw new IllegalArgumentException("Invalid Test"); // Throw to
																// exit
																// test
																// successfull
		}
		fail("Able to contain non super types of mannaged type");
	}

	@Test(expected = IllegalArgumentException.class)
	public void addLast4() {
		ut().addLast(null);
	}

	@Test(expected = IllegalStateException.class)
	public void addLast5() {
		for (int i = 0; i <= maxSize(); i++) {
			ut().addLast(getTestObject(i));
		}
	}

	@Test
	public void removeFirst() {
		T o0 = getTestObject(0);
		T o1 = getTestObject(1);
		ut().addLast(o0);
		ut().addLast(o1);
		assertEquals(ut().removeFirst(), o0);
		assertEquals(ut().removeFirst(), o1);
	}

	@Test(expected = IllegalStateException.class)
	public void removeFirst2() {
		ut().removeFirst();
	}

	@Test
	public void remove() {
		T o0 = getTestObject(0);
		T o1 = getTestObject(1);
		ut().addLast(o0);
		assertEquals(o0, ut().remove(o0));
		assertNull(ut().remove(o1));
	}

	@Test
	public void get() {
		T o0 = getTestObject(0);
		T o1 = getTestObject(1);
		ut().addLast(o0);
		ut().addLast(o1);
		assertEquals(o1, ut().get(1));
		assertEquals(o0, ut().get(0));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void get2() {
		ut().get(0);
	}

	@Test
	public void empty() {
		assertTrue(ut().empty());
		T o0 = getTestObject(0);
		ut().addLast(o0);
		assertFalse(ut().empty());
	}

	@Test
	public void full() {
		assertTrue(ut().empty());
		for (int i = 0; i < maxSize(); i++) {
			ut().addLast(getTestObject(i));
		}
		assertTrue(ut().full());
	}

	@Test
	public void size() {
		assertTrue(ut().empty());
		for (int i = 0; i < maxSize(); i++) {
			assertEquals(i, ut().size());
			ut().addLast(getTestObject(i));
			assertEquals(i + 1, ut().size());
		}
	}
	
	@Test
	public void itterator() {
		T tob1 = getTestObject(1);
		T tob2 = getTestObject(2);
		T tob3 = getTestObject(3);
		ut().addLast(tob2);
		ut().addLast(tob3);
		ut().addLast(tob1);
		Iterator<T> it = ut().iterator();
		assertTrue(it.hasNext());
		assertEquals(tob2, it.next());
		assertTrue(it.hasNext());
		assertEquals(tob3, it.next());
		assertTrue(it.hasNext());
		assertEquals(tob1, it.next());
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("Got next element after has next returned false");
		}catch(NoSuchElementException e) {
			//Do nothing
		}
	}

}
