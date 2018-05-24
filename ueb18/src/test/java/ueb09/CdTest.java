package ueb09;
import org.junit.Test;

public class CdTest extends ArtikelTest {

	@Override
	public void init() {
		super.init();
		Artikel base = underTest;
		underTest = new Cd(base.getArtikelNummer(), base
				.getArtikelBezeichnung(), base.getArtikelBestand(), base
						.getPreis(), "art", "int", 5);
	}
	
	@Test
	public void testCd() {
		underTest = new Cd(1234, "art", "int", "t", 1);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testCd1() {
		underTest = new Cd(1234, "art", "", "t", 1);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testCd2() {
		underTest = new Cd(1234, "art", "int", "", 1);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testCd3() {
		underTest = new Cd(1234, "art", "int", "t", 0);
	}

}
