package ueb20;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyHeap<E extends Comparable<E>> implements Queue<E> {

	private E[] store;
	private int size = 1;
	private long version = 0;
	private Class<?> type;

	private int leftIndex(int i) {
		return (i * 2) + 1;
	}

	private int rightIndex(int i) {
		return (i * 2) + 2;
	}

	private int parentIndex(int i) {
		return (i - 1) / 2;
	}

	@SuppressWarnings("unchecked")
	private void init(Class<?> type) {
		this.type = type;
		store = (E[]) Array.newInstance(type, size);
	}

	private int min(int i1, int i2) {
		if (i1 >= store.length || i2 >= store.length) {
			return i1 < store.length ? i1 : i2;
		}
		if (store[i1] == null || store[i2] == null) {
			return store[i1] != null ? i1 : i2;
		}
		return (store[i1].compareTo(store[i2]) == -1) ? i1 : i2;
	}

	private void swap(int i1, int i2) {
		E themp = store[i1];
		store[i1] = store[i2];
		store[i2] = themp;
	}

	private int indexOf(Object o) {
		int res = 0;
		for (; res < store.length; res++) {
			if (store[res] == null) {
				break;
			} else if (store[res].equals(o)) {
				return res;
			}
		}
		return -1;
	}

	public MyHeap(int size) {
		if (size < 1) {
			throw new IllegalArgumentException();
		}
		this.size = size;
	}

	@Override
	public int size() {
		if (store == null) {
			return 0;
		}
		for (int i = store.length - 1; i >= 0; i--) {
			if (store[i] != null) {
				return i + 1;
			}
		}
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return store == null || store[0] == null;
	}

	@Override
	public boolean contains(Object o) {
		if (store == null) {
			return false;
		}
		for (Object obj : store) {
			if (obj.equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new HeapIterator();
	}

	@Override
	public Object[] toArray() {
		return toArray(new Object[size()]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if(type != null && !a.getClass().getComponentType().isAssignableFrom(type)) {
			throw new ArrayStoreException();	
		}
		T[] res = a;
		if (res.length < size()) {
			res = (T[]) Array.newInstance(a.getClass().getComponentType(),
					size());
		}
		int size = size();
		for (int i = 0; i < res.length; i++) {
			res[i] = i < size ? ((T) store[i]) : null;
		}
		return res;
	}

	@Override
	public boolean remove(Object o) {
		if (store == null) {
			return false;
		}
		int index = indexOf(o);
		if (index != -1) {
			store[index] = null;
			if (index != size()) {
				swap(index, size() - 1);
			}
			rebuild();
			version++;
			return true;
		} else {
			version++;
			return false;
		}
	}

	private void rebuild() {
		if (store == null) {
			return;
		}
		for (int i = 0; i < store.length; i++) {
			if (store[i] == null) {
				break;
			}
			if (min(i, leftIndex(i)) != i) {
				moveInPossition(leftIndex(i));
			}
			if (min(i, rightIndex(i)) != i) {
				moveInPossition(rightIndex(i));
			}
		}
	}

	private void moveInPossition(int index) {
		int curIndex = index;
		while (curIndex != 0 && min(parentIndex(curIndex),
				curIndex) == curIndex) {
			swap(curIndex, parentIndex(curIndex));
			curIndex = parentIndex(curIndex);
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean res = false;
		for (E o : c) {
			res = add(o) || res;
		}
		return res;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean res = false;
		for (Object o : c) {
			res = remove(o) || res;
		}
		return res;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean res = false;

		for (Object o : this.toArray()) {
			if (!c.contains(o)) {
				res = remove(o) || res;
			}
		}
		return res;
	}

	@Override
	public void clear() {
		if (store == null) {
			return;
		}
		for (int i = 0; i < store.length; i++) {
			store[i] = null;
			version++;
		}
		version++;
	}

	@Override
	public boolean add(E e) {
		if (!offer(e)) {
			throw new IllegalStateException();
		}
		return true;
	}

	@Override
	public boolean offer(E e) {
		if (store == null) {
			if (e != null) {
				init(e.getClass());
			}
		}
		if (size() < store.length) {
			store[size()] = e;
			rebuild();
			version++;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public E remove() {
		E themp = poll();
		if (themp == null) {
			throw new NoSuchElementException();
		}
		return themp;
	}

	@Override
	public E poll() {
		if (store == null) {
			return null;
		}
		E themp = store[0];
		store[0] = null;
		if (size() > 1) {
			swap(0, size() - 1);
			rebuild();
		}
		version++;
		return themp;
	}

	@Override
	public E element() {
		E themp = peek();
		if (themp == null) {
			throw new NoSuchElementException();
		}
		return themp;
	}

	@Override
	public E peek() {
		return store == null ? null : store[0];
	}

	private class HeapIterator implements Iterator<E> {

		private int current = -1;
		private long workingVersion = version;

		@Override
		public boolean hasNext() {
			return store != null && current < size() - 1;
		}

		@Override
		public E next() {
			if (workingVersion != version) {
				throw new ConcurrentModificationException();
			}
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return store[++current];
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(Arrays.toString(store));
		return builder.toString();
	}

}
