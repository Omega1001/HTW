package ueb21;

public class Main {

public static void main(String[] args) throws InterruptedException {
		
		RandomDigitSums ranDigSum = new RandomDigitSums(0);
		
		System.out.println("\r\nEs gibt " + ranDigSum.getConsumer().numberOfDifferentResults() + " unterschiedliche Quersummen.\r\n");
		
		for (int i = 0; i < 29; i++) {
			
			if(ranDigSum.getConsumer().exists(i)) {
				
				System.out.println("Es gibt " + ranDigSum.getConsumer().numberOfOccurrences(i) + "-mal die " + i + "!");
				System.out.println("Ihre Zeitstempel sind: " + ranDigSum.getConsumer().getTimestampsForResult(i) + "\r\n");
			}
			else {
				
				System.out.println("Huch, die " + i + " ist wohl keine der Quersummen.\r\n");
			}
		}
		
		System.out.println("Aufsteigend sind die Quersummen: " + ranDigSum.getConsumer().getCrossTotalsAscending() + "\r\n");
		System.out.println("Absteigend sind die Quersummen: " + ranDigSum.getConsumer().getCrossTotalsDescending() + "\r\n");
	}
}
