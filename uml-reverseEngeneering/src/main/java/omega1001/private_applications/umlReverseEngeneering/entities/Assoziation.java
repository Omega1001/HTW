package omega1001.private_applications.umlReverseEngeneering.entities;

public class Assoziation {

	private final ClassEntry source;
	private final ClassEntry target;
	
	private ConnectionPoint srcConnector;
	private ConnectionPoint trgConnector;
	
	public Assoziation(ClassEntry source, ClassEntry target) {
		this(source,target,ConnectionPoint.NORTH, ConnectionPoint.NORTH);
	}
	
	public Assoziation(ClassEntry source, ClassEntry target, ConnectionPoint srcConnector,
			ConnectionPoint trgConnector) {
		super();
		this.source = source;
		this.target = target;
		this.srcConnector = srcConnector;
		this.trgConnector = trgConnector;
	}


	/**
	 * @return the srcConnector
	 */
	public ConnectionPoint getSrcConnector() {
		return srcConnector;
	}


	/**
	 * @param srcConnector the srcConnector to set
	 */
	public void setSrcConnector(ConnectionPoint srcConnector) {
		this.srcConnector = srcConnector;
	}


	/**
	 * @return the trgConnector
	 */
	public ConnectionPoint getTrgConnector() {
		return trgConnector;
	}


	/**
	 * @param trgConnector the trgConnector to set
	 */
	public void setTrgConnector(ConnectionPoint trgConnector) {
		this.trgConnector = trgConnector;
	}


	/**
	 * @return the source
	 */
	public ClassEntry getSource() {
		return source;
	}


	/**
	 * @return the target
	 */
	public ClassEntry getTarget() {
		return target;
	}
	
	
	
}
