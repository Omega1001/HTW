package ueb14;

public class Person {
	
	private String vorname;
	private String nachnahme;
	
	public Person(String vorname, String nachnahme) {
		if(vorname == null || "".equals(vorname) || nachnahme == null || "".equals(nachnahme)) {
			throw new IllegalArgumentException("Must have first- and last name");
		}
		this.vorname = vorname;
		this.nachnahme = nachnahme;
	}
	
	

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}



	/**
	 * @return the nachnahme
	 */
	public String getNachnahme() {
		return nachnahme;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nachnahme == null) ? 0 : nachnahme
				.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname
				.hashCode());
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
		Person other = (Person) obj;
		if (nachnahme == null) {
			if (other.nachnahme != null)
				return false;
		} else if (!nachnahme.equals(other.nachnahme))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(vorname);
		builder.append(" ");
		builder.append(nachnahme);
		return builder.toString();
	}
	
	
}
