import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 */

/**
 * @author jannik
 *
 */
public class ArtikelTest {

	protected Artikel underTest;

	/**
	 * Test method for
	 * {@link Artikel#Artikel(int, java.lang.String, int, double)}.
	 */
	@Test
	public void testArtikelIntStringIntDouble() {
		underTest = new Artikel(1234, "Test", 12, 6.7);
		assertEquals(1234, underTest.getArtikelNummer());
		assertEquals("Test", underTest.getArtikelBezeichnung());
		assertEquals(12, underTest.getArtikelBestand());
		assertEquals(6.7, underTest.getPreis(), 0);
		underTest = new Artikel(1234, "Test", 12);
		assertEquals(1234, underTest.getArtikelNummer());
		assertEquals("Test", underTest.getArtikelBezeichnung());
		assertEquals(12, underTest.getArtikelBestand());
		assertThat(underTest.getPreis(), greaterThan(0.0));
		underTest = new Artikel(1234, "Test");
		assertEquals(1234, underTest.getArtikelNummer());
		assertEquals("Test", underTest.getArtikelBezeichnung());
		assertThat(underTest.getArtikelBestand(), greaterThanOrEqualTo(0));
		assertThat(underTest.getPreis(), greaterThan(0.0));
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> new Artikel(123, "Test", 12, 6.7));
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> new Artikel(12345, "Test", 12, 6.7));
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> new Artikel(1234, null, 12, 6.7));
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> new Artikel(1234, "", 12, 6.7));
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> new Artikel(1234, " ", 12, 6.7));
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> new Artikel(1234, null, -15, 6.7));
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> new Artikel(1234, null, 12, 0.0));
	}


	/**
	 * Test method for {@link Artikel#bucheZugang(int)}.
	 */
	@Test
	public void testBucheZugang() {
		underTest = new Artikel(1234, "Test", 10, 10.0);
		underTest.bucheZugang(5);
		assertEquals(15, underTest.getArtikelBestand());
		underTest.bucheZugang(17);
		assertEquals(32, underTest.getArtikelBestand());
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> underTest.bucheZugang(0));
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> underTest.bucheZugang(-12));
	}

	/**
	 * Test method for {@link Artikel#bucheAbgang(int)}.
	 */
	@Test
	public void testBucheAbgang() {
		underTest = new Artikel(1234, "Test", 10, 10.0);
		underTest.bucheAbgang(3);
		assertEquals(7, underTest.getArtikelBestand());
		underTest.bucheAbgang(5);
		assertEquals(2, underTest.getArtikelBestand());
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> underTest.bucheAbgang(0));
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> underTest.bucheAbgang(-12));
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> underTest.bucheAbgang(3));
	}

	/**
	 * Test method for
	 * {@link Artikel#setArtikelBezeichnung(java.lang.String)}.
	 */
	@Test
	public void testSetArtikelBezeichnung() {
		underTest = new Artikel(1234, "Test", 10, 10.0);
		underTest.setArtikelBezeichnung("T2");
		assertEquals("T2", underTest.getArtikelBezeichnung());
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> underTest.setArtikelBezeichnung(null));
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> underTest.setArtikelBezeichnung("  "));
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> underTest.setArtikelBezeichnung(""));
	}


	/**
	 * Test method for {@link Artikel#setPreis(double)}.
	 */
	@Test
	public void testSetPreis() {
		underTest = new Artikel(1234, "Test", 10, 10.0);
		underTest.setPreis(20.0);
		assertEquals(20.0, underTest.getPreis(),0);
		underTest.setPreis(19.005);
		assertEquals(19.01, underTest.getPreis(),0);
		underTest.setPreis(19.004);
		assertEquals(19.01, underTest.getPreis(),0);
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> underTest.setPreis(0));
		assertThatExceptionOfType(IllegalArgumentException.class)
		.isThrownBy(() -> underTest.setPreis(-17));
	}

}
