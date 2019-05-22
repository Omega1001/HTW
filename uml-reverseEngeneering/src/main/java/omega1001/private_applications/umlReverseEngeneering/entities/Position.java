package omega1001.private_applications.umlReverseEngeneering.entities;

public class Position {

	private Coordinate coordinate;
	private int width;
	private int heigth;

	public Position() {
		this(0, 0, 0, 0);
	}

	public Position(int x, int y, int heigth, int width) {
		set(x, y, heigth, width);
	}

	public void set(Position pos) {
		setPos(pos.getX(), pos.getY());
		setDim(pos.getHeigth(), pos.getWidth());
	}
	
	public void set(int x, int y, int heigth, int width) {
		this.coordinate = new Coordinate(x, y);
		this.width = width;
		this.heigth = heigth;
	}

	public void setPos(int x, int y) {
		setX(x);
		setY(y);
	}

	public void setDim(int heigth, int width) {
		this.setWidth(width);
		this.setHeigth(heigth);
	}
	
	public Coordinate getConnectionPointCoordinates(ConnectionPoint p) {
		switch (p) {
		case NORTH:
			return new Coordinate(coordinate.getX() + (int)Math.floor(width/2), coordinate.getY());
		case SOUTH:
			return new Coordinate(coordinate.getX() + (int)Math.floor(width/2), coordinate.getY()+ heigth);
		case WEST:
			return new Coordinate(coordinate.getX() , coordinate.getY() + (int)Math.floor(heigth/2));
		case EAST:
			return new Coordinate(coordinate.getX()+ width , coordinate.getY() + (int)Math.floor(heigth/2));
		}
		return new Coordinate(0,0);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return coordinate.getX();
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.coordinate.setX(x);
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return coordinate.getY();
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.coordinate.setY(y);
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate the coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the heigth
	 */
	public int getHeigth() {
		return heigth;
	}

	/**
	 * @param heigth the heigth to set
	 */
	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position [coordinate=");
		builder.append(coordinate);
		builder.append(", width=");
		builder.append(width);
		builder.append(", heigth=");
		builder.append(heigth);
		builder.append("]");
		return builder.toString();
	}

	
	
}
