package ueb16;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class NumberCruncherTest {
	
	private NumberCruncher underTest1;
	private NumberCruncher underTest2;
	
	private float [] testArray = {1, 2, 3, 4, 5} ;
	
	private String [] sum = {"sum"};
	private float [] solutionSum = {1, 3, 6, 10, 15} ;
	
	private String [] divide = {"divide"};
	private float [] solutionDivide = {1, 3, 6/6, 4/3, 5/1} ;
	
	private String [] subtract = {"subtract"};
	private float [] solutionSubstract = {1, -1, -4, -8, -13} ;

	private String [] average = {"average"};
	private float [] solutionAverage = {1, 2, 3, 4, 3} ;
	
	private String [] swirl = {"swirl"};
	@Test
	public void NumberCruncherTest1() {
		underTest1 = new NumberCruncher(5);
		underTest2 = new NumberCruncher(5);
		assertFalse(Arrays.equals(underTest1.getNumbers(), underTest2.getNumbers()));
	}
	@Test
	public void NumberCruncherSumTest1() {
		underTest1 = new NumberCruncher(5);
		float [] memory = underTest1.getNumbers().clone();
		float buffer;
		underTest1.crunch(sum);
		assertTrue((underTest1.getNumbers()[0] == (buffer=memory[0]))&&
				underTest1.getNumbers()[1] == (buffer=(buffer+memory[1]))&&
				underTest1.getNumbers()[2] == (buffer=(buffer+memory[2]))&&
				underTest1.getNumbers()[3] == (buffer=(buffer+memory[3]))&&
				underTest1.getNumbers()[4] == (buffer=(buffer+memory[4])));
	}
	@Test
	public void NumberCruncherDivideTest1() {
		underTest1 = new NumberCruncher(5);
		float [] memory = underTest1.getNumbers().clone();
		Arrays.sort(memory);
		underTest1.crunch(divide);
		float [] result = underTest1.getNumbers().clone();
		Arrays.sort(result);
		
		assertTrue((result[0] == memory[0])&&
					result[1] == memory[1]&&
					result[2] == (memory[2]/memory[2])&&
					result[3] == (memory[3]/memory[1])&&
					result[4] == (memory[4]/memory[0]));
	}
	@Test
	public void NumberCruncherSubtractTest1() {
		underTest1 = new NumberCruncher(5);
		float [] memory = underTest1.getNumbers().clone();
		float buffer;
		underTest1.crunch(subtract);
		assertTrue((underTest1.getNumbers()[0] == (buffer=memory[0]))&&
				underTest1.getNumbers()[1] == (buffer=(buffer-memory[1]))&&
				underTest1.getNumbers()[2] == (buffer=(buffer-memory[2]))&&
				underTest1.getNumbers()[3] == (buffer=(buffer-memory[3]))&&
				underTest1.getNumbers()[4] == (buffer=(buffer-memory[4])));
	}
	@Test
	public void NumberCruncherAverageTest1() {
		
	}
	@Test
	public void NumberCruncherSwirlTest1() {
		underTest1 = new NumberCruncher(5);
		float [] memory = underTest1.getNumbers().clone();
		underTest1.crunch(swirl);
		assertTrue(compare(underTest1.getNumbers(),memory));
	}
	
	private boolean compare(float[] a, float[] b) {
		for(int i=0; i < a.length; i++) {
			for(int j=0; a[i] != b[j] && j < a.length; j++) {
				if(j == a.length-1 && a[i] != b[j]) {
					return false;
				}
			}
		}
		return true;
	}
}
