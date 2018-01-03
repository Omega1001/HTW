/**
 * 
 */
package ueb08;

/**
 * @author jannik
 *
 */
public class Patient {
	public static final int MIN_NUMBER = 1000;
	public static final int MAX_NUMBER = 9999;

	private final int number;
	private String name;

	public Patient(int number, String name) {
		if (number < MIN_NUMBER || number > MAX_NUMBER) {
			throw new IllegalArgumentException("Invalid Patient number");
		}
		setName(name);
		this.number = number;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		if (name == null || "".equals(name.trim())) {
			throw new IllegalArgumentException("Invalid Patient name");
		}
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Patient [number=");
		builder.append(number);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
