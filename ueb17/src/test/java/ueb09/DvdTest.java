package ueb09;
import org.junit.Test;

public class DvdTest extends ArtikelTest {

	@Override
	public void init() {
		super.init();
		Artikel base = underTest;
		underTest = new Dvd(base.getArtikelNummer(), base
				.getArtikelBezeichnung(), base.getArtikelBestand(), base
						.getPreis(), "ti",120, 2010);
	}
	
	@Test
	public void testDvd() {
		underTest = new Dvd(1234, "art", "ti", 120, 2010);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testDvd1() {
		underTest = new Dvd(1234, "art", "", 120, 2010);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testDvd2() {
		underTest = new Dvd(1234, "art", "ti", 0, 2010);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testDvd3() {
		underTest = new Dvd(1234, "art", "ti", 120, 1900);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testDvd4() {
		underTest = new Dvd(1234, "art", "ti", 120, 2200);
	}

}
