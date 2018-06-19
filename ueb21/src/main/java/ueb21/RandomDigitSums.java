package ueb21;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RandomDigitSums implements Runnable{

	private Random rng = new Random();
	private Queue<Integer> queue;
	private Consumer<Integer> consumer;
	private Supplier<Integer> supplier;
	private int bSC = 0;
	
	public RandomDigitSums(int mode) throws InterruptedException {
		this(mode,new MyConsumer(),new MyProducer());
	}

	public RandomDigitSums(int mode, Consumer<Integer> consumer, Supplier<Integer> supplier) {

		if (mode < 0 || mode > 1) {

			throw new IllegalArgumentException("Unsuported operation!");
		}
		
		if(consumer == null || supplier == null) {
			throw new IllegalArgumentException("Supplier and consumer must not be null");
		}
		this.supplier = supplier;
		this.consumer = consumer;
		if (mode == 1) {
			this.queue = new PriorityQueue<>();
		} else {

			this.queue = new LinkedList<>();
		}
		
		
	}
	
	public void run() {
		for (int i = 0; i < 1e4; i++) {

			if (rng.nextInt(2) > 0) {
				queue.offer(supplier.get());
			} else {
				if (queue.peek() == null) {
					System.out.println("consume-Aufruf auf leere Queue!");
					bSC++;
				} else {
					consumer.accept(queue.poll());
				}
			}
			try {
				waitFor(1);
			} catch (InterruptedException e) {
				System.out.println("Cought Interruped, exiting ...");
				break;
			}
		}

		System.out.println("\r\nEs gab " + bSC
				+ " consume-Aufrufe auf eine leere Queue!");
	}

	public Consumer<Integer> getConsumer() {
		return this.consumer;
	}
	
	

	/**
	 * @return the supplier
	 */
	public Supplier<Integer> getSupplier() {
		return supplier;
	}

	private static void waitFor(int i) throws InterruptedException {
		long target = System.currentTimeMillis() + i;
		while (target > System.currentTimeMillis()) {
			Thread.sleep(0);
		}
	}
}
