package ueb19;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTest {

	private MyLinkedList<Integer> underTest = new MyLinkedList<Integer>();
	private MyLinkedList<Integer> underTestEmpty = new MyLinkedList<
			Integer>();
	private MyLinkedList<Integer> underTestTemp1 = new MyLinkedList<
			Integer>();
	private MyLinkedList<Integer> underTestTemp2 = new MyLinkedList<
			Integer>();

	private Integer a[] = { 1, 2, 3, 4, 5 };
	private Integer aEmpty[] = {};
	private Integer aAddAll[] = { 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, };

	@Before
	public void init() {
		underTest.add(1);
		underTest.add(2);
		underTest.add(3);
		underTest.add(4);
		underTest.add(5);
	}

	@Test
	public void sizeTest1() {
		assertEquals(0, underTestEmpty.size());
	}

	@Test
	public void sizeTest2() {
		assertEquals(5, underTest.size());
	}

	@Test
	public void isEmptyTest1() {
		assertEquals(true, underTestEmpty.isEmpty());
	}

	@Test
	public void isEmptyTest2() {
		assertEquals(false, underTest.isEmpty());
	}

	@Test
	public void containsTest1() {
		assertEquals(false, underTestEmpty.contains(1));
	}

	@Test
	public void containsTest2() {
		assertEquals(true, underTest.contains(1));
	}

	@Test
	public void containsTest3() {
		assertEquals(false, underTest.contains(6));
	}

	@Test
	public void toArrayTest1() {
		assertArrayEquals(aEmpty, underTestEmpty.toArray());
	}

	@Test
	public void toArrayTest2() {
		Object a[] = { 1, 2, 3, 4, 5 };
		assertArrayEquals(a, underTest.toArray());
	}

	@Test
	public void toArrayTest3() {
		assertArrayEquals(aEmpty, underTestEmpty.toArray(
				new Integer[underTestEmpty.size()]));
	}

	@Test
	public void toArrayTest4() {
		assertArrayEquals(a, underTest.toArray(new Integer[underTest
				.size()]));
	}

	@Test
	public void addTest1() {
		Integer aAdd[] = { -1 };
		underTestEmpty.add(-1);
		assertArrayEquals(aAdd, underTestEmpty.toArray());
	}

	@Test
	public void addTest2() {
		Integer aAdd[] = { 1, 2, 3, 4, 5, -1 };
		underTest.add(-1);
		assertArrayEquals(aAdd, underTest.toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeTest1() {
		underTestEmpty.remove(1);
	}

	@Test
	public void removeTest2() {
		Integer aRemove[] = { 2, 3, 4, 5 };
		assertEquals(true, underTest.remove((Object) 1));
		assertArrayEquals(aRemove, underTest.toArray());
	}

	@Test
	public void removeTest3() {
		Integer aRemove[] = { 1, 2, 4, 5 };
		assertEquals(true, underTest.remove((Object) 3));
		assertArrayEquals(aRemove, underTest.toArray());
	}

	@Test
	public void removeTest4() {
		Integer aRemove[] = { 1, 2, 3, 4 };
		assertEquals(true, underTest.remove((Object) 5));
		assertArrayEquals(aRemove, underTest.toArray());
	}

	@Test
	public void removeTest5() {
		assertEquals(false, underTestEmpty.remove((Object) (-1)));
		assertArrayEquals(a, underTest.toArray());
	}

	// @Test
	// public void getElementByValueTest1() {
	// assertEquals(null, underTestEmpty.getElementByValue(1));
	// }
	//
	// @Test
	// public void getElementByValueTest2() {
	// assertArrayEquals(1, underTestEmpty.getElementByValue(1));
	// }
	//
	// @Test
	// public void getElementByValueTest3() {
	// assertArrayEquals(null, underTestEmpty.getElementByValue(-1));
	// }

	@Test
	public void containsAllTest1() {
		assertEquals(true, underTestEmpty.containsAll(underTestEmpty));
	}

	@Test
	public void containsAllTest2() {
		assertEquals(false, underTestEmpty.containsAll(underTest));
	}

	@Test
	public void containsAllTest3() {
		assertEquals(true, underTest.containsAll(underTest));
	}

	@Test
	public void containsAllTest4() {
		assertEquals(true, underTest.containsAll(underTestEmpty));
	}

	@Test
	public void containsAllTest5() {
		MyLinkedList<Integer> com = new MyLinkedList<Integer>();
		com.add(1);
		assertEquals(true, underTest.containsAll(com));
	}

	@Test
	public void addAllTest1() {
		underTestEmpty.addAll(underTest);
		assertArrayEquals(underTest.toArray(), underTestEmpty.toArray());
	}

	@Test
	public void addAllTest2() {
		underTestEmpty.addAll(underTestEmpty);
		assertArrayEquals(underTestEmpty.toArray(), underTestEmpty
				.toArray());
	}

	@Test
	public void addAllTest3() {
		underTest.addAll(Arrays.asList(a));
		assertArrayEquals(aAddAll, underTest.toArray());
	}

	@Test
	public void addAllTest4() {
		underTest.addAll(underTestEmpty);
		assertArrayEquals(a, underTest.toArray());
	}

	@Test
	public void addAllTest5() {
		assertEquals(true, underTestEmpty.addAll(0, underTest));
		assertArrayEquals(underTest.toArray(), underTestEmpty.toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllTest6() {
		underTestEmpty.addAll(1, underTest);
		assertArrayEquals(underTestEmpty.toArray(), underTestEmpty
				.toArray());
	}

	@Test
	public void addAllTest7() {
		assertEquals(true, underTest.addAll(0, Arrays.asList(a)));
		assertArrayEquals(aAddAll, underTest.toArray());
	}

	@Test
	public void addAllTest8() {
		Integer aAddAllT[] = { 1, 2, 1, 2, 3, 4, 5, 3, 4, 5, };
		assertEquals(true, underTest.addAll(2, Arrays.asList(a)));
		assertArrayEquals(aAddAllT, underTest.toArray());
	}

	@Test
	public void addAllTest9() {
		assertEquals(true, underTest.addAll(5, Arrays.asList(a)));
		assertArrayEquals(aAddAll, underTest.toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllTest10() {
		underTest.addAll(6, underTest);
		assertArrayEquals(a, underTest.toArray());
	}

	@Test
	public void removeAllTest1() {
		assertEquals(false, underTestEmpty.removeAll(underTestEmpty));
		assertArrayEquals(underTestEmpty.toArray(), underTestEmpty
				.toArray());
	}

	@Test
	public void removeAllTest2() {
		assertEquals(false, underTestEmpty.removeAll(underTest));
		assertArrayEquals(underTestEmpty.toArray(), underTestEmpty
				.toArray());
	}

	@Test
	public void removeAllTest3() {
		assertEquals(false, underTest.removeAll(underTestEmpty));
		assertArrayEquals(a, underTest.toArray());
	}

	@Test
	public void removeAllTest4() {
		assertEquals(true, underTest.removeAll(Arrays.asList(a)));
		assertArrayEquals(aEmpty, underTest.toArray());
	}

	@Test
	public void removeAllTest5() {
		underTestTemp1.add(1);
		underTestTemp1.add(3);
		underTestTemp1.add(5);
		underTestTemp2.add(2);
		underTestTemp2.add(4);
		assertEquals(true, underTest.removeAll(underTestTemp1));
		assertArrayEquals(underTestTemp2.toArray(), underTest.toArray());
	}

	@Test
	public void retainAllTest1() {
		assertEquals(false, underTestEmpty.retainAll(underTestEmpty));
		assertArrayEquals(underTestEmpty.toArray(), underTestEmpty
				.toArray());
	}

	@Test
	public void retainAllTest2() {
		assertEquals(false, underTestEmpty.retainAll(underTest));
		assertArrayEquals(aEmpty, underTestEmpty.toArray());
	}

	@Test
	public void retainAllTest3() {
		assertEquals(true, underTest.retainAll(underTestEmpty));
		assertTrue(underTest.isEmpty());
	}

	@Test
	public void retainAllTest4() {
		assertEquals(false, underTest.retainAll(underTest));
	}

	@Test
	public void retainAllTest5() {
		underTestTemp1.add(1);
		underTestTemp1.add(3);
		underTestTemp1.add(5);
		assertEquals(true, underTest.retainAll(underTestTemp1));
		assertArrayEquals(underTestTemp1.toArray(), underTest.toArray());
	}

	@Test
	public void clearTest1() {
		underTestEmpty.clear();
		assertArrayEquals(aEmpty, underTestEmpty.toArray());
	}

	@Test
	public void clearTest2() {
		underTest.clear();
		assertArrayEquals(aEmpty, underTest.toArray());
	}

	@Test
	public void addTest3() {
		Integer aAdd[] = { -1 };
		underTestEmpty.add(0, -1);
		assertArrayEquals(aAdd, underTestEmpty.toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addTest4() {
		underTestEmpty.add(1, -1);
		assertArrayEquals(aEmpty, underTestEmpty.toArray());
	}

	@Test
	public void addTest5() {
		Integer aAdd[] = { 1, 2, -1, 3, 4, 5 };
		underTest.add(2, -1);
		assertArrayEquals(aAdd, underTest.toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeTest6() {
		underTestEmpty.remove(1);
		assertArrayEquals(aEmpty, underTestEmpty.toArray());
	}

	@Test
	public void removeTest7() {
		Integer aRemove[] = { 1, 2, 4, 5 };
		Integer i = 3;
		assertEquals(i, underTest.remove(2));
		assertArrayEquals(aRemove, underTest.toArray());
	}

	@Test
	public void indexOfTest1() {
		assertEquals(-1, underTestEmpty.indexOf(3));
	}

	@Test
	public void indexOfTest2() {
		assertEquals(2, underTest.indexOf(3));
	}

	@Test
	public void indexOfTest3() {
		assertEquals(-1, underTest.indexOf(-1));
	}

	@Test
	public void lastIndexOfTest1() {
		assertEquals(-1, underTestEmpty.lastIndexOf(3));
	}

	@Test
	public void lastIndexOfTest2() {
		underTest.add(3);
		assertEquals(5, underTest.lastIndexOf(3));
	}

	@Test
	public void lastIndexOfTest3() {
		assertEquals(-1, underTest.lastIndexOf(-1));
	}

	@Test
	public void listIteratorNextTest() {
		ListIterator<Integer> it = underTest.listIterator();
		assertTrue(it.hasNext());
		assertEquals((Integer) 1, it.next());
		assertEquals(1, it.nextIndex());
		assertTrue(it.hasNext());
		assertEquals((Integer) 2, it.next());
		assertEquals(2, it.nextIndex());
		assertTrue(it.hasNext());
		assertEquals((Integer) 3, it.next());
		assertEquals(3, it.nextIndex());
		assertTrue(it.hasNext());
		assertEquals((Integer) 4, it.next());
		assertEquals(4, it.nextIndex());
		assertTrue(it.hasNext());
		assertEquals((Integer) 5, it.next());
		assertEquals(5, it.nextIndex());
		assertFalse(it.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void listIteratorNext2Test() {
		ListIterator<Integer> it = underTestEmpty.listIterator();
		assertFalse(it.hasNext());
		it.next();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void listIteratorNext3Test() {
		ListIterator<Integer> it = underTest.listIterator();
		underTest.remove(0);
		it.next();
	}

	@Test
	public void listIteratorPreviousTest() {
		ListIterator<Integer> it = underTest.listIterator();
		while (it.hasNext()) {
			it.next();
		}
		assertTrue(it.hasPrevious());
		assertEquals(3, it.previousIndex());
		assertEquals((Integer) 4, it.previous());
		assertTrue(it.hasPrevious());
		assertEquals(2, it.previousIndex());
		assertEquals((Integer) 3, it.previous());
		assertTrue(it.hasPrevious());
		assertEquals(1, it.previousIndex());
		assertEquals((Integer) 2, it.previous());
		assertTrue(it.hasPrevious());
		assertEquals(0, it.previousIndex());
		assertEquals((Integer) 1, it.previous());
		assertFalse(it.hasPrevious());
		assertEquals(-1, it.previousIndex());
	}

	@Test(expected = NoSuchElementException.class)
	public void listIteratorPrevious2Test() {
		ListIterator<Integer> it = underTest.listIterator();
		assertFalse(it.hasPrevious());
		it.previous();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void listIteratorPrevious3Test() {
		ListIterator<Integer> it = underTest.listIterator();
		underTest.remove(0);
		it.previous();
	}

	@Test
	public void listIteratorAddTest() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.add(10);
		it.next();
		assertArrayEquals(new Integer[] { 1, 10, 2, 3, 4, 5 }, underTest
				.toArray());
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorAdd2Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.add(10);
		it.add(12);
		it.next();
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorAdd3Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.remove();
		it.add(12);
		it.next();
	}
	
	@Test(expected = IllegalStateException.class)
	public void listIteratorAdd5Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.set(1);
		it.add(12);
		it.next();
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorAdd4Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.add(10);
	}

	@Test
	public void listIteratorRemoveTest() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.remove();
		it.next();
		assertArrayEquals(new Integer[] { 2, 3, 4, 5 }, underTest
				.toArray());
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorRemove2Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.add(10);
		it.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorRemove3Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.remove();
		it.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorRemove4Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorRemove5Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.set(1);
		it.remove();
	}

	@Test
	public void listIteratorSetTest() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.set(11);
		it.next();
		assertArrayEquals(new Integer[] { 11, 2, 3, 4, 5 }, underTest
				.toArray());
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorSet2Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.add(10);
		it.set(2);
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorSet3Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.remove();
		it.set(2);
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorSet4Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.set(2);
	}

	@Test(expected = IllegalStateException.class)
	public void listIteratorSet5Test() {
		ListIterator<Integer> it = underTest.listIterator();
		it.next();
		it.set(1);
		it.set(2);
	}

}
