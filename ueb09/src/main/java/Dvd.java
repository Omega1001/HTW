
public class Dvd extends Artikel {

	private int spieldauer;
	private String titel;
	private int erscheinungsjahr;

	public Dvd(int artikelNummer, String artikelBezeichnung,
			Integer artikelBestand, Double preis,
			String titel,int spieldauer, int erscheinungsjahr) {
		super(artikelNummer, artikelBezeichnung, artikelBestand, preis);

		if (titel != null && !"".equals(titel.trim())) {
			throw new InvalidInputException("Title must not be empty",
					titel, "nonempty Text");
		}
		
		if (erscheinungsjahr > 1950 && erscheinungsjahr < 2014 ) {
			throw new InvalidInputException("Erscheinungsjahr must be between 1950 and 2014",
					erscheinungsjahr, "1950 < erscheinungsjahr < 2014");
		}

		if (spieldauer < 0) {
			throw new InvalidInputException(
					"Spieldauer must be bigger than 0", spieldauer,
					"1 or higher");
		}
		this.spieldauer = spieldauer;
		this.titel = titel;
		this.erscheinungsjahr = erscheinungsjahr;
	}

	public Dvd(int artikelNummer, String artikelBezeichnung,
			int artikelBestand, String titel,int spieldauer, int erscheinungsjahr) {
		this(artikelNummer, artikelBezeichnung, artikelBestand, null,
				titel,spieldauer, erscheinungsjahr);
	}

	public Dvd(int artikelNummer, String artikelBezeichnung, double preis,
			String titel,int spieldauer, int erscheinungsjahr) {
		this(artikelNummer, artikelBezeichnung, null, preis,
				titel, spieldauer,erscheinungsjahr);
	}

	public Dvd(int artikelNummer, String artikelBezeichnung,
			String titel,int spieldauer, int erscheinungsjahr) {
		this(artikelNummer, artikelBezeichnung, null, null,
				titel,spieldauer,erscheinungsjahr);
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
		builder.append("Dvd [spieldauer=");
		builder.append(spieldauer);
		builder.append(", titel=");
		builder.append(titel);
		builder.append(", erscheinungsjahr=");
		builder.append(erscheinungsjahr);
		builder.append("]");
		return builder.toString();
	}


	
	

}
