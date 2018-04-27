package ueb16;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractQueue<T> implements Queue<T> {

	private Class<T> tagetType;
	private final Object[] store;

	public AbstractQueue(Class<T> tagetType, int size) {
		this.tagetType = tagetType;
		this.store = new Object[size];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ueb10.Queue#addLast(java.lang.Object)
	 */
	@Override
	public void addLast(T o) {
		if (o == null) {
			throw new IllegalArgumentException(this.tagetType.getName()
					.concat(" muss not be null"));
		}
		if (!this.tagetType.isAssignableFrom(o.getClass())) {
			throw new IllegalArgumentException("Not the mannaged Type");
		}
		for (int i = 0; i < store.length; i++) {
			if (store[i] == null) {
				store[i] = o;
				return;
			} else if (store[i].equals(o)) {
				throw new IllegalArgumentException(this.tagetType.getName()
						.concat(" already queued"));
			}
		}
		throw new IllegalStateException(
				"Queue currently has maximal capacity");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ueb10.Queue#removeFirst()
	 */
	@Override
	public Object removeFirst() {
		if (this.empty()) {
			throw new IllegalStateException("No entry left");
		}
		return remove(store[0]);
	}

	public Object remove(Object o) {
		Object result = null;
		for (int i = 0; i < store.length; i++) {

			if (result == null) {
				if (store[i] == null) {
					break;
				} else if (store[i].equals(o)) {
					result = store[i];
					store[i] = null;
				}
			} else {
				if (store[i] == null) {
					break;
				}
				store[i - 1] = store[i];
				store[i] = null;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ueb10.Queue#get(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get(int i) {
		if (i >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return (T) store[i];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ueb10.Queue#empty()
	 */
	@Override
	public boolean empty() {
		return store[0] == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ueb10.Queue#full()
	 */
	@Override
	public boolean full() {
		return store[store.length - 1] != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ueb10.Queue#size()
	 */
	@Override
	public int size() {
		for (int i = 0; i < store.length; i++) {
			if (store[i] == null) {
				return i;
			}
		}
		return empty() ? 0 : store.length;
	}

	@Override
	public Iterator<T> iterator() {
		return new QueueIterator();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName()).append("[");
		sb.append("type = ").append(tagetType.getSimpleName()).append(
				" size= ").append(size());
		sb.append("]");
		return sb.toString();
	}

	private class QueueIterator implements Iterator<T> {

		private int lastIndex = 0;

		@Override
		public boolean hasNext() {
			return lastIndex < size();
		}

		@Override
		public T next() {
			if (hasNext()) {
				return get(lastIndex++);
			}
			throw new NoSuchElementException();
		}

	}

}
