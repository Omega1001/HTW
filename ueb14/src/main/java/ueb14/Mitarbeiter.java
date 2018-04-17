package ueb14;

public class Mitarbeiter extends Person {
	
	private String mail;

	public Mitarbeiter(String vorname, String nachnahme, String mail) {
		super(vorname, nachnahme);
		if(mail == null || "".equals(mail)) {
			throw new IllegalArgumentException("invalid mail address");
		}
		this.mail = mail;
	}

	public void reserviere(Raum raum, Uhrzeit begin, Uhrzeit ende, String bemerkung) {
		if(raum == null) {
			throw new IllegalArgumentException("Raum must be set");
		}
		Reservierung r = new Reservierung(begin, ende);
		r.setReservator(this);
		r.setBemerkung("".equals(bemerkung) ? null: bemerkung);
		raum.addReservierung(r);
	}
	
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mitarbeiter other = (Mitarbeiter) obj;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(" (");
		builder.append(mail);
		builder.append(')');
		return builder.toString();
	}
	
	

}
