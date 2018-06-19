package ueb21;

import static org.junit.Assert.*;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;

import utils.DataProxy;

public class RandomDigitSumsTest {

	private RandomDigitSums underTest;
	private SupplierProxy sProxy = new SupplierProxy();
	private ConsumerProxy cProxy = new ConsumerProxy();

	@Before
	public void init() {
		underTest = new RandomDigitSums(0, cProxy, sProxy);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRandomDigitSumsConstructor1()
			throws InterruptedException {
		underTest = new RandomDigitSums(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRandomDigitSumsConstructor2()
			throws InterruptedException {
		underTest = new RandomDigitSums(1, null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRandomDigitSumsConstructor3()
			throws InterruptedException {
		underTest = new RandomDigitSums(1, new MyConsumer(), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRandomDigitSumsConstructor4()
			throws InterruptedException {
		underTest = new RandomDigitSums(1, null, new MyProducer());
	}

	@Test
	public void testRandomDigitSumsConstructor5()
			throws InterruptedException {
		underTest = new RandomDigitSums(0, new MyConsumer(),
				new MyProducer());
	}

	@Test
	public void testRandomDigitSumsConstructor6()
			throws InterruptedException {
		underTest = new RandomDigitSums(1, new MyConsumer(),
				new MyProducer());
	}
	
	@Test
	public void testExecution() {
		final DataProxy<Boolean> sCall = new DataProxy<Boolean>(false);
		final DataProxy<Boolean> cCall = new DataProxy<Boolean>(false);
		sProxy.setActual(()->{sCall.setData(true);return 5;});
		cProxy.setActual((i)->cCall.setData(true));
		underTest.run();
		assertTrue(sCall.getData());
		assertTrue(cCall.getData());
	}
	


private class SupplierProxy implements Supplier<Integer> {

		private Supplier<Integer> actual;

		@Override
		public Integer get() {
			return actual != null ? actual.get() : 0;
		}

		/**
		 * @return the actual
		 */
		public Supplier<Integer> getActual() {
			return actual;
		}

		/**
		 * @param actual
		 *            the actual to set
		 */
		public void setActual(Supplier<Integer> actual) {
			this.actual = actual;
		}

	}

	private class ConsumerProxy implements Consumer<Integer> {

		private Consumer<Integer> actual;

		@Override
		public void accept(Integer t) {
			if (actual != null) {
				actual.accept(t);
			}
		}

		/**
		 * @return the actual
		 */
		public Consumer<Integer> getActual() {
			return actual;
		}

		/**
		 * @param actual
		 *            the actual to set
		 */
		public void setActual(Consumer<Integer> actual) {
			this.actual = actual;
		}

	}

}
