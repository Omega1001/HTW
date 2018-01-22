/**
 * Simple Unterklasse zur Bestandsfuehrung von DVD-Artikeln
 * 
 * @author Jannik ADAM
 * @author Fromm-Borys
 * @version 1.0
 */
public class Dvd extends Artikel {

	private int spielDauer;
	private String titel;
	private int erscheinungsJahr;

	/**
	 * Konstruktor fuer ein DVD-Artikel Objekt<br>
	 * Erstellt einen neuen DVD-Arktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand, einem Anfangspreis, einer Spieldauer, einem Titel und einem Ersccheinungsjahr.
	 * <p>
	 * Die Artikelnummer muss 4-Stellig sein "[0-9]{4}"<br>
	 * Der Name des DVD-Artikels darf nicht leer oder null sein<br>
	 * Der Anfangsbestand muss positiv oder 0 sein<br>
	 * Der Preis eines DVD-Artikels muss groesser 0 sein<br>
	 * Die Spieldauer eines DVD-Artikels muss groesser 0 sein<br>
	 * Der Titel eines DVD-Artikels darf nicht leer oder null sein<br>
	 * Das Erscheinungsjahr eines DVD-Artikels muss groesser 0 sein<br>
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des DVD-Artikels
	 * @param artikelBestand
	 *            Startbestand des DVD-Artikels
	 * @param preis
	 *            einzelpreis des DVD-Artikels
	 * @param spielDauer
	 * 			  Spieldauer des DVD-Artikels
	 * @param titel
	 * 			  Titel des DVD-Artikels
	 * @param Erscheinungsjahg
	 * 			  Erscheinungsjahr des DVD-Artikels
	 */
	public Dvd(int artikelNummer, String artikelBezeichnung,
			Integer artikelBestand, Double preis,
			String titel,int spielDauer, int erscheinungsJahr) {
		super(artikelNummer, artikelBezeichnung, artikelBestand, preis);

		if (titel == null || "".equals(titel.trim())) {
			throw new InvalidInputException("Title must not be empty",
					titel, "nonempty Text");
		}
		
		if (erscheinungsJahr > 1950 && erscheinungsJahr < 2014 ) {
			throw new InvalidInputException("erscheinungsJahr must be between 1950 and 2014",
					erscheinungsJahr, "1950 < erscheinungsJahr < 2014");
		}

		if (spielDauer < 0) {
			throw new InvalidInputException(
					"spielDauer must be bigger than 0", spielDauer,
					"1 or higher");
		}
		this.spielDauer = spielDauer;
		this.titel = titel;
		this.erscheinungsJahr = erscheinungsJahr;
	}

	/**
	 * Konstruktor fuer ein DVD-Artikel Objekt<br>
	 * Erstellt einen neuen DVD-Arktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand, einem Anfangspreis von 1.0, einer Spieldauer, einem Titel und einem Ersccheinungsjahr.
	 * <p>
	 * 
	 * Weitere Informationen finden Sie hier
	 * {@link #Artikel(int, String, Integer, Double, int, String, int)}
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des DVD-Artikels
	 * @param artikelBestand
	 *            Startbestand des DVD-Artikels
	 * @param spielDauer
	 * 			  Spieldauer des DVD-Artikels
	 * @param titel
	 * 			  Titel des DVD-Artikels
	 * @param Erscheinungsjahg
	 * 			  Erscheinungsjahr des DVD-Artikels
	 */
	public Dvd(int artikelNummer, String artikelBezeichnung,
			int artikelBestand, String titel,int spielDauer, int erscheinungsJahr) {
		this(artikelNummer, artikelBezeichnung, artikelBestand, null,
				titel,spielDauer, erscheinungsJahr);
	}

	/**
	 * Konstruktor fuer ein DVD-Artikel Objekt<br>
	 * Erstellt einen neuen DVD-Arktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand von 0, einem Anfangspreis, einer Spieldauer, einem Titel und einem Ersccheinungsjahr.
	 * <p>
	 * 
	 * Weitere Informationen finden Sie hier
	 * {@link #Artikel(int, String, Integer, Double, int, String, int)}
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des DVD-Artikels
	 * @param preis
	 * 			  Preis des DVD-Artikels
	 * @param spielDauer
	 * 			  Spieldauer des DVD-Artikels
	 * @param titel
	 * 			  Titel des DVD-Artikels
	 * @param Erscheinungsjahg
	 * 			  Erscheinungsjahr des DVD-Artikels
	 */
	public Dvd(int artikelNummer, String artikelBezeichnung, double preis,
			String titel,int spielDauer, int erscheinungsJahr) {
		this(artikelNummer, artikelBezeichnung, null, preis,
				titel, spielDauer,erscheinungsJahr);
	}

	/**
	 * Konstruktor fuer ein DVD-Artikel Objekt<br>
	 * Erstellt einen neuen DVD-Arktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand von 0, einem Anfangspreis von 1.0, einer Spieldauer, einem Titel und einem Ersccheinungsjahr.
	 * <p>
	 * 
	 * Weitere Informationen finden Sie hier
	 * {@link #Artikel(int, String, Integer, Double, int, String, int)}
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des DVD-Artikels
	 * @param spielDauer
	 * 			  Spieldauer des DVD-Artikels
	 * @param titel
	 * 			  Titel des DVD-Artikels
	 * @param Erscheinungsjahg
	 * 			  Erscheinungsjahr des DVD-Artikels
	 */
	public Dvd(int artikelNummer, String artikelBezeichnung,
			String titel,int spielDauer, int erscheinungsJahr) {
		this(artikelNummer, artikelBezeichnung, null, null,
				titel,spielDauer,erscheinungsJahr);
	}
	
	@Override
	public String getBeschreibung() {
		return titel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dvd [spielDauer=");
		builder.append(spielDauer);
		builder.append(", titel=");
		builder.append(titel);
		builder.append(", erscheinungsJahr=");
		builder.append(erscheinungsJahr);
		builder.append("]");
		return builder.toString();
	}


	
	

}
