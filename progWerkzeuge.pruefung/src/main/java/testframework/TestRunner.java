package testframework;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import testframework.annotations.After;
import testframework.annotations.Before;
import testframework.annotations.TestMethod;

public class TestRunner {

	public static void main(String[] args) {
		try {
			for (String testsuite : args) {
				Class<?> c = Class.forName(testsuite);
				System.out.println("Overall test suite setup");
				invokeTestMethods(c, Before.class);
				System.out.println("Executing test Methods");
				invokeTestMethods(c,TestMethod.class);
				System.out.println("Overall test suite tear down");
				invokeTestMethods(c, After.class);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

	private static <T extends Annotation> void invokeTestMethods(Class<?> c, Class<T> annotation) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
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
