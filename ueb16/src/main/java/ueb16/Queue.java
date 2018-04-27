package ueb16;

public interface Queue<T> extends Iterable<T>{
	/**
	 * This Method is used to add a new Object to this {@link Queue}<br>
	 * New Elements are added to the end of the List<br>
	 * This method throws a {@link IllegalArgumentException}, if the passed
	 * object is null<br>
	 * This method throws a {@link IllegalArgumentException}, if the passed
	 * Object is incompatible with this {@link Queue}<br>
	 * This method throws a {@link IllegalStateException}, if {@link Queue}
	 * is full<br>
	 * 
	 * @param o
	 *            the Object to add to this {@link Queue}
	 * @throws IllegalArgumentException
	 *             if the passed object is null or incompatible
	 * @throws IllegalStateException
	 *             if this {@link Queue} is full
	 */
	void addLast(T o); // Objekt hinten anfuegen

	/**
	 * This method is used to remove the first Object in this
	 * {@link Queue}<br>
	 * The removed object is returned by this method<br>
	 * This method throws a {@link IllegalStateException}, if this
	 * {@link Queue} is currently empty<br>
	 * 
	 * @return the first Object of this {@link Queue}, that has been
	 *         removed
	 * @throws IllegalStateException
	 *             if the list is empty
	 */
	Object removeFirst(); // entferne das erste Element und gebe eine //
							// Referenz darauf zurueck

	/**
	 * This method is used to retrieve the object at the position i<br>
	 * The returned object remains in this {@link Queue}<br>
	 * If i is smaler than 0 or grater then the current size of this
	 * {@link Queue}, this method throws a
	 * {@link IndexOutOfBoundsException}<br>
	 * 
	 * @param i
	 *            Index of the element to read
	 * @return The element at the possition "i" in this {@link Queue}
	 * @throws IndexOutOfBoundsException
	 *             if 0 > i or i > size
	 */
	T get(int i); // Das i-te Element zurueckgeben

	/**
	 * This method is used to tell if this {@link Queue} contains any
	 * entries<br>
	 * This method returns true if there are no entries in this list<br>
	 * Otherwise, false is returned
	 * 
	 * @return if this {@link Queue} contains no elements<br>
	 */
	boolean empty(); // Testen, ob schon Elemente eingefuegt wurden

	/**
	 * This method is used to tell if this {@link Queue} is full<br>
	 * This method returns true if this {@link Queue} has room for more
	 * entries<br>
	 * Else, false is returned
	 * <p>
	 * More practically, this method returns if a call of
	 * {@link #addLast(Object)} would rather throw an
	 * {@link IllegalStateException} than to add the passed Object<br>
	 * 
	 * @return if there is room for more objects
	 * @see #addLast(Object)
	 */
	boolean full();// Testen, ob noch Elemente einfuegbar sind,
					// d. h. ob das letzte Element schon einen Wert != null
					// hat

	/**
	 * This method returns the actual size of this {@link Queue}<br>
	 * This method returns the count of the objects managed in this
	 * object<br>
	 * More practically, this method returns the amount of times you may
	 * call {@link #removeFirst()} before a {@link IllegalStateException}
	 * is thrown<br>
	 * 
	 * @return the count of elements in this {@link Queue}
	 */
	int size();// Anzahl eingefuegter Elemente

}