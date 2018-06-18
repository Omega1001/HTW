package ueb21;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomDigitSumsTest {

	RandomDigitSums underTest;
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testRandomDigitSumsConstructor1() throws InterruptedException {

		underTest = new RandomDigitSums(-1);
	}
	
	@Test
	public void testRandomDigitSumsConstructor2() throws InterruptedException {

		int conSiz = 0;
		int estConSiz = 0;
		int difRes = 0;
		
		underTest = new RandomDigitSums(0);
		
		estConSiz = 10000 - (underTest.getBSC() + underTest.getProCou());
		difRes = underTest.getConsumer().numberOfDifferentResults();
		
		for (int i = 0; difRes != 0; i++) {
				
			int siz = underTest.getConsumer().getTimestampsForResult(i).size();
			
			if (siz != 0) {
				
			conSiz += siz;
			difRes--;
			}
		}
		
		assertEquals(estConSiz, conSiz);
	}
	
	@Test
	public void testRandomDigitSumsConstructor3() throws InterruptedException {

		int conSiz = 0;
		int estConSiz = 0;
		int difRes = 0;
		
		underTest = new RandomDigitSums(1);
		
		estConSiz = 10000 - (underTest.getBSC() + underTest.getProCou());
		difRes = underTest.getConsumer().numberOfDifferentResults();
		
		for (int i = 0; difRes != 0; i++) {
				
			int siz = underTest.getConsumer().getTimestampsForResult(i).size();
			
			if (siz != 0) {
				
			conSiz += siz;
			difRes--;
			}
		}
		
		assertEquals(estConSiz, conSiz);
	}

}
