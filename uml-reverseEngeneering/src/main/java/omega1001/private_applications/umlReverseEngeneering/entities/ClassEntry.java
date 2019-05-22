package omega1001.private_applications.umlReverseEngeneering.entities;

import java.util.LinkedList;
import java.util.List;

public class ClassEntry {

	private final Class<?> managedClass;
	private int lineCount = 0;
	private String content = "";
	
	private final Position dimension;
	
	private List<Assoziation> assotiations = new LinkedList<>();
	
	/**
	 * @return the assotiations
	 */
	public List<Assoziation> getAssotiations() {
		return assotiations;
	}


	public ClassEntry(Class<?> cls) {
		managedClass = cls;
		dimension = new Position();
	}


	/**
	 * @return the dimension
	 */
	public Position getDimension() {
		return dimension;
	}


	/**
	 * @return the managedClass
	 */
	public Class<?> getManagedClass() {
		return managedClass;
	}


	/**
	 * @return the lineCount
	 */
	public int getLineCount() {
		return lineCount;
	}


	/**
	 * @param lineCount the lineCount to set
	 */
	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}


	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassEntry [dimension=");
		builder.append(dimension);
		builder.append(", managedClass=");
		builder.append(managedClass);
		builder.append("]");
		return builder.toString();
	}
	
	

	

}
