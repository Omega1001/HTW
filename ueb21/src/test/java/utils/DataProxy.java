
package utils;

public class DataProxy<T> {

	private T data;
	
	

	public DataProxy(T data) {
		super();
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
