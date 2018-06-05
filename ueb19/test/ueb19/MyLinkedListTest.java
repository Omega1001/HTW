package ueb19;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTest {
	
	private MyLinkedList<Integer> underTest = new MyLinkedList<Integer>();
	private MyLinkedList<Integer> underTestEmpty = new MyLinkedList<Integer>();
	private MyLinkedList<Integer> underTestTemp1 = new MyLinkedList<Integer>();
	private MyLinkedList<Integer> underTestTemp2 = new MyLinkedList<Integer>();
	
	private Integer a[] = {1,2,3,4,5};
	private Integer aEmpty[] = {};
	private Integer aAddAll[] = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5,};

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
		assertEquals(true, underTestEmpty.size());
	}
	
	@Test
	public void isEmptyTest2() {
		assertEquals(false, underTest.size());
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
		Object a[] = {1,2,3,4,5};
		assertArrayEquals(a, underTest.toArray());
	}
	
	@Test
	public void toArrayTest3() {
		assertArrayEquals(aEmpty, underTestEmpty.toArray(new Integer[underTestEmpty.size()]));
	}
	
	@Test
	public void toArrayTest4() {
		assertArrayEquals(a, underTest.toArray(new Integer[underTest.size()]));
	}
	
	@Test
	public void addTest1() {
		Integer aAdd[] = {-1};
		underTestEmpty.add(-1);
		assertArrayEquals(aAdd, underTestEmpty.toArray());
	}
	
	@Test
	public void addTest2() {
		Integer aAdd[] = {1, 2, 3, 4, 5, -1};
		underTest.add(-1);
		assertArrayEquals(aAdd, underTest.toArray());
	}
	
	@Test
	public void removeTest1() {
		assertEquals(false, underTestEmpty.remove(1));
		assertArrayEquals(aEmpty, underTestEmpty.toArray());
	}
	
	@Test
	public void removeTest2() {
		Integer aRemove[] = {2, 3, 4, 5};
		assertEquals(true, underTest.remove(1));
		assertArrayEquals(aRemove, underTest.toArray());
	}
	
	@Test
	public void removeTest3() {
		Integer aRemove[] = {1, 2, 4, 5};
		assertEquals(true, underTest.remove(3));
		assertArrayEquals(aRemove, underTest.toArray());
	}
	
	@Test
	public void removeTest4() {
		Integer aRemove[] = {1, 2, 3, 4};
		assertEquals(true, underTest.remove(5));
		assertArrayEquals(aRemove, underTest.toArray());
	}
	
	@Test
	public void removeTest5() {
		assertEquals(false, underTestEmpty.remove(-1));
		assertArrayEquals(a, underTest.toArray());
	}
	
//	@Test
//	public void getElementByValueTest1() {
//		assertEquals(null, underTestEmpty.getElementByValue(1));
//	}
//	
//	@Test
//	public void getElementByValueTest2() {
//		assertArrayEquals(1, underTestEmpty.getElementByValue(1));
//	}
//	
//	@Test
//	public void getElementByValueTest3() {
//		assertArrayEquals(null, underTestEmpty.getElementByValue(-1));
//	}
	
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
		assertEquals(false, underTest.containsAll(underTestEmpty));
	}
	
	@Test
	public void containsAllTest5() {
		MyLinkedList<Integer> com = new MyLinkedList<Integer>();
		com.add(1);
		assertEquals(false, underTest.containsAll(com));
	}
	
	@Test
	public void addAllTest1() {
		underTestEmpty.addAll(underTest);
		assertArrayEquals(underTest.toArray(), underTestEmpty.toArray());
	}
	
	@Test
	public void addAllTest2() {
		underTestEmpty.addAll(underTestEmpty);
		assertArrayEquals(underTestEmpty.toArray(), underTestEmpty.toArray());
	}
	
	@Test
	public void addAllTest3() {
		underTest.addAll(underTest);
		assertArrayEquals(aAddAll, underTest.toArray());
	}
	
	@Test
	public void addAllTest4() {
		underTest.addAll(underTest);
		assertArrayEquals(a, underTest.toArray());
	}
	
	@Test
	public void addAllTest5() {
		assertEquals(true, underTestEmpty.addAll(0, underTest));
		assertArrayEquals(underTest.toArray(), underTestEmpty.toArray());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void addAllTest6() {
		underTestEmpty.addAll(1, underTest);
		assertArrayEquals(underTestEmpty.toArray(), underTestEmpty.toArray());
	}
	
	@Test
	public void addAllTest7() {
		assertEquals(true, underTest.addAll(0, underTest));
		assertArrayEquals(aAddAll, underTest.toArray());
	}
	
	@Test
	public void addAllTest8() {
		Integer aAddAllT[] = {1, 2, 1, 2, 3, 4, 5, 3, 4, 5,};
		assertEquals(true, underTestEmpty.addAll(2, underTest));
		assertArrayEquals(aAddAllT, underTest.toArray());
	}
	
	@Test
	public void addAllTest9() {
		assertEquals(true, underTestEmpty.addAll(4, underTest));
		assertArrayEquals(aAddAll, underTest.toArray());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void addAllTest10() {
		underTest.addAll(6, underTest);
		assertArrayEquals(a, underTest.toArray());
	}
	
	@Test
	public void removeAllTest1() {
		assertEquals(false, underTestEmpty.removeAll(underTestEmpty));
		assertArrayEquals(underTestEmpty.toArray(), underTestEmpty.toArray());
	}
	
	@Test
	public void removeAllTest2() {
		assertEquals(false, underTestEmpty.removeAll(underTest));
		assertArrayEquals(underTestEmpty.toArray(), underTestEmpty.toArray());
	}
	
	@Test
	public void removeAllTest3() {
		assertEquals(true, underTest.removeAll(underTestEmpty));
		assertArrayEquals(a, underTest.toArray());
	}
	
	@Test
	public void removeAllTest4() {
		assertEquals(true, underTest.removeAll(underTest));
		assertArrayEquals(aEmpty, underTest.toArray());
	}
	
	@Test
	public void removeAllTest5() {
		underTestTemp1.add(1);
		underTestTemp1.add(3);
		underTestTemp1.add(5);
		underTestTemp2.add(2);
		underTestTemp2.add(4);
		assertEquals(true, underTestEmpty.removeAll(underTestTemp1));
		assertArrayEquals(underTestTemp2.toArray(), underTest.toArray());
	}
	
	@Test
	public void retainAllTest1() {
		assertEquals(true, underTestEmpty.retainAll(underTestEmpty));
		assertArrayEquals(underTestEmpty.toArray(), underTestEmpty.toArray());
	}
	
	@Test
	public void retainAllTest2() {
		assertEquals(false, underTestEmpty.retainAll(underTest));
		assertArrayEquals(aEmpty, underTest.toArray());
	}
	
	@Test
	public void retainAllTest3() {
		assertEquals(true, underTest.retainAll(underTestEmpty));
		assertArrayEquals(a, underTest.toArray());
	}
	
	@Test
	public void retainAllTest4() {
		assertEquals(true, underTest.retainAll(underTest));
		assertArrayEquals(aEmpty, underTest.toArray());
	}
	
	@Test
	public void retainAllTest5() {
		underTestTemp1.add(1);
		underTestTemp1.add(3);
		underTestTemp1.add(5);
		assertEquals(true, underTestEmpty.retainAll(underTestTemp1));
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
		Integer aAdd[] = {-1};
		underTestEmpty.add(0, -1);
		assertArrayEquals(aAdd, underTestEmpty.toArray());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void addTest4() {
		underTestEmpty.add(1, -1);
		assertArrayEquals(aEmpty, underTestEmpty.toArray());
	}
	
	@Test
	public void addTest5() {
		Integer aAdd[] = {1, 2, 3, -1, 4, 5};
		underTest.add(2, -1);
		assertArrayEquals(aAdd, underTest.toArray());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void removeTest6() {
		underTestEmpty.remove(1);
		assertArrayEquals(aEmpty, underTestEmpty.toArray());
	}
	
	@Test
	public void removeTest7() {
		Integer aRemove[] = {1, 2, 4, 5};
		Integer i = 3;
		assertEquals(i, underTest.remove(2));
		assertArrayEquals(aRemove, underTestEmpty.toArray());
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
	
//	@Test
//	public void lastIndexOfTest2() {
//		underTest.add(3);
//		assertEquals(5, underTest.lastIndexOf(3));
//	}
//	
//	@Test
//	public void lastIndexOfTest3() {
//		assertEquals(-1, underTest.lastIndexOf(-1));
//	}

}
