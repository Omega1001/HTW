package ueb17;

public class AandPMethodsAnonym {

	public static final MyFunction I = new MyFunction() {
		@Override
		public int apply(int i) {
			return i * i;
		}
	};

	public static final MyFunction II = new MyFunction() {

		@Override
		public int apply(int i) {

			int result = 1;

			for (int j = 2; j <= i; j++) {
				result = result * j;
			}
			return result;
		}
	};

	public static final MyFunction III = new MyFunction() {

		@Override
		public int apply(int i) {

			return (int) Math.pow(i, i++);
		}
	};

	public static final MyFunction IV = new MyFunction() {

		@Override
		public int apply(int i) {

			int fib1 = 0;
			int fib2 = 1;

			for (int j = 1; j <= i; j++) {
				fib1 += fib2;
				fib2 -= fib1;
			}
			return fib1;
		}
	};
}
