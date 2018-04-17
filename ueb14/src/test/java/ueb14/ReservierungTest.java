package ueb14;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReservierungTest {

	private Uhrzeit uhrzeit1;
	private Uhrzeit uhrzeit2;
	
	@Before
	public void init() {
		uhrzeit1 = new Uhrzeit(12, 30);
		uhrzeit2 = new Uhrzeit(13, 30);
	}

	@Test
	public void testReservierung() {
		Reservierung r = new Reservierung(uhrzeit1, uhrzeit2);
		assertEquals(uhrzeit1, r.getStart());
		assertEquals(uhrzeit2, r.getEnde());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReservierung2() {
		new Reservierung(uhrzeit1, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReservierung3() {
		new Reservierung(null, uhrzeit2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReservierung4() {
		new Reservierung(uhrzeit2, uhrzeit1);
	}

}
