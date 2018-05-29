package utils;

import java.util.function.Consumer;

public class UpcastConsumerBuilder {

	public static <T, S extends T> Consumer<T> upcastPredicate(Consumer<
			S> actual, Class<S> type) {
		return (t) -> {
			if (type.isInstance(t)) {
				actual.accept(type.cast(t));
			}
		};
	}

}
