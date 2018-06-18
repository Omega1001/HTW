package ueb09;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Simple Lager Klasse zum archivieren von Artikeln.
 * <p>
 * Diese Klasse erlaubt das Speichern von Artikel Objekten in Arrays. <br>
 * 
 * @author Jannik Adam
 * @author Fromm-Borys
 * @version 1.0
 *
 */
public class Lager implements Iterable<Artikel> {

	private static final int MIN_DIMENSION = 1;
	private static final int MAX_DIMENSION = 9000;// 1000-9999 sind valide
													// Artikelnummern daher
													// die 9000.
	private static final int MIN_PROZENT = -100;

	private LinkedHashMap<Integer, Artikel> lagerFeld;
	private int artikelAnzahl;
	private int dimension;

	/**
	 * Konstruktor fuer ein Lager Objekt.
	 * <p>
	 * 
	 * Erstellt ein Array zur Auflistung von Artikel Objekten.<br>
	 * Diese erstellte Instanz kann dimension viele {@link Artikel} Objecte
	 * aufnehmen
	 * <p>
	 * 
	 * Die dimension muss groesser als 0 und kleiner oder gleich 9000
	 * sein.<br>
	 * Wird die dimension ausserhalb dieses Raumes gewaehlt, wird eine
	 * {@link IllegalArgumentException} geworfen.
	 * 
	 * @param dimension
	 *            gibt die Dimension des zu erstellenden Feldes an.
	 * @throws IllegalArgumentException
	 *             wenn die Dimension zu klein oder zu groess gewaehlt wird
	 */
	public Lager(int dimension) {
		if (dimension < MIN_DIMENSION || dimension > MAX_DIMENSION) {
			throw new IllegalArgumentException(
					"Die Anzahl der Artikel im Lager muss zwischen "
							+ MIN_DIMENSION + " und " + MAX_DIMENSION
							+ "liegen!");
		}
		this.dimension = dimension;
		artikelAnzahl = 0;
		lagerFeld = new LinkedHashMap<Integer, Artikel>(dimension);
	}

	/**
	 * Konstruktor fuer ein Lager Object dass die maximale Anzahl an
	 * Artikel Objekten speichern kann.
	 * <p>
	 * Diese Mehode verhaelt sich genau wie {@link #Lager(int)} mit
	 * MAX_DIMENSION als parameter
	 * 
	 * @see #Lager(int)
	 */
	public Lager() {
		this(MAX_DIMENSION);
	}

	/**
	 * Methode zum erfassen eines einmaligen Artikel Objekt in dem Lager
	 * Objekt.
	 * <p>
	 * 
	 * @param artNr
	 *            Artikelnummer des zu erfassenden Artikels.
	 * @param artBez
	 *            Artikelbezeichnung des zu erfassenden Artikels.
	 * @param artMenge
	 *            Artikelmenge des zu erfassenden Artikels.
	 */
	public void lagereArtikel(Artikel artikel) {
		if (artikelAnzahl >= dimension) {
			throw new IllegalArgumentException("Lager ist bereits voll!");
		}
		for (int i = 0; i < artikelAnzahl; i++) {
			if (lagerFeld.containsKey(artikel.getArtikelNummer())) {
				throw new IllegalArgumentException(
						"Artikel bereits eingelagert!");
			}
		}
		lagerFeld.put(artikel.getArtikelNummer(), artikel);
		artikelAnzahl++;
	}

	/**
	 * Methode zum loeschen eines Artikels aus dem Lager, ohne Luecken zu
	 * verursachen.
	 * <p>
	 * 
	 * @param artNr
	 *            Einzugebende Nummer des zu loeschenden Artikels.
	 * @throws NoSuchElementException
	 *             wenn das angegebene Element nicht in diesem Lager
	 *             existiert
	 */
	public void loescheArtikel(int artNr) throws NoSuchElementException {

		if (null == lagerFeld.remove(artNr)) {

			throw new NoSuchElementException();
		}

		artikelAnzahl--;
	}

	/**
	 * Methode zum buchen eines Zugangs zu einem bestimmten Artikel.
	 * <p>
	 * 
	 * @param artNr
	 *            Einzugebende Nummer des Artikels der Zugang verzeichnet.
	 * @param zugang
	 *            Einzugebende Menge des Zugangs.
	 * @throws NoSuchElementException
	 *             wenn das angegebene Element nicht in diesem Lager
	 *             existiert
	 */
	public void bucheZugang(int artNr, int zugang)
			throws NoSuchElementException {
		findeArtikel(artNr).bucheZugang(zugang);
	}

	/**
	 * Methode zum buchen eines Abgangs zu einem bestimmten Artikel.
	 * <p>
	 * 
	 * @param artNr
	 *            Einzugebende Nummer des Artikels der Abgang verzeichnet.
	 * @param zugang
	 *            Einzugebende Menge des Abgangs.
	 * @throws NoSuchElementException
	 *             wenn das angegebene Element nicht in diesem Lager
	 *             existiert
	 */
	public void bucheAbgang(int artNr, int abgang)
			throws IllegalArgumentException {
		findeArtikel(artNr).bucheAbgang(abgang);
	}

	/**
	 * Methode zum Anpassen aller Preise um einen bestimmten
	 * Prozentsatz.<br>
	 * Sowohl positive, als auch negative prozentsaetze sind erlaubt
	 * <p>
	 * 
	 * Ein Preisnachlasss von 100% oder mehr ist jedoch nicht erlaubt<br>
	 * <p>
	 * 
	 * @param prozent
	 *            Einzugebender Prozenwert.
	 * @throws NoSuchElementException
	 *             wenn das angegebene Element nicht in diesem Lager
	 *             existiert
	 */
	public void passePreiseAn(double prozent)
			throws IllegalArgumentException {
		if (prozent <= MIN_PROZENT) {
			throw new IllegalArgumentException(
					"Der Prozentwert muss grosser als -100% sein!");
		}
		prozent = 1 + (prozent / 100);
		for (Artikel art : this) {
			art.setPreis((art.getPreis() * prozent));
		}
	}

	/**
	 * MAN SIEHE HIER UMLAUTE FUNKTIONIEREN DOCH AUF DEM ENDLOSDRUCKER!!!
	 * Methode, die ein Artikel Object anhand seiner Artikelnummer zurrück
	 * gibt.<br>
	 * Sollte es meh als einen Artikel mit der selben Nummer geben, wird
	 * hier jeweils der 1. zurrueckgegeben.
	 * 
	 * @param artNr
	 *            Nummer des zu holenden Artikel
	 * @return der Artikel mit der uebergebenen Artikelnummer
	 * @throws NoSuchElementException
	 *             wenn kein Element mit nummer nummer existiert
	 */
	public Artikel findeArtikel(int artNr) throws NoSuchElementException {
		if (null == lagerFeld.get(artNr)) {

			throw new NoSuchElementException();
		}

		return lagerFeld.get(artNr);
	}

	/**
	 * Methode zum ausgeben einer Bestandsliste eines Lagers.
	 * <p>
	 * 
	 * @return Formatierte Bestandsliste des Lagers
	 */
	public String ausgebenBestandsListe() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-6s %-40s %10s %7s %15s", "ArtNr",
				"Beschreibung", "Preis", "Bestand", "Gesamt"));
		sb.append("\r\n");
		double ges = 0;
		for (Artikel a : this) {
			sb.append("\r\n");
			double t = a.getPreis() * a.getArtikelBestand();
			sb.append(String.format("%-6d %-40s %10.2f %7d %15.2f", a
					.getArtikelNummer(), a.getArtikelBezeichnung(), a
							.getPreis(), a.getArtikelBestand(), t));
			ges = ges + t;
		}
		sb.append("\r\n");
		sb.append(String.format("%-66s %15.2f", "Gesamt", ges));
		return sb.toString();
	}

	/**
	 * Methode um das Lager Array zu sortieren und alternativ als Listen
	 * Object zu Verfuegung zu stellen.
	 * <p>
	 * 
	 * @param kriterium
	 *            BiPredicate das das Sortierkriterium fest legt.
	 * @return Nach Kriterium sortierte Liste.
	 */
	public List<Artikel> getSorted(BiPredicate<Artikel,
			Artikel> kriterium) {
		return stream().sorted((a, b) -> kriterium.test(a, b) ? 1 : -1)
				.collect(Collectors.toList());
	}

	public Stream<Artikel> stream() {
		return StreamSupport.stream(this.spliterator(), false);
	}

	/**
	 * Methode um alle Artikel im Lager auszugeben die einem bestimmtem
	 * Kriterium entsprechen.
	 * <p>
	 * 
	 * @param kriterium
	 *            Predicate das das Auswahlkriterium fest legt.
	 * @return Liste mit allem im Lager enthaltenen Artikeln die dem
	 *         Kriterium entsprechen.
	 */
	public List<Artikel> filter(Predicate<Artikel> kriterium) {
		return stream().filter(kriterium).collect(Collectors.toList());
	}

	/**
	 * Methode um eine uebergebene Operation auf alle Artikel im Lager
	 * anzuwenden.
	 * <p>
	 * 
	 * @param operation
	 *            auszufuehrende Operation.
	 */
	public void applyToArticles(Consumer<Artikel> operation) {
		stream().forEach(a -> operation.accept(a));
	}

	/**
	 * Gibt die Anzahl der gelagerten Artikel zurueck.
	 * 
	 * @return die Artrikelanzahl
	 */
	public int getArtikelAnzahl() {
		return artikelAnzahl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lager[ \r\n\tAnzahl der Artikel: ");
		builder.append(artikelAnzahl + "\r\n\t");
		builder.append("Gelagerte Artikel:[");
		for (Artikel art : this) {
			builder.append("\r\n\t\t").append(art);
		}
		builder.append("]\r\n]");
		return builder.toString();
	}

	@Override
	public Iterator<Artikel> iterator() {
		return new Iterator<Artikel>() {

			Iterator<Integer> ite = lagerFeld.keySet().iterator();

			@Override
			public boolean hasNext() {

				return ite.hasNext();
			}

			@Override
			public Artikel next() {

				return lagerFeld.get(ite.next());
			}
		};
	}

	/**
	 * Methode um auf alle Artikel im Lager, die einem bestimmtem Kriterium
	 * entsprechen, eine uebergebene Operation anazuwenden.
	 * <p>
	 * 
	 * @param operation
	 *            auszufuehrende Operation.
	 * @param kriterium
	 *            Predicate das das Auswahlkriterium fest legt.
	 */
	public void applyToSomeArticles(Consumer<Artikel> operation, Predicate<
			Artikel> kriterium) {
		stream().filter(kriterium).forEach(operation);
	}

	/**
	 * Methode um alle einem Suchkriterium entsprechenden Artikel aus dem
	 * Lager als eine sortierte Liste wiederzugeben.
	 * <p>
	 * 
	 * @param kriterium
	 *            Predicate das das Auswahlkriterium fuer die Suche fest
	 *            legt.
	 * @param sortierKriterium
	 *            BiPredicate das das Sortierkriterium fest legt.
	 * @return Nach dem Sortierkriterium sortierte Liste, deren Inhalt
	 *         derScuhkriterium entspricht.
	 */
	public List<Artikel> getArticles(Predicate<Artikel> suchKriterium,
			BiPredicate<Artikel, Artikel> sortierKriterium) {

		return stream().filter(suchKriterium).sorted((a, b) -> sortierKriterium
				.test(a, b) ? 1 : -1).collect(Collectors.toList());
	}
}
