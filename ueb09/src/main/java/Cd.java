/**
 * Simple Unterklasse zur Bestandsfuehrung von CD-Artikeln
 * 
 * @author Jannik ADAM
 * @author Fromm-Borys
 * @version 1.0
 */
public class Cd extends Artikel {

	private String interpret;
	private String titel;
	private int anzahlTitel;

	/**
	 * Konstruktor fuer ein CD-Artikel Objekt<br>
	 * Erstellt einen neuen CD-Arktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand, einem Anfangspreis, einem Interpreten, einem Titel und einer Anzahl von Titeln.
	 * <p>
	 * Die Artikelnummer muss 4-Stellig sein "[0-9]{4}"<br>
	 * Der Name des CD-Artikels darf nicht leer oder null sein<br>
	 * Der Anfangsbestand muss positiv oder 0 sein<br>
	 * Der Preis eines CD-Artikels muss groesser 0 sein<br>
	 * Der Interpret eines CD-Artikels darf nicht leer oder null sein<br>
	 * Der Titel eines CD-Artikels darf nicht leer oder null sein<br>
	 * Die Anzahl der Titel eines CD-Artikels muss groesser 0 sein<br>
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des CD-Artikels
	 * @param artikelBestand
	 *            Startbestand des CD-Artikels
	 * @param preis
	 *            einzelpreis des CD-Artikels
	 * @param interpret
	 * 			  Interpret des CD-Artikels
	 * @param titel
	 * 			  Titel des CD-Artikels
	 * @param anzahlArtikel
	 * 			  Anzahl der Titel des CD-Artikels
	 */
	public Cd(int artikelNummer, String artikelBezeichnung,
			Integer artikelBestand, Double preis, String interpret,
			String titel, int anzahlTitel) {
		super(artikelNummer, artikelBezeichnung, artikelBestand, preis);

		if (interpret == null || "".equals(interpret.trim())) {
			throw new InvalidInputException("Interpret must not be empty",
					interpret, "nonempty Text");
		}

		if (titel == null || "".equals(titel.trim())) {
			throw new InvalidInputException("Title must not be empty",
					titel, "nonempty Text");
		}

		if (anzahlTitel < 0) {
			throw new InvalidInputException(
					"Tiltle Count must be bigger than 0", interpret,
					"1 or higher");
		}
		this.interpret = interpret;
		this.titel = titel;
		this.anzahlTitel = anzahlTitel;
	}

	/**
	 * Konstruktor fuer ein CD-Artikel Objekt<br>
	 * Erstellt einen neuen CD-Arktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand, einem Preis von 1.00, einem Interpreten, einem Titel und einer Titelanzahl.
	 * <p>
	 * 
	 * Weitere Informationen finden Sie hier
	 * {@link #Artikel(int, String, Integer, Double, String, String, int)}
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des Artikels
	 * @param artikelBestand
	 *            Startbestand des Artikels
	 * @param interpret
	 * 			  Interpret des CD-Artikels
	 * @param titel
	 * 			  Titel des CD-Artikels
	 * @param anzahlArtikel
	 * 			  Anzahl der Titel des CD-Artikels
	 */
	public Cd(int artikelNummer, String artikelBezeichnung,
			int artikelBestand, String interpret, String titel,
			int anzahlTitel) {
		this(artikelNummer, artikelBezeichnung, artikelBestand, null,
				interpret, titel, anzahlTitel);
	}

	/**
	 * Konstruktor fuer ein CD-Artikel Objekt<br>
	 * Erstellt einen neuen CD-Arktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand von 0, einem Preis, einem Interpreten, einem Titel und einer Titelanzahl.
	 * <p>
	 * 
	 * Weitere Informationen finden Sie hier
	 * {@link #Artikel(int, String, Integer, Double, String, String, int)}
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des Artikels
	 * @param preis
	 *            Preis des Artikels
	 * @param interpret
	 * 			  Interpret des CD-Artikels
	 * @param titel
	 * 			  Titel des CD-Artikels
	 * @param anzahlArtikel
	 * 			  Anzahl der Titel des CD-Artikels
	 */
	public Cd(int artikelNummer, String artikelBezeichnung, double preis,
			String interpret, String titel, int anzahlTitel) {
		this(artikelNummer, artikelBezeichnung, null, preis, interpret,
				titel, anzahlTitel);
	}

	/**
	 * Konstruktor fuer ein CD-Artikel Objekt<br>
	 * Erstellt einen neuen CD-Arktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand von 0, einem Preis von 1.0, einem Interpreten, einem Titel und einer Titelanzahl.
	 * <p>
	 * 
	 * Weitere Informationen finden Sie hier
	 * {@link #Artikel(int, String, Integer, Double, String, String, int)}
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des Artikels
	 * @param artikelBestand
	 *            Startbestand des Artikels
	 * @param interpret
	 * 			  Interpret des CD-Artikels
	 * @param titel
	 * 			  Titel des CD-Artikels
	 * @param anzahlArtikel
	 * 			  Anzahl der Titel des CD-Artikels
	 */
	public Cd(int artikelNummer, String artikelBezeichnung,
			String interpret, String titel, int anzahlTitel) {
		this(artikelNummer, artikelBezeichnung, null, null, interpret,
				titel, anzahlTitel);
	}
	
	@Override
	public String getBeschreibung() {
		return interpret.concat(" : ").concat(titel);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cd [interpret=");
		builder.append(interpret);
		builder.append(", titel=");
		builder.append(titel);
		builder.append(", anzahlTitel=");
		builder.append(anzahlTitel);
		builder.append("]");
		return builder.toString();
	}
	
	

}
