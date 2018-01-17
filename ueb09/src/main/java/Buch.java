
public class Buch extends Artikel {

	private String author;
	private String titel;
	private String verlag;

	public Buch(int artikelNummer, String artikelBezeichnung,
			Integer artikelBestand, Double preis, String interpret,
			String titel, String anzahlTitel) {
		super(artikelNummer, artikelBezeichnung, artikelBestand, preis);

		if (interpret != null && !"".equals(interpret.trim())) {
			throw new InvalidInputException("Interpret must not be empty",
					interpret, "nonempty Text");
		}

		if (titel != null && !"".equals(titel.trim())) {
			throw new InvalidInputException("Title must not be empty",
					titel, "nonempty Text");
		}

		if (verlag != null && !"".equals(verlag.trim())) {
			throw new InvalidInputException("Verlag must not be empty",
					verlag, "nonempty Text");
		}
		this.author = interpret;
		this.titel = titel;
		this.verlag = anzahlTitel;
	}

	public Buch(int artikelNummer, String artikelBezeichnung,
			int artikelBestand, String interpret, String titel,
			String verlag) {
		this(artikelNummer, artikelBezeichnung, artikelBestand, null,
				interpret, titel, verlag);
	}

	public Buch(int artikelNummer, String artikelBezeichnung, double preis,
			String interpret, String titel, String verlag) {
		this(artikelNummer, artikelBezeichnung, null, preis, interpret,
				titel, verlag);
	}

	public Buch(int artikelNummer, String artikelBezeichnung,
			String interpret, String titel, String verlag) {
		this(artikelNummer, artikelBezeichnung, null, null, interpret,
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
