
public class Cd extends Artikel {

	private String interpret;
	private String titel;
	private int anzahlTitel;

	public Cd(int artikelNummer, String artikelBezeichnung,
			Integer artikelBestand, Double preis, String interpret,
			String titel, int anzahlTitel) {
		super(artikelNummer, artikelBezeichnung, artikelBestand, preis);

		if (interpret != null && !"".equals(interpret.trim())) {
			throw new InvalidInputException("Interpret must not be empty",
					interpret, "nonempty Text");
		}

		if (titel != null && !"".equals(titel.trim())) {
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

	public Cd(int artikelNummer, String artikelBezeichnung,
			int artikelBestand, String interpret, String titel,
			int anzahlTitel) {
		this(artikelNummer, artikelBezeichnung, artikelBestand, null,
				interpret, titel, anzahlTitel);
	}

	public Cd(int artikelNummer, String artikelBezeichnung, double preis,
			String interpret, String titel, int anzahlTitel) {
		this(artikelNummer, artikelBezeichnung, null, preis, interpret,
				titel, anzahlTitel);
	}

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
