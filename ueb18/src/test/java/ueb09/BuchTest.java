package ueb09;
import org.junit.Test;

public class BuchTest extends ArtikelTest {

	@Override
	public void init() {
		super.init();
		Artikel base = underTest;
		underTest = new Buch(base.getArtikelNummer(), base
				.getArtikelBezeichnung(), base.getArtikelBestand(), base
						.getPreis(), "a", "t", "v");
	}
	@Test
	public void testBuch() {
		underTest = new Buch(1234, "buch", "aut", "t", "v");
	}
	@Test(expected = InvalidInputException.class)
	public void testBuch2() {
		underTest = new Buch(1234, "buch", "", "t", "v");
	}
	@Test(expected = InvalidInputException.class)
	public void testBuch3() {
		underTest = new Buch(1234, "buch", "art", "", "v");
	}
	@Test(expected = InvalidInputException.class)
	public void testBuch4() {
		underTest = new Buch(1234, "buch", "art", "t", "");
	}

}
