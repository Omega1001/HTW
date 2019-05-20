package testframework;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import testframework.annotations.After;
import testframework.annotations.Before;
import testframework.annotations.TestMethod;

/**
 * This class is in charge of running test suits.<br>
 * This method features two possibilities for interaction:
 * <table>
 * <tr>
 * <td>Method</td>
 * <td>Usage</td>
 * <tr>
 * <td>{@link #main(String[])}</td>
 * <td>This method is used to run the Tester from command Line<br>
 * It expects a List of classes, that contain the actual tests<br>
 * Please note, that all classnames have to be fully qualified<br>
 * The Tester will be executed in the order they where supplied on the
 * commandline<br>
 * </td>
 * </tr>
 * <tr>
 * <td>{@link #runTestSuit(Class)}</td>
 * <td>This method is used to execute a speciffic tester, that is supplied
 * programatically<br>
 * This Method is mainly called when this program is used as part of an other
 * program</td>
 * </tr>
 * </table>
 *
 * @author jannik
 * @author dominik
 *
 */
public class TestRunner {
	/**
	 * This method is used to run the Tester from commandline.<br>
	 * It will attempt to load all classes supplied in order of specification and
	 * pass them to the {@link #runTestSuit(Class)} method
	 * <p>
	 * Please note, that all paths have to be absolute in order to be loaded<br>
	 * If a class can not be loaded, an exception will be printed<br>
	 * The failed class will be skiped and this method will attempt to load the next
	 * specified
	 *
	 * @param args List of full qualified class names that are to be used as tester
	 */
	public static void main(String[] args) {
		for (String testsuite : args) {
			try {
				Class<?> c = Class.forName(testsuite);
				System.out.println("Running test suit " + testsuite);
				runTestSuit(c);
			} catch (ClassNotFoundException e) {
				System.err.println("Unable to run testsuit " + testsuite);
				e.printStackTrace(System.err);
				System.err.println("Skipping ...");
			}
		}
	}

	/**
	 * This method is used to execute a specified tester.<br>
	 * This method will execute all methods annotated with the following Annotations
	 * in the following order:
	 * <ol>
	 * <li>{@link Before}</li>
	 * <li>{@link TestMethod}</li>
	 * <li>{@link After}</li>
	 * </ol>
	 * In case any of the method throws an exception, it is printed and the method
	 * will return<br>
	 * Exceptions are not thrown
	 *
	 * @param testSuit to run
	 */
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

	/**
	 * This method executes all methods in the provided class annotated with the
	 * specified annotation.<bt>
	 *
	 * @param c          class to query
	 * @param annotation to look for
	 * @throws IllegalAccessException    if the method can not be called
	 * @throws IllegalArgumentException  if a called method was provided with
	 *                                   illegal arguments
	 * @throws InvocationTargetException if a called method throws an exception
	 * @throws InstantiationException    if no instance of that class can be created
	 */
	private static void invokeTestMethods(Class<?> c, Class<? extends Annotation> annotation)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		for (Method m : c.getMethods()) {
			if (m.getAnnotation(annotation) != null) {
				m.invoke(c.newInstance());
			}
		}
	}

	/**
	 * This method compares two booleans. It will print "pass" on std.out if the two
	 * booleans match, "fail" otherwais
	 *
	 * @param expected value
	 * @param actual   value
	 */
	public static void assertEquals(boolean expected, boolean actual) {
		System.out.print("\t\ttest verdict: ");
		if (expected == actual) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
	}
}
