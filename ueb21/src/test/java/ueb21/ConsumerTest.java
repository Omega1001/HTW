package ueb21;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConsumerTest {
	
	private Consumer underTest = new Consumer();

	@Test(expected = IllegalArgumentException.class)
	public void testConsume1() {
		underTest.consume(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConsume2() {
		underTest.consume(-1);
	}
	
	@Test
	public void testConsume3() {
		assertEquals((Integer)0, underTest.consume(0));
		assertTrue(underTest.exists(0));
	}
	
	@Test
	public void testConsume4() {
		
		for (int i = 1; i < 1000; i++) {
			
			Integer j = 0;
			Integer temp = i;
			
			while(temp != 0) {
				
				j += temp % 10;
				temp /= 10;
			}
			
			assertEquals("" + i , j, underTest.consume(i));
			assertTrue("" + i, underTest.exists(j));
		}
	}
	
	@Test
	public void testConsume5() {
		
		assertEquals((Integer) 46, underTest.consume(Integer.MAX_VALUE));
		assertTrue(underTest.exists(46));
	}
	
	@Test
	public void numberOfDifferentResults() {
		//TODO
	}
	
	@Test
	public void numberOfOccurrences() {
		//TODO
	}
	
	@Test
	public void getCrossTotalsAscending() {
		//TODO
	}
	
	@Test
	public void getCrossTotalsDescending() {
		//TODO
	}
	
	@Test
	public void getTimestampsForResult() {
		//TODO
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void exists1() {
		underTest.consume(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void exists2() {
		underTest.consume(-1);
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
			underTest.consume(i);
			assertTrue("" + i, underTest.exists(j));
		}
	}

}
