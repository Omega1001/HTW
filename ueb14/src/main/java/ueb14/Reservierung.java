package ueb14;

public class Reservierung {

	private String bemerkung;
	private Uhrzeit start;
	private Uhrzeit ende;
	private Raum raum;
	private Mitarbeiter reservator;
	
	
	
	public Reservierung(Uhrzeit start, Uhrzeit ende) {
		if(start == null || ende == null) {
			throw new IllegalArgumentException("No start and/or end time specified");
		}
		this.start = start;
		this.ende = ende;
	}
	/**
	 * @return the reservator
	 */
	public Mitarbeiter getReservator() {
		return reservator;
	}
	/**
	 * @param reservator the reservator to set
	 */
	public void setReservator(Mitarbeiter reservator) {
		this.reservator = reservator;
	}
	/**
	 * @return the bemerkung
	 */
	public String getBemerkung() {
		return bemerkung;
	}
	/**
	 * @param bemerkung the bemerkung to set
	 */
	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}
	/**
	 * @return the start
	 */
	public Uhrzeit getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Uhrzeit start) {
		this.start = start;
	}
	/**
	 * @return the ende
	 */
	public Uhrzeit getEnde() {
		return ende;
	}
	/**
	 * @return the raum
	 */
	public Raum getRaum() {
		return raum;
	}
	/**
	 * @param raum the raum to set
	 */
	public void setRaum(Raum raum) {
		this.raum = raum;
	}
	/**
	 * @param ende the ende to set
	 */
	public void setEnde(Uhrzeit ende) {
		this.ende = ende;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ende == null) ? 0 : ende.hashCode());
		result = prime * result + ((raum == null) ? 0 : raum.hashCode());
		result = prime * result + ((reservator == null) ? 0 : reservator
				.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservierung other = (Reservierung) obj;
		if (ende == null) {
			if (other.ende != null)
				return false;
		} else if (!ende.equals(other.ende))
			return false;
		if (raum == null) {
			if (other.raum != null)
				return false;
		} else if (!raum.equals(other.raum))
			return false;
		if (reservator == null) {
			if (other.reservator != null)
				return false;
		} else if (!reservator.equals(other.reservator))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reservierung [bemerkung=");
		builder.append(bemerkung);
		builder.append(", start=");
		builder.append(start);
		builder.append(", ende=");
		builder.append(ende);
		builder.append(", raum=");
		builder.append(raum);
		builder.append(", reservator=");
		builder.append(reservator);
		builder.append("]");
		return builder.toString();
	}
	
	
}
