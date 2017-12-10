import java.util.Iterator;
import java.util.NoSuchElementException;

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

	private Artikel[] lagerFeld;
	private int artikelAnzahl;

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
	 * Wird die dimension ausserhalb dieses Raumes gewählt, wird eine
	 * {@link IllegalArgumentException} geworfen.
	 * 
	 * @param dimension
	 *            gibt die Dimension des zu erstellenden Feldes an.
	 * @throws IllegalArgumentException
	 *             wenn die Dimension zu klein oder zu groß gewählt wird
	 */
	public Lager(int dimension) {
		if (dimension < MIN_DIMENSION || dimension > MAX_DIMENSION) {
			throw new IllegalArgumentException(
					"Die Anzahl der Artikel im Lager muss zwischen "
							+ MIN_DIMENSION + " und " + MAX_DIMENSION
							+ "liegen!");
		}
		artikelAnzahl = 0;
		lagerFeld = new Artikel[dimension];
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
	 * Methode zum erfassen eines Artikel Onjekt in dem Lager Objekt.
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
		if (artikelAnzahl >= lagerFeld.length) {
			throw new IllegalArgumentException("Lager ist bereits voll!");
		}
		this.lagerFeld[artikelAnzahl] = artikel;
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
		this.lagerFeld[findeArtikelIndex(artNr)] =
				this.lagerFeld[artikelAnzahl - 1];
		this.lagerFeld[artikelAnzahl - 1] = null;
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
			double neuPreis =(art.getPreis() * prozent);
			if(Double.isInfinite(neuPreis)) {// Warum funktioniert das nicht?
				throw new ArithmeticException
					("Das w�re absoluter Wucher bei Artiekl" + art.getArtikelNummer() + "!");
			}art.setPreis(neuPreis);
		}
	}

	/**
	 * Methode, die ein Artikel Object anhand seiner Artikelnummer zurrück
	 * gibt.<br>
	 * Sollte es meh als einen Artikel mit der selben Nummer geben, wird
	 * hier jeweils der 1. zurrueckgegeben.
	 * 
	 * @param artNr
	 *            Nummer des zu holenden Artikel
	 * @return der Artikel mit der uebergebenen Artikelnummer
	 * @throws NoSuchElementException
	 * 	 wenn kein Element mit nummer nummer existiert
	 */
	public Artikel findeArtikel(int artNr) throws NoSuchElementException {
		return lagerFeld[findeArtikelIndex(artNr)];
	}

	/**
	 * Methode zum ermitteln des Index eines bestimmten Artikels im Lager.
	 * <p>
	 * 
	 * @param artNr
	 *            Einzugebende Nummer des zu findenden Artikels.
	 * @return Feld das der Artikel im Lager belegt.
	 * @exception NoSuchElementException
	 *                wenn kein Element mit dieser nummer existiert
	 */
	private int findeArtikelIndex(int artNr)
			throws NoSuchElementException {
		for (int feld = 0; feld < artikelAnzahl; feld++) {
			if (this.lagerFeld[feld].getArtikelNummer() == artNr) {
				return feld;
			}
		}
		throw new NoSuchElementException("Der Artikel Nummer " + artNr + " ist nicht vorhanden!");
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
		builder.append("Lager \r\nAnzahl der Artikel: ");
		builder.append(artikelAnzahl + "\r\n");
		builder.append("Gelagerte Artikel:\r\n");
		for (Artikel art : this) {
			builder.append(art).append("\r\n");
		}
		builder.append("");
		return builder.toString();
	}

	@Override
	public Iterator<Artikel> iterator() {
		return new Iterator<Artikel>() {
			private Artikel next = null;
			private int position = -1;

			@Override
			public boolean hasNext() {
				if (next != null) {
					return true;
				} else {
					position++;
					if (position < lagerFeld.length
							&& lagerFeld[position] != null) {
						next = lagerFeld[position];
						return true;
					} else {
						return false;
					}
				}
			}

			@Override
			public Artikel next() {
				if (next != null) {
					Artikel art = next;
					next = null;
					return art;
				} else if (hasNext()) {
					return next();
				} else {
					throw new NoSuchElementException();
				}
			}
		};
	}

}
