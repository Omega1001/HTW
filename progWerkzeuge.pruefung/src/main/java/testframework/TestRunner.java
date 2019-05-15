package testframework;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import testframework.annotations.After;
import testframework.annotations.Before;
import testframework.annotations.TestMethod;

public class TestRunner {

	public static void main(String[] args) {
		for (String testsuite : args) {
			try {
				Class<?> c = Class.forName(testsuite);
				System.out.println("Running test suit "+testsuite);
				runTestSuit(c);
			} catch (ClassNotFoundException e) {
				System.err.println("Unable to run testsuit "+testsuite);
				e.printStackTrace(System.err);
				System.err.println("Skipping ...");
			}
		}
	}

	public static void runTestSuit(Class<?> testSuit) {
		try {
			System.out.println("Overall test suite setup");
			invokeTestMethods(testSuit, Before.class);
			System.out.println("Executing test Methods");
			invokeTestMethods(testSuit, TestMethod.class);
			System.out.println("Overall test suite tear down");
			invokeTestMethods(testSuit, After.class);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	private static <T extends Annotation> void invokeTestMethods(Class<?> c, Class<T> annotation)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		for (Method m : c.getMethods()) {
			if (m.getAnnotation(annotation) != null) {
				m.invoke(c.newInstance());
			}
		}
	}

	public static void assertEquals(boolean expected, boolean actual) {
		System.out.print("\t\ttest verdict: ");
		if (expected == actual) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
	}
}
