package ueb19;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements List<E> {

	private ListElement root;

	@Override
	public int size() {
		if (root == null) {
			return 0;
		} else {
			int size = 1;
			ListElement themp = root.getNext();
			while (themp != root) {
				size++;
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
			if (i < size()) {
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
			return true;
		} else {
			ListElement themp = new ListElement(e);
			themp.setNext(root);
			themp.setPrev(root.getPrev());
			root.getPrev().setNext(themp);
			root.setPrev(themp);
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
				elm.getPrev().setNext(elm.getNext());
				elm.getNext().setNext(elm.getPrev());
				elm.setNext(null);
				elm.setPrev(null);
			}
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
		return addAll(size() - 1, c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		int tIndex = index;
		for (E o : c) {
			add(tIndex++, o);
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
		for (Object o : c) {
			if (!c.contains(o)) {
				result = remove(o) || result;
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
	}

	@Override
	public E get(int index) {
		return getElementByIndex(index).getValue();
	}

	private ListElement getElementByIndex(int index) {
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
		return old;
	}

	@Override
	public void add(int index, E element) {
		MyLinkedList<E>.ListElement themp = new ListElement(element);
		if (root == null) {
			root = themp;
		} else {

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
		themp.getNext().setPrev(themp.getNext());
		themp.setNext(null);
		themp.setPrev(null);
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
				if (themp.equals(o)) {
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
			int index = size();
			while (themp != root) {
				if (themp.equals(o)) {
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
		return new AbstractListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
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
		private int index = 0;
		private boolean mayMod = true;

		private AbstractListIterator() {
			this.current = null;
		}

		@Override
		public boolean hasNext() {
			return root != null && current.getNext() != root || first;
		}

		@Override
		public E next() {
			if (current == null) {
				if (root == null) {
					throw new NoSuchElementException();
				} else {
					current = root;
					first = true;
				}
			} else if (current == root && !first) {
				throw new NoSuchElementException();
			} else {
				current = current.getNext();
				first = false;
			}
			mayMod = true;
			index++;
			return current.getValue();
		}

		@Override
		public boolean hasPrevious() {
			return index > 0;
		}

		@Override
		public E previous() {
			if (index > 0) {
				current = current.getPrev();
			} else {
				throw new NoSuchElementException();
			}
			mayMod = true;
			index--;
			return current.getValue();
		}

		@Override
		public int nextIndex() {
			return index + (hasNext() ? 1 : 0);
		}

		@Override
		public int previousIndex() {
			return index - (hasPrevious() ? 1 : 0);
		}

		@Override
		public void remove() {
			if (mayMod) {
				if(root == null){
					throw new IllegalStateException();
				}else if (hasPrevious()) {
					ListElement toDel = current;
					previous();
					removeNode(toDel);
				}else {
					//Single Element
					if(root.getNext() == root) {
						root = null;
						current = null;
					}else {
						removeNode(current);
						current = null;
					}
				}
				mayMod = false;
			} else {
				throw new IllegalStateException();
			}

		}

		@Override
		public void set(E e) {
			current.setValue(e);
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
			} else {
				throw new IllegalStateException();
			}
		}

	}

}
