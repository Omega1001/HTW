package ueb21;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class RandomDigitSums {
		
		private Random rng = new Random();
		private Queue<Integer> queue;
		private ArrayDeque<Integer> arrDeq = new ArrayDeque<Integer>();
		private PriorityQueue<Integer> priQue = new PriorityQueue<Integer>();
		private Consumer consumer;
		private int bSC = 0;
		private int proCou = 0;
		
		public RandomDigitSums (int mode) throws InterruptedException {
			
			if (mode < 0 || mode > 1) {
				
				throw new IllegalArgumentException ("Unsuported operation!");
			}
			
			if (mode == 1) {
				
				this.queue = arrDeq;
			}
			else {
				
				this.queue = priQue;
			}
			
			this.consumer = new Consumer();
			
			for(int i = 0; i < 10000; i++) {
				
				if(rng.nextInt(2) == 0 ) {
					
					queue.offer(Producer.produce());
					proCou++;
				}
			
				else {
					if(queue.peek() == null) {
						
						System.out.println("consume-Aufruf auf leere Queue!");
						bSC++;
					}
					else {
						
						consumer.consume(queue.poll());
					}
				}
				waitFor(1);
			}
			
			System.out.println("\r\nEs gab " + bSC + " consume-Aufrufe auf eine leere Queue!");
		}

		public Consumer getConsumer() {
		
			return this.consumer;
		}
		
		public int getBSC() {
			
			return bSC;
		}
		
		public int getProCou() {
			
			return proCou;
		}

		private static void waitFor(int i) throws InterruptedException {
			long target = System.currentTimeMillis()+i;
			while(target > System.currentTimeMillis()) {
				Thread.sleep(0);
			}
		}
}
