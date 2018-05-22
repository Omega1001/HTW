package ueb09;

import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class Main {
	
	public static void main(String[] args) {
		
		
		//private static final BiPredicate <Artikel, Artikel> Unterkategorie = ???; was ist die Unterkategorie?
		final BiPredicate <Artikel, Artikel> Bestand = (x,y) -> x.getArtikelBestand() > y.getArtikelBestand();
		final BiPredicate <Artikel, Artikel> Preis = (x,y) -> x.getPreis() > y.getPreis();
		
		
		final Consumer<Artikel> zehnProzent = a -> {
			((Artikel) a).setPreis(((Artikel) a).getPreis()*0.9); 
		};
		final Consumer<Artikel> sonderangebot = a -> {
			((Artikel) a).setArtikelBezeichnung("Sonderangebot" +((Artikel) a).getArtikelBezeichnung()); 
		};
		final Consumer<Artikel> zehnProzentSonderangebot = a -> {
			zehnProzent.andThen(sonderangebot).accept(a); 
		};
		
		Lager lager = new Lager(5);
		lager.lagereArtikel(new Artikel(1000, "Artikel", 20, 1.0));
		lager.lagereArtikel(new Buch(1001, "Buch", 15, 5.0, "BuchAuthor", "BuchTitel", "BuchVerlag"));
		lager.lagereArtikel(new Cd(1002, "CD", 10, 10.0, "CDIntepret", "CDTitel", 5));
		lager.lagereArtikel(new Dvd(1003, "DVD", 5, 15.55,  "DVDTitel", 120, 2010));
		
		System.out.println(lager.toString());
		
//		lager.getSorted(Unterkategorie);
//		
//		System.out.println(lager.toString());

		lager.getSorted(Bestand);
		
		System.out.println(lager.toString());
		
		lager.getSorted(Preis);
		
		System.out.println(lager.toString());
		
		lager.applyToArticles(zehnProzent);
		
		System.out.println(lager.toString());
		
		lager.applyToArticles(sonderangebot);
		
		System.out.println(lager.toString());
	}

}
