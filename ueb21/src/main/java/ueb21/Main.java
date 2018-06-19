package ueb21;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		RandomDigitSums ranDigSum = new RandomDigitSums(0);
		ranDigSum.run();
		if (ranDigSum.getConsumer() instanceof MyConsumer) {
			MyConsumer con = (MyConsumer) ranDigSum.getConsumer();
			System.out.println("\r\nEs gibt " + con
					.numberOfDifferentResults()
					+ " unterschiedliche Quersummen.\r\n");

			for (int i = 0; i < 29; i++) {

				if (con.exists(i)) {

					System.out.println("Es gibt " + con
							.numberOfOccurrences(i) + "-mal die " + i
							+ "!");
					System.out.println("Ihre Zeitstempel sind: " + con
							.getTimestampsForResult(i) + "\r\n");
				} else {

					System.out.println("Huch, die " + i
							+ " ist wohl keine der Quersummen.\r\n");
				}
			}

			System.out.println("Aufsteigend sind die Quersummen: " + con
					.getCrossTotalsAscending() + "\r\n");
			System.out.println("Absteigend sind die Quersummen: " + con
					.getCrossTotalsDescending() + "\r\n");
		} else {
			System.out.println(
					"No statistics availeble, no MyConsumer in service");
		}
	}
}
