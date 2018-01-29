//comment




/**
 * Simple Klasse zur Bestandsfuehrung eines Produkts
 * 
 * @author Jannik Adam
 * @author Fromm-Borys 
 * @version 1.0
 */
public class Artikel
{
    private static final int MIN_ARTIKELNUMMER = 1000;
    private static final int MAX_ARTIKELNUMMER = 9999;
    private int artikelnummer;
    private String artikelbezeichnung;
    private int artikelbestand;
    /**
     * Konstruktor fuer ein Artikel Objekt<br>
     * Erstellt einen neuen Arktikel mit einer Artikelnummer, einem Namen und einem Anfangsbestand<p>
     * Die Artikelnummer muss 4-Stellig sein "[0-9]{4}"<br>
     * Der Name des Artikels darf nicht leer oder null sein<br>
     * Der Anfangsbestand muss positiv oder 0 sein<br>
     * 
     * @param artikelnummer 4-stellige Artikelnummer
     * @param artikelbezeichnung Anzeigename des Artikels
     * @param artikelbestand Startbestand des Artikels
     */
    public Artikel(int artikelnummer, String artikelbezeichnung, int artikelbestand)
    {
        assert (artikelnummer >= MIN_ARTIKELNUMMER && artikelnummer <= MAX_ARTIKELNUMMER): "Die Artikelnummer muss eine 4-stellige positive Zahl sein!";
        this.artikelnummer = artikelnummer; 
        setArtikelbezeichnung(artikelbezeichnung);
        assert artikelbestand >= 0: "Der Artikelbestand darf nie kleiner als 0 werden!";
        this.artikelbestand = artikelbestand;
    }
    
    /**
     * Konstruktor fuer ein Artikel Objekt<br>
     * Erstellt einen neuen Arktikel mit einer Artikelnummer, einem Namen und einem Anfangsbestand von 0<p>
     * 
     * Weitere Informationen finden Sie hier {@link #Artikel(int, String, int)}
     * 
     * @param artikelnummer 4-stellige Artikelnummer
     * @param artikelbezeichnung Anzeigename des Artikels
     * @see #Artikel(int, String, int)
     * 
     */
    public Artikel(int artikelnummer, String artikelbezeichnung) {
        this(artikelnummer, artikelbezeichnung, 0);
    }
    /**
     * Methode um den aktuellen Status eines Objekt von Typ {@link Artikel} in Textform dazustellen.
     * 
     * @return Textuelle Darstellung des Objects
     */
    public String toString() 
    {
        return "Artikel: " + artikelnummer +
               ", Bezeichnung: " + artikelbezeichnung +
               ", Bestand: " + artikelbestand;
    }
    
    /**
     * Gibt die Artikelnummer des Artikels zurrueck
     * @return die vergebene Artikelnummer
     */
    public int getArtikelnummer()
    {
        return artikelnummer;
    }
    
    /**
     * Gibt die Artikelbezeichnung zurrueck
     * @return die vergebene Artikelbezeichnung
     */
    public String getArtikelbezeichnung()
    {
        return artikelbezeichnung;
    }
    /**
     * Gibt den aktuellen Artikelbestand zurrueck
     * @return den aktuellen bestand des Artikels
     */
    public int getArtikelbestand()
    {
        return artikelbestand;
    }
    /**
     * Methode zum Buchen des Einganges eines Artikels<p>
     * Die menge der eingehenden Artikel muss groesser als 0 sein
     * 
     * @param menge anzahl der eingegangenen Artikel
     */
    public void zugang(int menge) 
    { 
        assert menge > 0: "Menge muss > 0 sein!";
        this.artikelbestand = this.artikelbestand + menge;    
    }
    /**
     * Methode zum Buchen des Ausgangs eines Artikels<p>
     * Die menge der ausgehenden Artikel darf nicht negativ sein<br>
     * Die menge der ausgehenden Artikel darf den Wert der vorhandenen Artikel nicht überschreiten
     * @param menge der ausgehenden Artikel
     */
    public void abgang(int menge) 
    { 
        assert menge > 0: "Menge muss > 0 sein!";
        assert menge <= artikelbestand: "Menge muss <= dem Artikelbestand sein!";
        this.artikelbestand = this.artikelbestand - menge;    
    }
    /**Methode zum aendern der Artikelbezeichnung<p>
     * Der Name des Artikels darf nicht leer oder null sein<br>
     * @param artikelbezeichnung Neue bezeichnung für den Artikel
     */
    public void setArtikelbezeichnung(String artikelbezeichnung) 
    {
        assert (artikelbezeichnung != null && artikelbezeichnung.trim().length() > 0 ): "Die Artikelbezeichnung darf nicht null sein!";
        this.artikelbezeichnung = artikelbezeichnung;
    }
}
