package ueb19;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements List<E> {

	private ListElement root;
	private long lastModified = 0;

	@Override
	public int size() {
		if (root == null) {
			return 0;
		} else {
			int size = 1;
			ListElement themp = root.getNext();
			while (themp != root) {
				size++;
				themp = themp.getNext();
			}
			return size;
		}
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public boolean contains(Object o) {
		if (o == null) {
			return false;
		}
		for (Object t : this) {
			if (t != null && t.equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return listIterator();
	}

	@Override
	public Object[] toArray() {
		Object[] res = new Object[size()];
		int i = 0;
		for (E v : this) {
			res[i] = v;
			i++;
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		T[] res = a;
		if (res.length < size()) {
			res = (T[]) Array.newInstance(a.getClass().getComponentType(),
					size());
		}
		for (int i = 0; i < res.length; i++) {
			if (i >= size()) {
				res[i] = null;
			} else {
				res[i] = (T) get(i);
			}
		}
		return res;
	}

	@Override
	public boolean add(E e) {
		if (root == null) {
			root = new ListElement(e);
			root.setNext(root);
			root.setPrev(root);
			lastModified++;
			return true;
		} else {
			ListElement themp = new ListElement(e);
			themp.setNext(root);
			themp.setPrev(root.getPrev());
			root.getPrev().setNext(themp);
			root.setPrev(themp);
			lastModified++;
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		ListElement elm = getElementByValue(o);
		if (elm == null) {
			return false;
		} else {
			if (elm == root && elm.getNext() == elm.getPrev()) {
				root = null;
			} else {
				if (elm == root) {
					root = root.getNext();
				}
				elm.getPrev().setNext(elm.getNext());
				elm.getNext().setPrev(elm.getPrev());
				elm.setNext(null);
				elm.setPrev(null);
			}
			lastModified++;
			return true;
		}
	}

	private ListElement getElementByValue(Object o) {
		if (root == null) {
			return null;
		}
		if (root.getValue().equals(o)) {
			return root;
		}
		ListElement themp = root.getNext();
		while (themp != root) {
			if (themp.getValue().equals(o)) {
				return themp;
			}
			themp = themp.getNext();
		}
		return null;
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
		return addAll(size(), c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (index < -1 || index > size()) {
			throw new IndexOutOfBoundsException(String.valueOf(index));
		}
		int tIndex = index;
		for (E o : c) {
			add(tIndex, o);
			tIndex++;
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Object o : c) {
			result = remove(o) || result;
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = false;
		Iterator<E> it = listIterator();
		while (it.hasNext()) {
			if (!c.contains(it.next())) {
				it.remove();
				result = true;
			}
		}
		return result;
	}

	@Override
	public void clear() {
		if (root != null) {
			ListElement themp = root;
			while (themp.getNext() != null) {
				themp.getPrev().setNext(null);
				themp.setPrev(null);
				themp = themp.getNext();
			}
		}
		root = null;
		lastModified++;
	}

	@Override
	public E get(int index) {
		return getElementByIndex(index).getValue();
	}

	private ListElement getElementByIndex(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(String.valueOf(index));
		}
		if (root == null) {
			throw new IndexOutOfBoundsException();
		}
		ListElement res = root;
		for (int i = 0; i < index; i++) {
			res = res.getNext();
			if (res == root) {
				throw new IndexOutOfBoundsException();
			}
		}
		return res;
	}

	@Override
	public E set(int index, E element) {
		MyLinkedList<E>.ListElement themp = getElementByIndex(index);
		E old = themp.getValue();
		themp.setValue(element);
		lastModified++;
		return old;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException(String.valueOf(index));
		}
		MyLinkedList<E>.ListElement themp = new ListElement(element);
		if (root == null) {
			themp.setNext(themp);
			themp.setPrev(themp);
			root = themp;
			lastModified++;
		} else if (index == size()) {
			themp.setPrev(root.getPrev());
			themp.setNext(root);
			root.getPrev().setNext(themp);
			root.setPrev(themp);
			lastModified++;
		} else {
			MyLinkedList<E>.ListElement parent = getElementByIndex(index);
			themp.setPrev(parent.getPrev());
			themp.setNext(parent);
			parent.getPrev().setNext(themp);
			parent.setPrev(themp);
			if (parent == root) {
				root = themp;
			}
			lastModified++;
		}
	}

	@Override
	public E remove(int index) {
		MyLinkedList<E>.ListElement themp = getElementByIndex(index);
		removeNode(themp);
		return themp.getValue();
	}

	private void removeNode(MyLinkedList<E>.ListElement themp) {
		themp.getPrev().setNext(themp.getNext());
		themp.getNext().setPrev(themp.getPrev());
		themp.setNext(null);
		themp.setPrev(null);
		lastModified++;
	}

	@Override
	public int indexOf(Object o) {
		if (root == null) {
			return -1;
		} else if (root.getValue().equals(o)) {
			return 0;
		} else {
			MyLinkedList<E>.ListElement themp = root.getNext();
			int index = 1;
			while (themp != root) {
				if (themp.getValue().equals(o)) {
					return index;
				}
				themp = themp.getNext();
				index++;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (root == null) {
			return -1;
		} else {
			MyLinkedList<E>.ListElement themp = root.getPrev();
			int index = size() - 1;
			while (themp != root) {
				if (themp.getValue().equals(o)) {
					return index;
				}
				themp = themp.getPrev();
				index--;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new AbstractListIterator(0);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new AbstractListIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	private class ListElement {
		private E value;
		private MyLinkedList<E>.ListElement next = null;
		private MyLinkedList<E>.ListElement prev = null;

		public ListElement(E value) {
			super();
			this.value = value;
		}

		/**
		 * @return the value
		 */
		public E getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(E value) {
			this.value = value;
		}

		/**
		 * @return the next
		 */
		public MyLinkedList<E>.ListElement getNext() {
			return next;
		}

		/**
		 * @param next
		 *            the next to set
		 */
		public void setNext(MyLinkedList<E>.ListElement next) {
			this.next = next;
		}

		/**
		 * @return the prev
		 */
		public MyLinkedList<E>.ListElement getPrev() {
			return prev;
		}

		/**
		 * @param prev
		 *            the prev to set
		 */
		public void setPrev(MyLinkedList<E>.ListElement prev) {
			this.prev = prev;
		}

	}

	private class AbstractListIterator implements ListIterator<E> {

		private ListElement current;
		private boolean first = true;
		private int index = -1;
		private boolean mayMod = false;
		private long workingVersion = lastModified;

		private AbstractListIterator(int i) {
			this.current = null;
			while (index < i - 1) {
				next();
			}
		}

		@Override
		public boolean hasNext() {
			return root != null && (current == null || current
					.getNext() != root || first);
		}

		@Override
		public E next() {
			if (workingVersion != lastModified) {
				throw new ConcurrentModificationException();
			}
			if (current == null) {
				if (root == null) {
					throw new NoSuchElementException();
				} else {
					current = root;
					first = root.getNext() != root;
					index = 0;
				}
			} else if (current == root && !first) {
				throw new NoSuchElementException();
			} else {
				current = current.getNext();
				first = false;
				index++;
			}
			mayMod = true;
			return current.getValue();
		}

		@Override
		public boolean hasPrevious() {
			return index > 0;
		}

		@Override
		public E previous() {
			if (workingVersion != lastModified) {
				throw new ConcurrentModificationException();
			}
			if (index > 0) {
				current = current.getPrev();
				first = current == root && root.getNext() != root;
			} else {
				throw new NoSuchElementException();
			}
			mayMod = true;
			index--;
			return current.getValue();
		}

		@Override
		public int nextIndex() {
			return index + 1;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			if (mayMod) {
				if (root == null) {
					throw new IllegalStateException();
				} else if (hasPrevious()) {
					ListElement toDel = current;
					previous();
					removeNode(toDel);
				} else {
					// Is Root Element
					if (root.getNext() == root) {
						// Single Element
						root = null;
						current = null;
					} else {
						root = current.getNext();
						removeNode(current);
						current = null;
						first = root.getNext() != root;
					}
				}
				mayMod = false;
				workingVersion = lastModified;
			} else {
				throw new IllegalStateException();
			}

		}

		@Override
		public void set(E e) {
			if (mayMod) {
				current.setValue(e);
				mayMod = false;
			} else {
				throw new IllegalStateException();
			}
		}

		@Override
		public void add(E e) {
			if (mayMod) {
				ListElement themp = new ListElement(e);
				themp.setPrev(current);
				themp.setNext(current.getNext());
				current.getNext().setPrev(themp);
				current.setNext(themp);
				next();
				mayMod = false;
				workingVersion = lastModified;
			} else {
				throw new IllegalStateException();
			}
		}

	}

}
