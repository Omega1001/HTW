import java.util.Scanner;
/**
 * Klasse zum interaktiven Test der Klasse Math.
 * Diese Klasse erlaubt es im Dialog die Methoden der Klasse Math zu testen.
 * 
 * @author Jannik Adam
 * @author Fromm-Borys
 * @version 0.4
 */
public class Mathialog
{
		/**
		 * Scaner der die Benutzereingaben auswertet.
		 */
		private final Scanner Input = new Scanner ( System.in );
		/**
		 * Deklarieren der Funktionvariablen zum aufrufen der einzelnen Methoden.
		 */
		private static final int ENDE 		 		 								 = 0;
		private static final int TEILER_SUMME 		 								 = 1;
		private static final int PRUF_ZIFFER_ISBN_10 								 = 2;
		private static final int BERECHNE_NULL_STELLEN_EINER_QUADRATISCHEN_GLEICHUNG = 3;
	/**
	 * Methode, die den interaktiven Test startet.
	 * 
	 * @param args
	 * 		Programmargumente, die nicht ausgewertet werden.
	 */
	public static void main ( String [ ] args )
	{
		new Mathialog ( ).start ( );
	}
	/**
	 * Methode, die die Funktionsvariable neutral initialisiert 
	 * und auf Benutzereingaben wartet und diese auswertet.
	 * Sowie Excpetions fangt und ausgibt damit diese nicht die Methode beenden.
	 */
	public void start ( )
	{
        int funktion = -1;

        while ( funktion != ENDE )
        {
            try
            {
                funktion = einlesenFunktion ( );
                ausfuehrenFunktion ( funktion );
            } 
            catch ( java.util.InputMismatchException e )
            {
                System.out.println ( e );
                Input.next ( );
            }
            catch ( java.lang.ArithmeticException e )
            {
                System.out.println ( e );
                Input.next ( );
            }
            catch ( IllegalArgumentException e )
            {
                System.out.println ( e );
                Input.next ( );
            }
        }
	 }
			/**
			 * Methode, die dem Benutzer die Funktionsvariablen auflistet 
			 * und anschlie�end die Benutzereingabe der Funktonsvariable einliest.
			 * 
			 * @return
			 * 		int, vom Nutzer gew�hlte Funktionvariable.
			 */
            private int einlesenFunktion ( )
            {
                System.out.println ( "Bitte Funktion W�hlen." );
                System.out.print ( TEILER_SUMME 	   								  + ": Teilersumme berechnen; "
                				 + PRUF_ZIFFER_ISBN_10 								  + ": ISBN-10 Pr�fziffer berechnen; "
                				 + BERECHNE_NULL_STELLEN_EINER_QUADRATISCHEN_GLEICHUNG + ": Quadratische Gleichung l�sen; "
                				 + ENDE 			   								  + ": Beenden; "                      );
                return Input.nextInt ( );
            }
            /**
             * Methode, die die Funktionsvariable auswertet
             * und die entsprechende Methode startet.
             * 
             * @param funktion
             * 		int, Funktionsvariable
             */
            private void ausfuehrenFunktion ( int funktion )
            {
                if ( funktion == TEILER_SUMME )
                {
                	
                	System.out.println ("Teilersumme = " + Math.berechneTeilerSumme ( erfasseTeilerSummenZahl ( ) ) );
                }
                else if ( funktion == PRUF_ZIFFER_ISBN_10 )
                {
                	System.out.println ( Math.berechnePrufZifferISBN_10 ( erfasseISBN_10_9 ( ) ) );
                }
                else if ( funktion == BERECHNE_NULL_STELLEN_EINER_QUADRATISCHEN_GLEICHUNG )
                {
                	System.out.println ( Math.berechneNullStellenEinerQuadratischenGleichung ( erfasseVariablePe ( ) , erfasseVariableQu ( ) ) );
                }
                else if ( funktion == ENDE )
                {
                    System.out.println ( "Programmende" );
                }
                else
                {
                    System.out.println ( "Ungultige Funktionsvariable!" );
                }
            }
            /**
             * Methode, die die Teilersummenzahl
             * fur das berechnen einer Teilersumme einliest.
             * 
             * @return
             * 		int, nat�rliche Zahl deren Teilersumme berechnet wird.
             */
            private int erfasseTeilerSummenZahl ( ) 
            {
                System.out.print ( "X = " );
                return Input.nextInt ( );
            }
            /**
             * Methode, die die ersten neun Stellen einer ISBN-10 erfasst.
             * 
             * @return
             * 		long, ganze Zahlen => Null.
             */
            private long erfasseISBN_10_9 ( ) 
            {
                System.out.print ( "ISBN-10 ohne Pr�fziffer lautet? " );
                return Input.nextLong ( );
            }
            /**
             * Methode, die die Variable p einer quadratischen Funktion
             * der Form F(x)=x^2+px+q einliest.
             * 
             * @return
             * 		double, reelle Zahl
             */
            private double erfasseVariablePe ( ) 
            {
            	System.out.print ( "P = " );
                return Input.nextDouble ( );
            }
            /**
             * Methode, die die Variable q einer quadratischen Funktion
             * der Form F(x)=x^2+px+q einliest.
             * 
             * @return
             * 		double, reelle Zahl
             */
            private double erfasseVariableQu ( ) 
            {
            	System.out.print ( "q = " );
                return Input.nextDouble ( );
            }
            
}