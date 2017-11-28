import java.util.Scanner;
import java.util.Arrays;

/**
 * Klasse zum interaktiven Test der Klasse Math. Diese Klasse erlaubt es im
 * Dialog die Methoden der Klasse Math zu testen.
 * 
 * @author Jannik Adam
 * @author Fromm-Borys
 * @version 2.0
 */
public class MathDialog {
	/**
	 * Scanner der die Benutzereingaben auswertet.
	 */
	private final Scanner input = new Scanner(System.in);
	/**
	 * Deklaration der Funktionsvariablen zum aufrufen der einzelnen
	 * Methoden.
	 */
	private static final int ENDE = 0;
	private static final int TEILER_SUMME = 1;
	private static final int PRUF_ZIFFER_ISBN_10 = 2;
	private static final int BERECHNE_NULL_STELLEN = 3;
	private static final int BERECHNE_TRIPEL = 4;
	private static final int BERECHNE_SUMME = 5;

	/**
	 * Methode, die den interaktiven Test startet.
	 * 
	 * @param args
	 *            Programmargumente, die nicht ausgewertet werden.
	 */
	public static void main(String[] args) {
		new MathDialog().start();
	}

	/**
	 * Methode, die die Funktionsvariable neutral initialisiert und auf
	 * Benutzereingaben wartet und diese auswertet. Sowie Excpetions fangt
	 * und ausgibt damit diese nicht die Methode beenden.
	 */
	public void start() {
		int funktion = -1;

		while (funktion != ENDE) {
			try {
				funktion = einlesenFunktion();
				ausfuehrenFunktion(funktion);
			} catch (java.util.InputMismatchException e) {
				System.err.println(e);
				input.next();
			} catch (java.lang.ArithmeticException e) {
				System.err.println(e);
			} catch (IllegalArgumentException e) {
				System.err.println(e);
			} catch (RuntimeException e) {
				System.err.println(e);
			}
		}
	}

	/**
	 * Methode, die dem Benutzer die Funktionsvariablen auflistet und
	 * anschlieï¿½end die Benutzereingabe der Funktonsvariable einliest.
	 * 
	 * @return int, vom Nutzer gewahlte Funktionvariable.
	 */
	private int einlesenFunktion() {
		System.out.println("Bitte Funktion Waehlen.");
		System.out.print(TEILER_SUMME + ": Teilersumme berechnen; \r\n"
				+ PRUF_ZIFFER_ISBN_10 + ": ISBN-10 Pruefziffer berechnen; \r\n"
				+ BERECHNE_NULL_STELLEN
				+ ": Quadratische Gleichung loesen; \r\n" 
				+ BERECHNE_TRIPEL
				+ ": Tripel berechnen; \r\n"
				+ BERECHNE_SUMME
				+ ": Summe berechnen; \r\n"
				+ ENDE
				+ ": Beenden; \r\n");
		return input.nextInt();
	}

	/**
	 * Methode, die die Funktionsvariable auswertet und die entsprechende
	 * Methode aufruft.
	 * 
	 * @param funktion
	 *            int, Funktionsvariable
	 */
	private void ausfuehrenFunktion(int funktion) {
		if (funktion == TEILER_SUMME) {
			System.out.println(""
					+ Math.berechneTeilerSumme(erfasseTeilerSummenZahl()));
		} else if (funktion == PRUF_ZIFFER_ISBN_10) {
			System.out.println(
					Math.berechnePrufZifferISBN_10(erfasseISBN9()));
		} else if (funktion == BERECHNE_NULL_STELLEN) {
			System.out.println(
					Math.berechneNullStellenEinerQuadratischenGleichung(
							erfasseFaktorP(), erfasseSummandQ()));
		} else if (funktion == BERECHNE_TRIPEL) {
			long[][] trippel = Math.berechneTripel(erfasseSchrankeMAX());
			for (long[] lar : trippel) {
				System.out.println(Arrays.toString(lar));
			}
		} else if (funktion == BERECHNE_SUMME) {
			System.out.println("" + ( Math.berechneSumme ( erfasseN ( ), erfasseX ( ) ) ) );
		} else if (funktion == ENDE) {
			System.out.println("Programmende");
		} else {
			System.out.println("Ungultige Funktionsvariable!");
		}
	}

	/**
	 * Methode, die die Teilersummenzahl fur das berechnen einer
	 * Teilersumme einliest.
	 * 
	 * @return int, naturliche Zahl deren Teilersumme berechnet wird.
	 */
	private int erfasseTeilerSummenZahl() {
		System.out.print("Bitte geben sie eine natuerliche Zahl ein, "
				+ "aus der die Teilersumme berechnet werden soll. ");
		return input.nextInt();
	}

	/**
	 * Methode, die die ersten neun Stellen einer ISBN-10 erfasst.
	 * 
	 * @return long, positive ganze Zahlen + die Zahl null.
	 */
	private long erfasseISBN9() {
		System.out.print("Bitte ISBN-10 ohne Pruefziffer eingeben. "
				+ "Fuehrende Nullen duerfen weggelassen werden. ");
		return input.nextLong();
	}

	/**
	 * Methode, die die Variable p einer quadratischen Funktion der Form
	 * F(x)=x^2+px+q einliest.
	 * 
	 * @return double, reelle Zahl
	 */
	private double erfasseFaktorP() {
		System.out.print("p = ");
		return input.nextDouble();
	}

	/**
	 * Methode, die die Variable q einer quadratischen Funktion der Form
	 * F(x)=x^2+px+q einliest.
	 * 
	 * @return double, reelle Zahl
	 */
	private double erfasseSummandQ() {
		System.out.print("q = ");
		return input.nextDouble();
	}
	
	/**
	 * Methode, die die schrankeMAX erfasst.
	 * 
	 * @return 
	 * 		long, natuerliche Zahl.
	 */
	private long erfasseSchrankeMAX() {
		System.out.print("Schranke = ");
		return input.nextLong();
	}
	
	/**
	 * Methode, die die Variable n der Summe erfasst.
	 * 
	 * @return 
	 * 		long, natuerliche Zahl.
	 */
	private long erfasseN() {
		System.out.print("n = ");
		return input.nextLong();
	}
	
	/**
	 * Methode, die die Variable x der Summe erfasst.
	 * 
	 * @return
	 * 		double, irgeneine reelle Zahl.
	 */
	private double erfasseX() {
		System.out.print("x = ");
		return input.nextDouble();
	}


}