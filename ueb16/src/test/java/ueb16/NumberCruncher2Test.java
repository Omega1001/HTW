package ueb16;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.ExecutionProxy;

public class NumberCruncher2Test {

	private NumberCruncher2 underTest;

	private static NumberCruncherOpperation op1 = ExecutionProxy.getProxy(
			NumberCruncherOpperation.class);
	private static NumberCruncherOpperation op2 = ExecutionProxy.getProxy(
			NumberCruncherOpperation.class);
	private static NumberCruncherOpperation op3 = ExecutionProxy.getProxy(
			NumberCruncherOpperation.class);
	private static NumberCruncherOpperation op4 = ExecutionProxy.getProxy(
			NumberCruncherOpperation.class);
	private static NumberCruncherOpperation op5 = ExecutionProxy.getProxy(
			NumberCruncherOpperation.class);

	@BeforeClass
	public static void init() {
		NumberCruncher2.setOpperation("SUM", op1);
		NumberCruncher2.setOpperation("DIVIDE", op2);
		NumberCruncher2.setOpperation("SUBTRACT", op3);
		NumberCruncher2.setOpperation("SWIRL", op4);
		NumberCruncher2.setOpperation("AVERAGE", op5);
	}

	@Before
	public void setUp() throws Exception {
		underTest = new NumberCruncher2(5);
		float[] numbers = underTest.getNumbers();
		ExecutionProxy.reset(op1);
		ExecutionProxy.setReturnValue(op1, numbers);
		ExecutionProxy.reset(op2);
		ExecutionProxy.setReturnValue(op2, numbers);
		ExecutionProxy.reset(op3);
		ExecutionProxy.setReturnValue(op3, numbers);
		ExecutionProxy.reset(op4);
		ExecutionProxy.setReturnValue(op4, numbers);
		ExecutionProxy.reset(op5);
		ExecutionProxy.setReturnValue(op5, numbers);
	}

	@Test
	public void constructor() {
		underTest = new NumberCruncher2(5);
		assertNotNull(underTest.getNumbers());
		assertEquals(5, underTest.getNumbers().length);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor2() {
		underTest = new NumberCruncher2(0);
	}

	@Test
	public void testCrunch() {
		float[] numbers = underTest.getNumbers();
		ExecutionProxy.setReturnValue(op1, numbers);
		underTest.crunch(new String[] { "SUM", "SUBTRACT", "AVERAGE" });
		checkOpperationCalled(op1, numbers);
		checkOpperationCalled(op3, numbers);
		checkOpperationCalled(op5, numbers);
		checkOpperationNotCalled(op2);
		checkOpperationNotCalled(op4);
	}

	@Test
	public void testCrunch2() {
		underTest.crunch(new String[] { "sum", "sUbTrAcT", "AvErAgE" });
		float[] numbers = underTest.getNumbers();
		checkOpperationCalled(op1, numbers);
		checkOpperationCalled(op3, numbers);
		checkOpperationCalled(op5, numbers);
		checkOpperationNotCalled(op2);
		checkOpperationNotCalled(op4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCrunch3() {
		underTest.crunch(new String[] { "sum", null, "AvErAgE" });
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCrunch4() {
		underTest.crunch(new String[] { "sum", "BÄÄÄÄÄÄÄH", "AvErAgE" });
	}

	private void checkOpperationNotCalled(NumberCruncherOpperation op) {
		assertNull("Method called, when it shouln'd have been",
				ExecutionProxy.getLastCalledMethod(op));
	}

	private void checkOpperationCalled(NumberCruncherOpperation op,
			Object... args) {
		Method m = ExecutionProxy.getLastCalledMethod(op);
		assertNotNull("No method called since last reset", m);
		assertEquals("Last Called Method was not apply method", "apply", m
				.getName());
		arrayEquals(ExecutionProxy.getLastCalledMethodArgs(op), args);
	}

	private void arrayEquals(Object[] called, Object[] expected) {
		if (called.length != expected.length) {
			fail("Array length differes from expected");
		}
		for (int i = 0; i < called.length; i++) {
			Object first = called[i];
			Object second = expected[i];
			if (first == null && second == null) {
				continue;
			} else if (first == null || second == null) {
				fail("unexpected null value");
			} else {
				if (first.getClass().isArray()) {
					if (second.getClass().isArray()) {
						arrayEquals(castArray(first), castArray(second));
					} else {
						fail("Parameter at index " + i
								+ " is an array, but non was expected");
					}
				}
				if (!first.equals(second)) {
					fail("detected differenc at index" + i + " was "
							+ first.toString() + " but expected " + second
									.toString());
				}
			}
		}
	}

	private Object[] castArray(Object arr) {
		Object[] res = new Object[Array.getLength(arr)];
		for (int i = 0; i < res.length; i++) {
			res[i] = Array.get(arr, i);
		}
		return res;
	}

}
