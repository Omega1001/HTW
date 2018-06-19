package ueb21;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class RandomDigitSums {

	private Random rng = new Random();
	private Queue<Integer> queue;
	private Consumer consumer;
	private int bSC = 0;

	public RandomDigitSums(int mode) throws InterruptedException {

		if (mode < 0 || mode > 1) {

			throw new IllegalArgumentException("Unsuported operation!");
		}

		if (mode == 1) {

			this.queue = new PriorityQueue<>();
		} else {

			this.queue = new LinkedList<>();
		}

		this.consumer = new Consumer();

		for (int i = 0; i < 1e4; i++) {

			if (rng.nextInt(2) > 0) {
				queue.offer(Producer.produce());
			} else {
				if (queue.peek() == null) {
					System.out.println("consume-Aufruf auf leere Queue!");
					bSC++;
				} else {
					consumer.consume(queue.poll());
				}
			}
			waitFor(1);
		}

		System.out.println("\r\nEs gab " + bSC
				+ " consume-Aufrufe auf eine leere Queue!");
	}

	public Consumer getConsumer() {

		return this.consumer;
	}

	private static void waitFor(int i) throws InterruptedException {
		long target = System.currentTimeMillis() + i;
		while (target > System.currentTimeMillis()) {
			Thread.sleep(0);
		}
	}
}
