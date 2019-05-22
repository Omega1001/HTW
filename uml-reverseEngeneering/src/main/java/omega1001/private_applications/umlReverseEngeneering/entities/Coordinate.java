package omega1001.private_applications.umlReverseEngeneering.entities;

public class Coordinate {

	private int x;
	private int y;

	public Coordinate() {
		this(0, 0);
	}

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	};
	
	public void add (int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void add (Coordinate c) {
		add(c.getX(),c.getY());
	}
	
	public void subtract (int x, int y) {
		add(-x,-y);
	}
	
	public void subtract (Coordinate c) {
		subtract(c.getX(),c.getY());
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coordinate [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}
	
	

}
