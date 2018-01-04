package ueb08;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class PatientenWarteschlangeTest {
	
	private PatientenWarteschlange underTest;
	private static final Patient TEST_P1 = new Patient(1234, "Test, P1");
	private static final Patient TEST_P2 = new Patient(5678, "Test, P2");
	private static final Patient TEST_P3 = new Patient(9012, "Test, P3");
	private static final Patient TEST_P4 = new Patient(3456, "Test, P4");

	@Before
	public void setUp() throws Exception {
		underTest = new PatientenWarteschlange(3);
		underTest.neuerPatient(TEST_P1);
		underTest.neuerPatient(TEST_P2);
	}

	@Test
	public void testPatientenWarteschlange() {
		new PatientenWarteschlange(1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPatientenWarteschlange2() {
		new PatientenWarteschlange(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPatientenWarteschlange3() {
		new PatientenWarteschlange(-3);
	}
	
	@Test
	public void testNeuerPatientPatient() {
		assertTrue("Test Patient 1 is not in the list",underTest.contains(TEST_P1));
		assertTrue("Test Patient 2 is not in the list",underTest.contains(TEST_P2));
		assertFalse("Test Patient 3 is already in the list",underTest.contains(TEST_P3));
		underTest.neuerPatient(TEST_P3);
		assertTrue("Test Patient 3 is not in the list",underTest.contains(TEST_P3));
		Iterator<Patient> it = underTest.iterator();
		it.next();it.next(); //TEst Patient 1 and 2
		assertEquals("Test Patient 3 is in the wrong spot",TEST_P3,it.next());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNeuerPatientPatient2() {
		assertTrue("Test Patient 1 is not in the list",underTest.contains(TEST_P1));
		underTest.neuerPatient(TEST_P1);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testNeuerPatientPatient3() {
		underTest.neuerPatient(TEST_P3);
		underTest.neuerPatient(TEST_P4);
	}

	@Test
	public void testEntfernePatientPatient() {
		assertTrue("Test Patient 1 is not in the list",underTest.contains(TEST_P1));
		assertTrue("Test Patient 2 is not in the list",underTest.contains(TEST_P2));
		assertEquals("Returned unexpected object",TEST_P2, underTest.entfernePatient(TEST_P2));
		assertTrue("Test Patient 1 is not in the list anymore",underTest.contains(TEST_P1));
		assertFalse("Test Patient 2 is still in the list",underTest.contains(TEST_P2));
	}
	
	@Test
	public void testEntfernePatientPatient2() {
		assertTrue("Test Patient 1 is not in the list",underTest.contains(TEST_P1));
		assertTrue("Test Patient 2 is not in the list",underTest.contains(TEST_P2));
		assertFalse("Test Patient 2 is already in the list",underTest.contains(TEST_P3));
		assertNull("deleted object that was not supposed to be there?!",underTest.entfernePatient(TEST_P3));
	}

	@Test
	public void testDerNaechsteBitte() {
		assertTrue("1st Patient not in the Queue", underTest.contains(TEST_P1));
		assertTrue("2st Patient not in the Queue", underTest.contains(TEST_P2));
		assertEquals("Got wrong next patient",TEST_P1, underTest.derNaechsteBitte());
		assertFalse("1st Patient not in the Queue", underTest.contains(TEST_P1));
		assertTrue("2st Patient not in the Queue", underTest.contains(TEST_P2));
	}
	
	@Test(expected = IllegalStateException.class)
	public void testDerNaechsteBitte2() {
		underTest.derNaechsteBitte();
		underTest.derNaechsteBitte();
		underTest.derNaechsteBitte();
	}
	
	@Test
	public void testContains() {
		assertTrue("Test Patient 1 is not in the list",underTest.contains(TEST_P1));
		assertTrue("Test Patient 2 is not in the list",underTest.contains(TEST_P2));
		assertFalse("Test Patient 3 is already in the list",underTest.contains(TEST_P3));
		underTest.neuerPatient(TEST_P3);
		assertTrue("Test Patient 1 is not in the list",underTest.contains(TEST_P1));
		assertTrue("Test Patient 2 is not in the list",underTest.contains(TEST_P2));
		assertTrue("Test Patient 3 is not in the list",underTest.contains(TEST_P3));
		underTest.entfernePatient(TEST_P2);
		assertTrue("Test Patient 1 is not in the list",underTest.contains(TEST_P1));
		assertFalse("Test Patient 2 is still in the list",underTest.contains(TEST_P2));
		assertTrue("Test Patient 3 is not in the list",underTest.contains(TEST_P3));
		assertFalse("reported null as contained",underTest.contains(null));
	}
	
	@Test
	public void testIterator() {
		Iterator<Patient> it = underTest.iterator();
		assertNotNull("Passed iterator is null",it);
		assertTrue("Has no next on first invocation", it.hasNext());
		assertEquals("Next object is not the expected object",TEST_P1,it.next());
		assertTrue("Has no next on first invocation", it.hasNext());
		assertEquals("Next object is not the expected object",TEST_P2,it.next());
		assertFalse("Has no next on first invocation", it.hasNext());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testIterator2() {
		Iterator<Patient> it = underTest.iterator();
		assertNotNull("Passed iterator is null",it);
		int count = 0;
		while(it.hasNext()) {
			if(count < 20) {
				it.next();
			}else {
				fail("Aboard iterator test after 20 iterations ...");
			}
		}
		assertFalse(it.hasNext());
		it.next();
	}

}
