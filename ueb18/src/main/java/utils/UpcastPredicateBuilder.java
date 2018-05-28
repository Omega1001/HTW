package utils;

import java.util.function.Predicate;

public class UpcastPredicateBuilder {

	public static <T, S extends T> Predicate<T> upcastPredicate(Predicate<
			S> actual, Class<S> type) {
		return upcastPredicate(actual, type, false);
	}

	public static <T, S extends T> Predicate<T> upcastPredicate(Predicate<
			S> actual, Class<S> type, boolean elseReturn) {
		return (t) -> {
			if (type.isInstance(t)) {
				return actual.test(type.cast(t));
			} else {
				return elseReturn;
			}
		};
	}

}
