package ueb10;

import static org.junit.Assert.*;

import org.junit.Test;

public abstract class AbstractQueueTest {

	protected abstract Object getTestObject(int no);
	protected abstract Queue ut();
	protected abstract int maxSize();
	
	@Test
	public void addLast() {
		int s = ut().size();
		Object o0 = getTestObject(0);
		Object o1 = getTestObject(1);
		
		ut().addLast(o0);
		assertEquals(s+1,ut().size());
		assertTrue(ut().get(0).equals(o0));
		ut().addLast(o1);
		assertEquals(s+2,ut().size());
		assertTrue(ut().get(0).equals(o0));
		assertTrue(ut().get(1).equals(o1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addLast2() {
		Object o0 = getTestObject(0);
		ut().addLast(o0);
		ut().addLast(o0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addLast3() {
		Object t0 = getTestObject(0);
		if(!Object.class.equals(t0.getClass())) {
			ut().addLast(new Object());
		}else {
			System.out.println("Implementation mannages Objects, test is invalid an will succedded");
			throw new IllegalArgumentException("Invalid Test"); // Throw to exit test successfull
		}
		fail("Able to contain non super types of mannaged type");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addLast4() {
		ut().addLast(null);
	}
	
	@Test(expected=IllegalStateException.class)
	public void addLast5() {
		for(int i=0;i<=maxSize();i++) {
			ut().addLast(getTestObject(i));
		}
	}
	@Test
	public void removeFirst() {
		Object o0 = getTestObject(0);
		Object o1 = getTestObject(1);
		ut().addLast(o0);
		ut().addLast(o1);
		assertEquals(ut().removeFirst(), o0);
		assertEquals(ut().removeFirst(), o1);
	}
	@Test
	public void removeFirst2() {
		
	}
	
	public void entfernePatient() {
		
	}
	
	public void get() {
		
	}
	
	public void empty() {
		
	}
	
	public void full() {
		
	}
	
	public void size() {
		
	}

}
