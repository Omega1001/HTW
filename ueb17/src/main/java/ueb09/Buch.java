package ueb09;
/**
 * Simple Unterklasse zur Bestandsfuehrung von Buchartikeln
 * 
 * @author Jannik ADAM
 * @author Fromm-Borys
 * @version 1.0
 */
public class Buch extends Artikel {

	private String author;
	private String titel;
	private String verlag;

	/**
	 * Konstruktor fuer ein Buchartikel Objekt<br>
	 * Erstellt einen neuen Bucharktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand, einem Anfangspreis, einem Author, einem Titel und einem Verlag.
	 * <p>
	 * Die Artikelnummer muss 4-Stellig sein "[0-9]{4}"<br>
	 * Der Name des Buchartikels darf nicht leer oder null sein<br>
	 * Der Anfangsbestand muss positiv oder 0 sein<br>
	 * Der Preis eines Buchartikels muss groesser 0 sein<br>
	 * Der Author eines Buchartikels darf nicht leer oder null sein<br>
	 * Der Titel eines Buchartikels darf nicht leer oder null sein<br>
	 * Der Verlag eines Buchartikels darf nicht leer oder null sein<br>
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des Buchartikels
	 * @param artikelBestand
	 *            Startbestand des Buchartikels
	 * @param preis
	 *            einzelpreis des Buchartikels
	 * @param author
	 * 			  Author des Buchartikels
	 * @param titel
	 * 			  Titel des Buchartikels
	 * @param verlag
	 * 			  Verlag des Buchartikels
	 */
	public Buch(int artikelNummer, String artikelBezeichnung,
			Integer artikelBestand, Double preis, String author,
			String titel, String verlag) {
		super(artikelNummer, artikelBezeichnung, artikelBestand, preis);

		if (author == null || "".equals(author.trim())) {
			throw new InvalidInputException("author must not be empty",
					author, "nonempty Text");
		}

		if (titel == null || "".equals(titel.trim())) {
			throw new InvalidInputException("Title must not be empty",
					titel, "nonempty Text");
		}

		if (verlag == null || "".equals(verlag.trim())) {
			throw new InvalidInputException("Verlag must not be empty",
					verlag, "nonempty Text");
		}
		this.author = author;
		this.titel = titel;
		this.verlag = verlag;
	}

	/**
	 * Konstruktor fuer ein Buchartikel Objekt<br>
	 * Erstellt einen neuen Bucharktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand, einem Anfangspreis von 1.0, einem Author, einem Titel und einem Verlag.
	 * <p>
	 * 
	 * Weitere Informationen finden Sie hier
	 * {@link #Artikel(int, String, Integer, Double, String, String, String)}
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des Buchartikels
	 * @param artikelBestand
	 *            Startbestand des Buchartikels
	 * @param author
	 * 			  Author des Buchartikels
	 * @param titel
	 * 			  Titel des Buchartikels
	 * @param verlag
	 * 			  Verlag des Buchartikels
	 */
	public Buch(int artikelNummer, String artikelBezeichnung,
			int artikelBestand, String author, String titel,
			String verlag) {
		this(artikelNummer, artikelBezeichnung, artikelBestand, null,
				author, titel, verlag);
	}

	/**
	 * Konstruktor fuer ein Buchartikel Objekt<br>
	 * Erstellt einen neuen Bucharktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand von 0, einem Anfangspreis, einem Author, einem Titel und einem Verlag.
	 * <p>
	 * 
	 * Weitere Informationen finden Sie hier
	 * {@link #Artikel(int, String, Integer, Double, String, String, String)}
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des Buchartikels
	 * @param preis
	 *            Preis des Buchartikels
	 * @param author
	 * 			  Author des Buchartikels
	 * @param titel
	 * 			  Titel des Buchartikels
	 * @param verlag
	 * 			  Verlag des Buchartikels
	 */
	public Buch(int artikelNummer, String artikelBezeichnung, double preis,
			String author, String titel, String verlag) {
		this(artikelNummer, artikelBezeichnung, null, preis, author,
				titel, verlag);
	}

	/**
	 * Konstruktor fuer ein Buchartikel Objekt<br>
	 * Erstellt einen neuen Bucharktikel mit einer Artikelnummer, einem Namen, einem Anfangsbestand von 0, einem Anfangspreis von 1.0, einem Author, einem Titel und einem Verlag.
	 * <p>
	 * 
	 * Weitere Informationen finden Sie hier
	 * {@link #Artikel(int, String, Integer, Double, String, String, String)}
	 * 
	 * @param artikelNummer
	 *            4-stellige Artikelnummer
	 * @param artikelBezeichnung
	 *            Anzeigename des Buchartikels
	 * @param author
	 * 			  Author des Buchartikels
	 * @param titel
	 * 			  Titel des Buchartikels
	 * @param verlag
	 * 			  Verlag des Buchartikels
	 */
	public Buch(int artikelNummer, String artikelBezeichnung,
			String author, String titel, String verlag) {
		this(artikelNummer, artikelBezeichnung, null, null, author,
				titel, verlag);
	}

	@Override
	public String getBeschreibung() {
		return author.concat(" : ").concat(titel);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Buch [author=");
		builder.append(author);
		builder.append(", titel=");
		builder.append(titel);
		builder.append(", verlag=");
		builder.append(verlag);
		builder.append("]");
		return builder.toString();
	}
	
	

}
