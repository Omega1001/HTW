package ueb21;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConsumerTest {
	
	private MyConsumer underTest = new MyConsumer();

	@Test(expected = IllegalArgumentException.class)
	public void testConsume1() {
		underTest.accept(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConsume2() {
		underTest.accept(-1);
	}
	
	@Test
	public void testConsume3() {
		underTest.accept(0);
		assertTrue(underTest.exists(0));
	}
	
	@Test
	public void testConsume4() {
		
		for (int i = 0; i < 10; i++) {
			underTest.accept(i);
			underTest.accept(i);
		}
		for(int i=1;i<10;i++) {
			assertTrue(underTest.exists(i));
			assertEquals((Integer)2, underTest.numberOfOccurrences(i));
		}
	}
	
	@Test
	public void testConsume5() {
		
		underTest.accept(Integer.MAX_VALUE);
		assertTrue(underTest.exists(46));
	}
	
	@Test
	public void numberOfDifferentResults1() {
		
		assertEquals((Integer)0, underTest.numberOfDifferentResults());
	}
	
	@Test
	public void numberOfDifferentResults2() {
		
		underTest.accept(0);
		assertEquals((Integer)1, underTest.numberOfDifferentResults());
	}
	
	@Test
	public void numberOfDifferentResults3() {
		
		for (int i = 1; i < 1000; i++) {
			
			underTest.accept(i);
		}
		
		assertEquals((Integer)27, underTest.numberOfDifferentResults());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void numberOfOccurrences1() {

		underTest.numberOfOccurrences(null);
	}
	
	@Test
	public void numberOfOccurrences2() {

		assertEquals((Integer)0, underTest.numberOfOccurrences(1));
	}
	
	@Test
	public void numberOfOccurrences3() {
		underTest.accept(1000);
		assertEquals((Integer)1, underTest.numberOfOccurrences(1));
	}
	
	@Test
	public void numberOfOccurrences4() {
		for (int i = 0; i < 10; i++) {
			
			underTest.accept(1000);
		}
		
		assertEquals((Integer)10, underTest.numberOfOccurrences(1));
	}
	
	@Test
	public void getCrossTotalsAscending1() {
		assertArrayEquals(new Integer[] {}, underTest.getCrossTotalsAscending().toArray());
	}
	
	public void getCrossTotalsAscending2() {
		
		underTest.accept(4);
		underTest.accept(5);
		underTest.accept(0);
		underTest.accept(8);
		underTest.accept(2);
		assertArrayEquals(new Integer[] {0, 2, 4, 5, 8}, underTest.getCrossTotalsAscending().toArray());
	}
	
	@Test
	public void getCrossTotalsDescending1() {
		assertArrayEquals(new Integer[] {}, underTest.getCrossTotalsDescending().toArray());
	}
	
	@Test
	public void getCrossTotalsDescending2() {
		
		underTest.accept(4);
		underTest.accept(5);
		underTest.accept(0);
		underTest.accept(8);
		underTest.accept(2);
		assertArrayEquals(new Integer[] {8, 5, 4, 2, 0}, underTest.getCrossTotalsDescending().toArray());
	}
	
	@Test
	public void getTimestampsForResult1() {
		assertEquals(null, underTest.getTimestampsForResult(0));
	}
	
	@Test
	public void getTimestampsForResult2() {
		
		underTest.accept(0);
		Long t = System.currentTimeMillis();
		//TODO hat immer funktioniert bis jetzt allerdings m�sste eine passenede Abweichung her.
		assertArrayEquals(new Long[] {t}, underTest.getTimestampsForResult(0).toArray());
	}
	
	@Test
	public void getTimestampsForResult3() {
		
		Long[] a = new Long[100];
		
		for (int i = 0; i < 100; i++) {
			
			underTest.accept(27);
			a[i] = System.currentTimeMillis();
		}
		
		//TODO hat immer funktioniert bis jetzt allerdings m�sste eine passenede Abweichung her.
		assertArrayEquals(a, underTest.getTimestampsForResult(9).toArray());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void exists1() {
		underTest.accept(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void exists2() {
		underTest.accept(-1);
	}
	
	@Test
	public void exists3() {
		
		for (int i = 1; i < 1000; i++) {
			
			Integer j = 0;
			Integer temp = i;
			
			while(temp != 0) {
				
				j += temp % 10;
				temp /= 10;
			}
			underTest.accept(i);
			assertTrue("" + i, underTest.exists(j));
		}
	}

}
