package testframework.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation marks a method to be called after test execution.
 *
 * @author jannik
 * @author dominik
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface After {

}
