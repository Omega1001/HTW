package ueb20;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class MyHeapTest {

	private MyHeap<Integer> underTest = new MyHeap<>(9);
	private MyHeap<Integer> underTestOne = new MyHeap<>(1);
	private MyHeap<Integer> underTestEmpty = new MyHeap<>(9);
	private MyHeap<Integer> underTestHalf = new MyHeap<>(8);

	private Integer arrayOfUnderTest[] = new Integer[9];
	private Integer arrayOfUnderTestOne[] = { 1 };
	private Integer arrayOfUnderTestEmpty[] = new Integer[0];
	private Integer arrayOfUnderTestHalf[] = { 9, 1, 9, 8, null, null,
			null, null };

	@Before
	public void setUp() {

		for (int i = 1; i != 10; i++) {
			underTest.offer(i);
			arrayOfUnderTest[i - 1] = i;
		}

		underTestOne.offer(1);

		underTestHalf.addAll(Arrays.asList(arrayOfUnderTestHalf));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testMyHeap1() {
		new MyHeap<>(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMyHeap2() {
		new MyHeap<>(0);
	}

	@Test
	public void testMyHeap3() {
		MyHeap<Integer> testMyHeap3 = new MyHeap<>(1);

		testMyHeap3.offer(1);

		assertArrayEquals(arrayOfUnderTestOne, testMyHeap3.toArray());
	}

	@Test
	public void testMyHeap4() {
		MyHeap<Integer> testMyHeap4 = new MyHeap<>(9);

		for (int i = 9; i != 0; i--) {
			testMyHeap4.offer(i);
		}

		containsAll(arrayOfUnderTest, testMyHeap4.toArray());
	}

	@Test
	public void testSize1() {
		assertEquals(0, underTestEmpty.size());
	}

	@Test
	public void testSize2() {
		assertEquals(1, underTestOne.size());
	}

	@Test
	public void testSize3() {
		assertEquals(9, underTest.size());
	}

	@Test
	public void testIsEmpty1() {
		assertEquals(true, underTestEmpty.isEmpty());
	}

	@Test
	public void testIsEmpty2() {
		assertEquals(false, underTestOne.isEmpty());
	}

	@Test
	public void testIsEmpty3() {
		assertEquals(false, underTest.isEmpty());
	}

	@Test
	public void testContains1() {
		assertEquals(false, underTestEmpty.contains(null));
	}

	@Test
	public void testContains2() {
		assertEquals(false, underTestEmpty.contains(0));
	}

	@Test
	public void testContains3() {
		assertEquals(false, underTestOne.contains(0));
	}

	@Test
	public void testContains4() {
		assertEquals(true, underTestOne.contains(1));
	}

	@Test
	public void testContains5() {
		assertEquals(true, underTest.contains(3));
	}

	@Test
	public void testContains6() {
		assertEquals(true, underTest.contains(5));
	}

	@Test
	public void testIterator() {
		Iterator<Integer> it = underTest.iterator();
		assertTrue(it.hasNext());
		assertEquals((Integer)1, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer)2, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer)3, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer)4, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer)5, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer)6, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer)7, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer)8, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer)9, it.next());
		assertFalse(it.hasNext());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testIterator2() {
		Iterator<Integer> it = underTestEmpty.iterator();
		assertFalse(it.hasNext());
		it.next();
	}

	@Test
	public void testToArray1() {
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testToArray2() {
		assertArrayEquals(arrayOfUnderTestOne, underTestOne.toArray());
	}

	@Test
	public void testToArray3() {
		assertArrayEquals(arrayOfUnderTest, underTest.toArray());
	}

	@Test
	public void testToArrayTArray1() {

		Integer array[] = new Integer[0];
		Integer result[] = new Integer[0];

		assertArrayEquals(result, underTestEmpty.toArray(array));
	}

	@Test
	public void testToArrayTArray2() {

		Integer array[] = { 0, null, 2 };
		Integer result[] = { null, null, null };

		assertArrayEquals(result, underTestEmpty.toArray(array));
	}

	@Test(expected = ArrayStoreException.class)
	public void testToArrayTArray3() {

		String array[] = new String[3];
		underTest.toArray(array);
	}

	@Test
	public void testToArrayTArray4() {

		Integer array[] = new Integer[0];
		// Under Test One is empty?!
		assertArrayEquals(arrayOfUnderTestOne, underTestOne.toArray(
				array));
	}

	@Test
	public void testToArrayTArray5() {

		Integer array[] = new Integer[1];

		assertArrayEquals(arrayOfUnderTestOne, underTestOne.toArray(
				array));
	}

	@Test
	public void testToArrayTArray6() {

		Integer array[] = { 0, null, 2 };
		Integer result[] = { 1, null, null };

		assertArrayEquals(result, underTestOne.toArray(array));
	}

	@Test
	public void testToArrayTArray7() {

		Integer array[] = new Integer[0];

		assertArrayEquals(arrayOfUnderTest, underTest.toArray(array));
	}

	@Test
	public void testToArrayTArray8() {

		Integer array[] = new Integer[25];
		Integer result[] = new Integer[25];
		for (int i = 1; i != 10; i++) {
			result[i - 1] = i;
		}

		assertArrayEquals(result, underTest.toArray(array));
	}

	@Test
	public void testRemoveObject1() {
		assertEquals(false, underTestEmpty.remove(null));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testRemoveObject2() {
		assertEquals(false, underTestEmpty.remove(0));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testRemoveObject3() {
		assertEquals(false, underTestOne.remove(null));
		assertArrayEquals(arrayOfUnderTestOne, underTestOne.toArray());
	}

	@Test
	public void testRemoveObject4() {
		assertEquals(false, underTestOne.remove(0));
		assertArrayEquals(arrayOfUnderTestOne, underTestOne.toArray());
	}

	@Test
	public void testRemoveObject5() {
		assertEquals(true, underTestOne.remove(1));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestOne.toArray());
	}

	@Test
	public void testRemoveObject6() {
		assertEquals(false, underTest.remove(null));
		assertArrayEquals(arrayOfUnderTest, underTest.toArray());
	}

	@Test
	public void testRemoveObject7() {
		assertEquals(false, underTest.remove(0));
		assertArrayEquals(arrayOfUnderTest, underTest.toArray());
	}

	@Test
	public void testRemoveObject8() {
		assertEquals(true, underTest.remove(1));
		Integer[] result = { 2, 3, 4, 5, 6, 7, 8, 9 };
		containsAll(result, underTest.toArray());
	}

	@Test
	public void testRemoveObject9() {
		assertEquals(true, underTest.remove(5));
		Integer[] result = { 1, 2, 3, 4, 6, 7, 8, 9 };
		containsAll(result, underTest.toArray());
	}

	@Test
	public void testRemoveObject10() {
		assertEquals(true, underTest.remove(9));
		Integer[] result = { 1, 2, 3, 4, 5, 6, 7, 8 };
		assertArrayEquals(result, underTest.toArray());
	}

	@Test
	public void testRemoveObject11() {

		MyHeap<Integer> myHeap = new MyHeap<>(10);
		myHeap.offer(1);
		for (int i = 1; i != 10; i++) {
			myHeap.offer(i);
		}

		assertEquals(true, myHeap.remove(5));
		containsAll(new Integer [] {1,1,2,3,4,6,7,8,9}, myHeap.toArray());
	}

	@Test
	public void testContainsAll1() {
		assertEquals(true, underTestEmpty.containsAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
	}

	@Test
	public void testContainsAll2() {
		assertEquals(false, underTestEmpty.containsAll(Arrays.asList(
				arrayOfUnderTestOne)));
	}

	@Test
	public void testContainsAll3() {
		assertEquals(false, underTestEmpty.containsAll(Arrays.asList(
				arrayOfUnderTest)));
	}

	@Test
	public void testContainsAll4() {
		assertEquals(true, underTestOne.containsAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
	}

	@Test
	public void testContainsAll5() {
		assertEquals(true, underTestOne.containsAll(Arrays.asList(
				arrayOfUnderTestOne)));
	}

	@Test
	public void testContainsAll6() {
		assertEquals(false, underTestOne.containsAll(Arrays.asList(
				arrayOfUnderTest)));
	}

	@Test
	public void testContainsAll7() {
		assertEquals(true, underTest.containsAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
	}

	@Test
	public void testContainsAll8() {
		assertEquals(true, underTest.containsAll(Arrays.asList(
				arrayOfUnderTestOne)));
	}

	@Test
	public void testContainsAll9() {
		assertEquals(true, underTest.containsAll(Arrays.asList(
				arrayOfUnderTest)));
	}

	@Test
	public void testContainsAll10() {
		Integer[] array = { 0 };
		assertEquals(false, underTest.containsAll(Arrays.asList(array)));
	}

	@Test
	public void testAddAll1() {
		assertEquals(false, underTestEmpty.addAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testAddAll2() {
		assertEquals(true, underTestEmpty.addAll(Arrays.asList(
				arrayOfUnderTestOne)));
		assertArrayEquals(arrayOfUnderTestOne, underTestEmpty.toArray());
	}

	@Test
	public void testAddAll3() {
		assertEquals(true, underTestEmpty.addAll(Arrays.asList(
				arrayOfUnderTest)));
		assertArrayEquals(arrayOfUnderTest, underTestEmpty.toArray());
	}

	@Test
	public void testAddAll4() {
		assertEquals(false, underTestOne.addAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
		assertArrayEquals(arrayOfUnderTestOne, underTestOne.toArray());
	}

	@Test(expected = IllegalStateException.class)
	public void testAddAll5() {
		underTestOne.addAll(Arrays.asList(arrayOfUnderTestOne));
	}

	@Test(expected = IllegalStateException.class)
	public void testAddAll6() {
		underTestOne.addAll(Arrays.asList(arrayOfUnderTest));
	}

	@Test
	public void testAddAll7() {
		assertEquals(false, underTest.addAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
		assertArrayEquals(arrayOfUnderTest, underTest.toArray());
	}

	@Test(expected = IllegalStateException.class)
	public void testAddAll8() {
		underTest.addAll(Arrays.asList(arrayOfUnderTestOne));
	}

	@Test(expected = IllegalStateException.class)
	public void testAddAll9() {
		underTest.addAll(Arrays.asList(arrayOfUnderTest));
	}

	@Test
	public void testRemoveAll1() {
		assertEquals(false, underTestEmpty.removeAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testRemoveAll2() {
		assertEquals(false, underTestEmpty.removeAll(Arrays.asList(
				arrayOfUnderTestOne)));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testRemoveAll3() {
		assertEquals(false, underTestEmpty.removeAll(Arrays.asList(
				arrayOfUnderTest)));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testRemoveAll4() {
		assertEquals(false, underTestOne.removeAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
		assertArrayEquals(arrayOfUnderTestOne, underTestOne.toArray());
	}

	@Test
	public void testRemoveAll5() {
		assertEquals(true, underTestOne.removeAll(Arrays.asList(
				arrayOfUnderTestOne)));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestOne.toArray());
	}

	@Test
	public void testRemoveAll6() {
		assertEquals(true, underTestOne.removeAll(Arrays.asList(
				arrayOfUnderTest)));
		assertArrayEquals(new Integer[] {}, underTestOne.toArray());
	}

	@Test
	public void testRemoveAll7() {
		assertEquals(false, underTest.removeAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
		assertArrayEquals(arrayOfUnderTest, underTest.toArray());
	}

	@Test
	public void testRemoveAll8() {

		Integer[] array = { 1, 2, 3 };
		Integer[] result = { 4, 5, 6, 7, 8, 9 };

		assertEquals(true, underTest.removeAll(Arrays.asList(array)));
		containsAll(result, underTest.toArray());
	}

	@Test
	public void testRemoveAll9() {

		Integer[] array = { 4, 5, 6 };
		Integer[] result = { 1, 2, 3, 7, 8, 9 };

		assertEquals(true, underTest.removeAll(Arrays.asList(array)));
		containsAll(result, underTest.toArray());
	}

	@Test
	public void testRemoveAll10() {

		Integer[] array = { 7, 8, 9 };
		Integer[] result = { 1, 2, 3, 4, 5, 6 };

		assertEquals(true, underTest.removeAll(Arrays.asList(array)));
		assertArrayEquals(result, underTest.toArray());
	}

	@Test
	public void testRemoveAll11() {
		assertEquals(true, underTest.removeAll(Arrays.asList(
				arrayOfUnderTest)));
		assertArrayEquals(arrayOfUnderTestEmpty, underTest.toArray());
	}

	@Test
	public void testRemoveAll12() {

		Integer[] array = { 1, 5, 9, 159 };

		assertEquals(true, underTest.removeAll(Arrays.asList(array)));
		containsAll(new Integer []{2,3,4,6,7,8}, underTest.toArray());
	}

	@Test
	public void testRetainAll1() {
		assertEquals(false, underTestEmpty.retainAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testRetainAll2() {
		assertEquals(false, underTestEmpty.retainAll(Arrays.asList(
				arrayOfUnderTestOne)));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testRetainAll3() {
		assertEquals(false, underTestEmpty.retainAll(Arrays.asList(
				arrayOfUnderTest)));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testRetainAll4() {
		assertEquals(true, underTestOne.retainAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
		assertArrayEquals(new Integer[] {}, underTestOne.toArray());
	}

	@Test
	public void testRetainAll5() {
		assertEquals(false, underTestOne.retainAll(Arrays.asList(
				arrayOfUnderTestOne)));
		assertArrayEquals(arrayOfUnderTestOne, underTestOne.toArray());
	}

	@Test
	public void testRetainAll6() {
		assertEquals(false, underTestOne.retainAll(Arrays.asList(
				arrayOfUnderTest)));
		assertArrayEquals(arrayOfUnderTestOne, underTestOne.toArray());
	}

	@Test
	public void testRetainAll7() {

		Integer[] array = { 0 };

		assertEquals(true, underTestOne.retainAll(Arrays.asList(array)));
		assertArrayEquals(arrayOfUnderTestEmpty, underTestOne.toArray());
	}

	@Test
	public void testRetainAll8() {
		assertEquals(true, underTest.retainAll(Arrays.asList(
				arrayOfUnderTestEmpty)));
		assertArrayEquals(new Integer [] {}, underTest.toArray());
	}

	@Test
	public void testRetainAll9() {
		assertEquals(true, underTest.retainAll(Arrays.asList(
				arrayOfUnderTestOne)));
		assertArrayEquals(arrayOfUnderTestOne, underTest.toArray());
	}

	@Test
	public void testRetainAll10() {
		assertEquals(false, underTest.retainAll(Arrays.asList(
				arrayOfUnderTest)));
		assertArrayEquals(arrayOfUnderTest, underTest.toArray());
	}

	@Test
	public void testRetainAll11() {

		Integer[] array = { 1, 2, 3 };

		assertEquals(true, underTest.retainAll(Arrays.asList(array)));
		assertArrayEquals(array, underTest.toArray());
	}

	@Test
	public void testRetainAll12() {

		Integer[] array = { 4, 5, 6 };

		assertEquals(true, underTest.retainAll(Arrays.asList(array)));
		containsAll(array, underTest.toArray());
	}

	@Test
	public void testRetainAll13() {

		Integer[] array = { 7, 8, 9 };

		assertEquals(true, underTest.retainAll(Arrays.asList(array)));
		containsAll(array, underTest.toArray());
	}

	@Test
	public void testRetainAll14() {

		Integer[] array = { 7, 8, 9, 10 };
		Integer[] result = { 7, 8, 9 };

		assertEquals(true, underTest.retainAll(Arrays.asList(array)));
		containsAll(result, underTest.toArray());
	}

	@Test
	public void testClear1() {
		underTestEmpty.clear();
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testClear2() {
		underTestOne.clear();
		assertArrayEquals(arrayOfUnderTestEmpty, underTestOne.toArray());
	}

	@Test
	public void testClear3() {
		underTest.clear();
		assertArrayEquals(arrayOfUnderTestEmpty, underTest.toArray());
	}

	@Test
	public void testAdd1() {
		assertEquals(true, underTestEmpty.add(1));
		assertArrayEquals(arrayOfUnderTestOne, underTestEmpty.toArray());
	}

	@Test(expected = IllegalStateException.class)
	public void testAdd2() {
		underTestOne.add(1);
	}

	@Test(expected = IllegalStateException.class)
	public void testAdd3() {
		underTest.add(1);
	}

	@Test
	public void testOffer1() {
		assertEquals(true, underTestEmpty.offer(1));
		assertArrayEquals(arrayOfUnderTestOne, underTestEmpty.toArray());
	}

	@Test
	public void testOffer2() {
		assertEquals(false, underTestOne.offer(1));
		assertArrayEquals(arrayOfUnderTestOne, underTestOne.toArray());
	}

	@Test
	public void testOffer3() {
		assertEquals(false, underTest.offer(1));
		assertArrayEquals(arrayOfUnderTest, underTest.toArray());
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemove1() {
		underTestEmpty.remove();
	}

	@Test
	public void testRemove2() {
		underTestOne.remove();
		assertArrayEquals(arrayOfUnderTestEmpty, underTestOne.toArray());
	}

	@Test
	public void testRemove3() {
		underTest.remove();

		Integer[] result = { 2, 3, 4, 5, 6, 7, 8, 9 };

		containsAll(result, underTest.toArray());
	}

	@Test
	public void testPoll1() {
		underTestEmpty.poll();
		assertArrayEquals(arrayOfUnderTestEmpty, underTestEmpty.toArray());
	}

	@Test
	public void testPoll2() {
		underTestOne.poll();
		assertArrayEquals(arrayOfUnderTestEmpty, underTestOne.toArray());
	}

	@Test
	public void testPoll3() {
		underTest.poll();

		Integer[] result = { 2, 3, 4, 5, 6, 7, 8, 9 };

		containsAll(result, underTest.toArray());
	}

	@Test
	public void testElement1() {
		assertNull(underTestEmpty.peek());
	}

	@Test
	public void testElement2() {
		assertEquals((Integer) 1, underTestOne.peek());
	}

	@Test
	public void testElement3() {
		assertEquals((Integer) 1, underTest.peek());
	}

	@Test
	public void testPeek1() {
		assertEquals(null, underTestEmpty.peek());
	}

	@Test
	public void testPeek2() {
		assertEquals((Integer) 1, underTestOne.peek());
	}

	@Test
	public void testPeek3() {
		assertEquals((Integer) 1, underTest.peek());
	}

	private void containsAll(Object[] expected, Object[] actual) {
		if (expected.length != actual.length) {
			fail("Array Differs in length: expected '" + expected.length
					+ "' actual '" + actual.length + "'");
		}
		for (Object ao : expected) {
			if (!contains(actual, ao)) {
				fail("Result Array is missing value " + ao);
			}
		}
	}

	private boolean contains(Object[] a, Object b) {
		for (Object ao : a) {
			if (ao.equals(b)) {
				return true;
			}
		}
		return false;
	}

}
