package ueb09;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

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

//------Start ueb18----------------------------------------------------------------------------------------------------
		
//		Absolut keine Ahnung ob das so richtig ist geschweige dem der Aufbau von den Lagermethoden
//		da diese vom halben Code her mit den methoden von 17 übereinstimmen 
//		aber nicht genug(aus meiner Sicht) um weiterverwendet zu werden
		
		System.out.println("Start ueb18!");
		
		System.out.println(lager.toString());
		
		System.out.println("Erhoehen Sie den Preis aller CDs um 10%");
		Consumer<Artikel> plusZehnProzent = a -> a.setPreis(a.getPreis() * 1.1);
		Predicate<Artikel> cD = a -> a.getClass().getSimpleName().equals("CD");	
		lager.applyToSomeArticles(plusZehnProzent, cD);
		
		System.out.println(lager.toString());
		
		System.out.println("Reduzieren Sie den Preis aller Artikel, von denen nur noch zwei Exemplare im Bestand sind um 5%.");
		Predicate<Artikel> bestand2 = a -> a.getArtikelBestand() == 2;
		Consumer<Artikel> funfProzent = a -> a.setPreis(a.getPreis() * 0.95);
		lager.applyToSomeArticles(funfProzent, bestand2);
		
		System.out.println(lager.toString());
		
		System.out.println("Reduzieren Sie alle Bücher eines gegebenen Autors um 5%.");
		Predicate<Artikel> BuchAutorX = a -> a.getBeschreibung().startsWith("AuthorX");
		lager.applyToSomeArticles(funfProzent, BuchAutorX);
		
		System.out.println(lager.toString());
		
		System.out.println("Erzeugen Sie einen Lambda-Ausdruck der die beiden Operationen i und iii kombiniert.");
		//Die Methode frisst zwei Argumente wie soll ich da EINEN validen Ausdruck erzeugen...
		Consumer<Artikel> pluszehnProzentfurCDsUndFunfProzentfurBuchervonAutorX = a -> {
			if(a.getClass().getSimpleName().equals("CD")) {
				a.setPreis(a.getPreis() * 1.1);
			}
			else {
				a.setPreis(a.getPreis() * 0.95);
			}
		};	
		Predicate<Artikel> cDsUndBucherMitAutorX = a -> a.getClass().getSimpleName().equals("CD")||a.getBeschreibung().startsWith("AuthorX");
		lager.applyToSomeArticles(pluszehnProzentfurCDsUndFunfProzentfurBuchervonAutorX, cDsUndBucherMitAutorX);
		
		System.out.println(lager.toString());
		
		System.out.println("Fragen Sie eine Liste aller Bücher, sortiert nach Autor, ab.");
		Predicate<Artikel> buch = a -> a.getClass().getSimpleName().equals("Buch");
		BiPredicate<Artikel, Artikel> autor = (x, y) -> {
			int comp = x.getBeschreibung().compareTo(y.getBeschreibung());
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
		}};
		System.out.println(lager.getArticles(buch, autor));
	}

}
