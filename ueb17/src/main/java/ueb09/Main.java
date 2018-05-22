package ueb09;

import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class Main {

	public static void main(String[] args) {

		// private static final BiPredicate <Artikel, Artikel>
		// Unterkategorie = ???; was ist die Unterkategorie?
		// assuming Classname where default Artikels are smalest, because
		// they have no subcat.
		BiPredicate<Artikel, Artikel> subcat = (x, y) -> {
			int comp = x.getClass().getSimpleName().compareTo(y.getClass()
					.getSimpleName());
			if (comp == 0) {
				return String.CASE_INSENSITIVE_ORDER.compare(
						x.getArtikelBezeichnung(), 
						y.getArtikelBezeichnung()
						) < 0;
			} else {
				if (x.getClass().equals(Artikel.class)) {
					return true;
				} else {
					return comp > 0;
				}
			}
		};
		BiPredicate<Artikel, Artikel> bestand = (x, y) -> x
				.getArtikelBestand() > y.getArtikelBestand();
		BiPredicate<Artikel, Artikel> preis = (x, y) -> x.getPreis() > y
				.getPreis();

		Consumer<Artikel> zehnProzent = a -> {
			((Artikel) a).setPreis(((Artikel) a).getPreis() * 0.9);
		};
		Consumer<Artikel> sonderangebot = a -> {
			((Artikel) a).setArtikelBezeichnung("Sonderangebot "
					+ ((Artikel) a).getArtikelBezeichnung());
		};
		Consumer<Artikel> zehnProzentSonderangebot = a -> {
			zehnProzent.andThen(sonderangebot).accept(a);
		};

		Lager lager = new Lager(5);
		lager.lagereArtikel(new Artikel(1000, "Artikel", 20, 1.0));
		lager.lagereArtikel(new Buch(1001, "Buch", 15, 5.0, "BuchAuthor",
				"BuchTitel", "BuchVerlag"));
		lager.lagereArtikel(new Cd(1002, "CD", 10, 10.0, "CDIntepret",
				"CDTitel", 5));
		lager.lagereArtikel(new Dvd(1003, "DVD", 5, 15.55, "DVDTitel", 120,
				2010));

		System.out.println(lager.toString());
		System.out.println("Subcat");
		System.out.println(lager.getSorted(subcat));

		System.out.println("Bestand");
		System.out.println(lager.getSorted(bestand));

		System.out.println("Preis");
		System.out.println(lager.getSorted(preis));

		System.out.println(lager.toString());
		System.out.println("10 %");
		lager.applyToArticles(zehnProzent);

		System.out.println(lager.toString());
		System.out.println("Sunderangebot");
		lager.applyToArticles(sonderangebot);

		System.out.println(lager.toString());
		System.out.println("10 % + Sonderangebot");
		lager.applyToArticles(zehnProzentSonderangebot);

		System.out.println(lager.toString());

	}

}
