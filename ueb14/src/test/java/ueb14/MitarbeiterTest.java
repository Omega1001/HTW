package ueb14;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MitarbeiterTest {

	private Mitarbeiter underTest;
	private Raum testRaum = new Raum(1, 2, 5);
	private Uhrzeit testUhrzeit = new Uhrzeit(13, 30);
	private Uhrzeit testUhrzeit2 = new Uhrzeit(14, 00);

	@Before
	public void init() {
		underTest = new Mitarbeiter("Test", "User", "test@mail.de");
	}

	@Test
	public void testMitarbeiter() {
		underTest = new Mitarbeiter("test", "User", "test.mail@somwhere.de");
		assertEquals("test.mail@somwhere.de", underTest.getMail());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMitarbeiter2() {
		new Mitarbeiter("test", "User", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMitarbeiter3() {
		new Mitarbeiter("test", "User", "");
	}

	@Test
	public void testReserviere() {
		assertTrue(testRaum.getReservierungen().isEmpty());
		underTest.reserviere(testRaum, testUhrzeit, testUhrzeit2,
				"something");
		assertEquals(1, testRaum.getReservierungen().size());
		assertTrue(testRaum.isReservedFor(underTest));

		Reservierung r = testRaum.getReservierungen().get(0);
		assertEquals(testUhrzeit, r.getStart());
		assertEquals(testUhrzeit2, r.getEnde());
		assertEquals(testRaum, r.getRaum());
		assertEquals(underTest, r.getReservator());
		assertEquals("something", r.getBemerkung());
	}

	@Test
	public void testReserviere2() {
		assertTrue(testRaum.getReservierungen().isEmpty());
		underTest.reserviere(testRaum, testUhrzeit, testUhrzeit2, "");
		assertEquals(1, testRaum.getReservierungen().size());
		Reservierung r = testRaum.getReservierungen().get(0);
		assertNull(r.getBemerkung());
	}

	@Test
	public void testReserviere3() {
		assertTrue(testRaum.getReservierungen().isEmpty());
		underTest.reserviere(testRaum, testUhrzeit, testUhrzeit2, null);
		assertEquals(1, testRaum.getReservierungen().size());
		Reservierung r = testRaum.getReservierungen().get(0);
		assertNull(r.getBemerkung());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReserviere4() {
		underTest.reserviere(null, testUhrzeit, testUhrzeit2, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReserviere5() {
		underTest.reserviere(testRaum, null, testUhrzeit2, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReserviere6() {
		underTest.reserviere(testRaum, testUhrzeit, null, null);
	}

}
