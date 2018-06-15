package ueb21;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Random;

public class RandomDigitSums {
	public static void main(String[] args) throws InterruptedException {
		
		Random rng = new Random();
		ArrayDeque<Integer> arrDeq = new ArrayDeque<Integer>();
		PriorityQueue<Integer> prioQue = new PriorityQueue<Integer>();
		Consumer con = new Consumer();
		int bSC = 0;
		
		for(int i = 0; i<10000; i++) {
			
			if(rng.nextInt(2) == 0 ) {
				arrDeq.offer(Producer.produce());
			}
		
			else {
				if(arrDeq.peek() == null) {
					//TODO remove
					System.out.println("BULLSHIT Aufgabe!");
					bSC++;
				}
				else {
					
					con.consume(arrDeq.poll());
				}
			}
			waitFor(1);
		}
		
		System.out.println("\r\nEs gibt " + con.numberOfDifferentResults() + " unterschiedliche Quersummen\r\n");
		
		for (int i = -1; i < 29; i++) {
			
			if(con.exists(i)) {
				
				System.out.println("Es gibt " + con.numberOfOccurrences(i) + "-mal die " + i + "!");
				System.out.println("Ihre Zeitstempel sind: " + con.getTimestampsForResult(i) + "\r\n");
			}
			else {
				
				System.out.println("Huch, die " + i + " ist wohl keine der Quersummen.\r\n");
			}
		}
		
		System.out.println("Aufsteigend sind die Quersummen: " + con.getCrossTotalsAscending() + "\r\n");
		System.out.println("Absteigend sind die Quersummen: " + con.getCrossTotalsDescending() + "\r\n");
		System.out.println("BULLSHIT^" + bSC + "!");
	}

	private static void waitFor(int i) throws InterruptedException {
		long target = System.currentTimeMillis()+i;
		while(target > System.currentTimeMillis()) {
			Thread.sleep(0);
		}
	}
}
