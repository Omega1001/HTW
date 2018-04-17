package ueb14;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RaumTest {

	private Raum underTest;
	
	private Mitarbeiter testMitarbeiter;
	private Mitarbeiter testMitarbeiter2;
	private Uhrzeit testUhrzeit1;
	private Uhrzeit testUhrzeit2;
	
	@Before
	public void setUp() throws Exception {
		underTest = new Raum(1, 2, 3);
		testMitarbeiter = new Mitarbeiter("vName", "nName", "mail@mail.de");
		testMitarbeiter2 = new Mitarbeiter("vName2", "nName2", "mail2@mail.de");
		testUhrzeit1 = new Uhrzeit(12, 30);
		testUhrzeit2 = new Uhrzeit(13, 30);
	}

	@Test
	public void testRaum() {
		underTest = new Raum(1, 2, 3);
		assertEquals(1, underTest.getGeb());
		assertEquals(2, underTest.getEtage());
		assertEquals(3, underTest.getRaum());
		assertNotNull(underTest.getReservierungen());
		assertTrue(underTest.getReservierungen().isEmpty());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRaum2() {
		new Raum(-1, 2, 3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRaum3() {
		new Raum(1, 2, -3);
	}

	@Test
	public void testAddReservierung() {
		Reservierung r = new Reservierung(testUhrzeit1, testUhrzeit2);
		r.setReservator(testMitarbeiter);
		underTest.addReservierung(r);
		assertFalse(underTest.getReservierungen().isEmpty());
		assertEquals(r, underTest.getReservierungen().get(0));
		assertEquals(underTest, r.getRaum());
	}

	@Test
	public void testGetAllReservatoren() {
		Reservierung r = new Reservierung(testUhrzeit1, testUhrzeit2);
		r.setReservator(testMitarbeiter);
		underTest.addReservierung(r);
		Reservierung r2 = new Reservierung(testUhrzeit1, testUhrzeit2);
		r2.setReservator(testMitarbeiter2);
		underTest.addReservierung(r2);
		assertTrue(containsAll(underTest.getAllReservatoren(),testMitarbeiter,testMitarbeiter2));
	}

	private boolean containsAll(List<Mitarbeiter> list,
			Mitarbeiter ... items) {
		boolean[] res = new boolean [items.length];
		for(int i=0; i<list.size();i++) {
			for(int i2=0;i2<items.length;i2++) {
				res[i2] = res [i2] || list.get(i).equals(items[i2]);
			}
		}
		for(Boolean b : res) {
			if(!b) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testIsReservedFor() {
		Reservierung r = new Reservierung(testUhrzeit1, testUhrzeit2);
		r.setReservator(testMitarbeiter);
		underTest.addReservierung(r);
		assertTrue(underTest.isReservedFor(testMitarbeiter));
		assertFalse(underTest.isReservedFor(testMitarbeiter2));
	}

}
