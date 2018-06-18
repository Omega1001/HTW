package ueb09;
import static org.junit.Assert.*;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;

import org.junit.Test;

public class ArtikelTest {

	protected Artikel underTest;

	@Before
	public void init() {
		underTest = new Artikel(1234, "Test", 10, 10.0);
	}

	/**
	 * Test method for
	 * {@link Artikel#Artikel(int, java.lang.String, int, double)}.
	 */
	@Test
	public void testArtikelConstructor() {
		underTest = new Artikel(1234, "Test", 12, 6.7);
		assertEquals(1234, underTest.getArtikelNummer());
		assertEquals("Test", underTest.getArtikelBezeichnung());
		assertEquals(12, underTest.getArtikelBestand());
		assertEquals(6.7, underTest.getPreis(), 0);
	}

	@Test
	public void testArtikelConstructor2() {
		underTest = new Artikel(1234, "Test", 12);
		assertEquals(1234, underTest.getArtikelNummer());
		assertEquals("Test", underTest.getArtikelBezeichnung());
		assertEquals(12, underTest.getArtikelBestand());
		assertThat(underTest.getPreis(), greaterThan(0.0));
	}

	private Matcher<Double> greaterThan(final double d) {
		return new BaseMatcher<Double>() {

			@Override
			public boolean matches(Object item) {
				if (item instanceof Double) {
					return ((Double)item) > d;
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Checks for greater than double");
				
			}
		};
	}
	
	private Matcher<Integer> greaterThanOrEqualTo(final double d) {
		return new BaseMatcher<Integer>() {

			@Override
			public boolean matches(Object item) {
				if (item instanceof Integer) {
					return ((Integer)item) >= d;
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Checks for greater than double");
				
			}
		};
	}

	@Test
	public void testArtikelConstructor3() {
		underTest = new Artikel(1234, "Test");
		assertEquals(1234, underTest.getArtikelNummer());
		assertEquals("Test", underTest.getArtikelBezeichnung());
		assertThat(underTest.getArtikelBestand(), greaterThanOrEqualTo(0));
		assertThat(underTest.getPreis(), greaterThan(0.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArtikelConstructor4() {
		new Artikel(123, "Test", 12, 6.7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArtikelConstructor5() {
		new Artikel(12345, "Test", 12, 6.7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArtikelConstructor6() {
		new Artikel(1234, null, 12, 6.7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArtikelConstructor7() {
		new Artikel(1234, "", 12, 6.7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArtikelConstructor8() {
		new Artikel(1234, " ", 12, 6.7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArtikelConstructor9() {
		new Artikel(1234, null, -15, 6.7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArtikelConstructor10() {
		new Artikel(1234, null, 12, 0.0);
	}

	/**
	 * Test method for {@link Artikel#bucheZugang(int)}.
	 */
	@Test
	public void testBucheZugang() {
		underTest.bucheZugang(5);
		assertEquals(15, underTest.getArtikelBestand());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testBucheZugang2() {
		underTest.bucheZugang(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBucheZugang3() {
		underTest.bucheZugang(-12);
	}

	/**
	 * Test method for {@link Artikel#bucheAbgang(int)}.
	 */
	@Test
	public void testBucheAbgang() {
		underTest.bucheAbgang(3);
		assertEquals(7, underTest.getArtikelBestand());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBucheAbgang2() {
		underTest.bucheAbgang(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBucheAbgang3() {
		underTest.bucheAbgang(-12);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBucheAbgang4() {
		underTest.bucheAbgang(11);
	}

	/**
	 * Test method for
	 * {@link Artikel#setArtikelBezeichnung(java.lang.String)}.
	 */
	@Test
	public void testSetArtikelBezeichnung() {
		underTest.setArtikelBezeichnung("T2");
		assertEquals("T2", underTest.getArtikelBezeichnung());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetArtikelBezeichnung2() {
		underTest.setArtikelBezeichnung(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetArtikelBezeichnung3() {
		underTest.setArtikelBezeichnung("  ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetArtikelBezeichnung4() {
		underTest.setArtikelBezeichnung("");
	}

	/**
	 * Test method for {@link Artikel#setPreis(double)}.
	 */
	@Test
	public void testSetPreis() {
		underTest.setPreis(20.0);
		assertEquals(20.0, underTest.getPreis(), 0);
	}

	@Test
	public void testSetPreis2() {
		underTest.setPreis(19.005);
		assertEquals(19.01, underTest.getPreis(), 0);
	}

	@Test
	public void testSetPreis3() {
		underTest.setPreis(19.0000000000004);
		assertEquals(19.00, underTest.getPreis(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPreis4() {
		 underTest.setPreis(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPreis5() {
		underTest.setPreis(-17);
	}

}
