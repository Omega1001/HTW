package testframework.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation marks a method to be called before test execution.
 *
 * @author jannik
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Before {

}
