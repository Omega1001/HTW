
/**
 * Simple Klasse zur Bestandsfuehrung eines Produkts
 * 
 * @author Jannik Adam
 * @author Fromm-Borys
 * @version 1.0
 */
public class Artikel {
	private static final int MIN_ARTIKELNUMMER = 1000;
	private static final int MAX_ARTIKELNUMMER = 9999;
	
	private int artikelNummer;
	private String artikelBezeichnung;
	private int artikelBestand;

	/**
	 * Konstruktor fuer ein Artikel Objekt<br>
	 * Erstellt einen neuen Arktikel mit einer Artikelnummer, einem Namen
	 * und einem Anfangsbestand
	 * <p>
	 * Die Artikelnummer muss 4-Stellig sein "[0-9]{4}"<br>
	 * Der Name des Artikels darf nicht leer oder null sein<br>
	 * Der Anfangsbestand muss positiv oder 0 sein<br>
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des Artikels
	 * @param artikelBestand
	 *            Startbestand des Artikels
	 */
	public Artikel(int artikelNummer, String artikelBezeichnung,
			       int artikelBestand
			       ){
		assert (artikelNummer >= MIN_ARTIKELNUMMER
				&& artikelNummer <= MAX_ARTIKELNUMMER) : "Die Artikelnummer muss eine "
						+ "4-stellige positive Zahl sein!";
        assert artikelBestand >= 0 : "Der Artikelbestand darf nie kleiner"
				+ " als 0 werden!";		
				
	    setArtikelBezeichnung(artikelBezeichnung);	
	    
		this.artikelNummer = artikelNummer;
		this.artikelBestand = artikelBestand;
	}

	/**
	 * Konstruktor fuer ein Artikel Objekt<br>
	 * Erstellt einen neuen Arktikel mit einer Artikelnummer, einem Namen
	 * und einem Anfangsbestand von 0
	 * <p>
	 * 
	 * Weitere Informationen finden Sie hier
	 * {@link #Artikel(int, String, int)}
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des Artikels
	 * @see #Artikel(int, String, int)
	 * 
	 */
	public Artikel(int artikelNummer, String artikelBezeichnung) {
		this(artikelNummer, artikelBezeichnung, 0);
	}

	/**
	 * Methode um den aktuellen Status eines Objekt von Typ {@link Artikel}
	 * in Textform dazustellen.
	 * 
	 * @return Textuelle Darstellung des Objects
	 */
	public String toString() {
		return "Artikel: " + artikelNummer + ", Bezeichnung: "
				+ artikelBezeichnung + ", Bestand: " + artikelBestand;
	}

	/**
	 * Gibt die Artikelnummer des Artikels zurrueck
	 * 
	 * @return die vergebene Artikelnummer
	 */
	public int getArtikelNummer() {
		return artikelNummer;
	}

	/**
	 * Gibt die Artikelbezeichnung zurrueck
	 * 
	 * @return die vergebene Artikelbezeichnung
	 */
	public String getArtikelBezeichnung() {
		return artikelBezeichnung;
	}

	/**
	 * Gibt den aktuellen Artikelbestand zurrueck
	 * 
	 * @return den aktuellen bestand des Artikels
	 */
	public int getArtikelBestand() {
		return artikelBestand;
	}

	/**
	 * Methode zum Buchen des Einganges eines Artikels
	 * <p>
	 * Die menge der eingehenden Artikel muss groesser als 0 sein
	 * 
	 * @param menge
	 *            anzahl der eingegangenen Artikel
	 */
	public void bucheZugang(int zugang ) {
		assert  zugang > 0 : "Menge muss > 0 sein!";
		this.artikelBestand = this.artikelBestand +  zugang ;
	}

	/**
	 * Methode zum Buchen des Ausgangs eines Artikels
	 * <p>
	 * Die menge der ausgehenden Artikel darf nicht negativ sein<br>
	 * Die menge der ausgehenden Artikel darf den Wert der vorhandenen
	 * Artikel nicht überschreiten
	 * 
	 * @param menge
	 *            der ausgehenden Artikel
	 */
	public void bucheAbgang( int abgang )  
	{
		assert abgang > 0 : "Menge muss > 0 sein!";
		assert abgang <= artikelBestand : "Menge muss <= dem Artikelbestand sein!";
		
		this.artikelBestand = this.artikelBestand - abgang;
	}

	/**
	 * Methode zum aendern der Artikelbezeichnung
	 * <p>
	 * Der Name des Artikels darf nicht leer oder null sein<br>
	 * 
	 * @param artikelBezeichnung
	 *            Neue bezeichnung für den Artikel
	 */
	public void setArtikelBezeichnung(String artikelBezeichnung) {
		assert (artikelBezeichnung != null && artikelBezeichnung.trim()
				.length() > 0) : "Die Artikelbezeichnung darf nicht null sein!";
		this.artikelBezeichnung = artikelBezeichnung;
	}
}
