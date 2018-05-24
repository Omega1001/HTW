package ueb17;

public class TopAndStaticNestedFaculty implements MyFunction {

	@Override
	public int apply(int i) {

		int result = 1;

		for (int j = 2; j <= i; j++) {
			result = result * j;
		}
		return result;
	}
	
	public static final class NestedFaculty implements MyFunction{
		@Override
		public int apply(int i) {

			int result = 1;

			for (int j = 2; j <= i; j++) {
				result = result * j;
			}
			return result;
		}
	}
}
