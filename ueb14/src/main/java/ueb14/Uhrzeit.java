package ueb14;

public class Uhrzeit {
	
	private int stunde;
	private int minute;
	
	public Uhrzeit(int stunde, int minute) {
		if(stunde < 0 || stunde >23 || minute < 0 || minute > 59) {
			throw new IllegalArgumentException("Not a Time");
			//TODO: Replace with custom exception
		}
		this.stunde = stunde;
		this.minute = minute;
	}	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + minute;
		result = prime * result + stunde;
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
		Uhrzeit other = (Uhrzeit) obj;
		if (minute != other.minute)
			return false;
		if (stunde != other.stunde)
			return false;
		return true;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(stunde);
		builder.append(":");
		builder.append(minute);
		return builder.toString();
	}
	
	

}
