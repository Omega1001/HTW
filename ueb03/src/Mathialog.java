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
		 * Scanner der die Benutzereingaben auswertet.
		 */
		private final Scanner Input = new Scanner ( System.in );
		/**
		 * Deklaration der Funktionsvariablen zum aufrufen der einzelnen Methoden.
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
            catch ( java.util.InputMismatchException e )// nach einem catch muss erst eine eingabe gettigt werden bevor erneut einge eingabeaufforderung ausgegeben wird.
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
            catch ( RuntimeException e )
            {
                System.out.println ( e );
                Input.next ( );
            }
        }
	 }
			/**
			 * Methode, die dem Benutzer die Funktionsvariablen auflistet 
			 * und anschließend die Benutzereingabe der Funktonsvariable einliest.
			 * 
			 * @return
			 * 		int, vom Nutzer gewahlte Funktionvariable.
			 */
            private int einlesenFunktion ( )
            {
                System.out.println ( "Bitte Funktion Whlen." );
                System.out.print ( TEILER_SUMME 	   								  
                				  + ": Teilersumme berechnen; "
                				  + PRUF_ZIFFER_ISBN_10 								  
                				  + ": ISBN-10 Prüfziffer berechnen; "
                				  + BERECHNE_NULL_STELLEN_EINER_QUADRATISCHEN_GLEICHUNG 
                				  + ": Quadratische Gleichung lösen; "
                				  + ENDE 			   								  
                				  + ": Beenden; "                      				   );
                return Input.nextInt ( );
            }
            /**
             * Methode, die die Funktionsvariable auswertet
             * und die entsprechende Methode aufruft.
             * 
             * @param funktion
             * 		int, Funktionsvariable
             */
            private void ausfuehrenFunktion ( int funktion )
            {
                if ( funktion == TEILER_SUMME )
                {
                	System.out.println ( "" 
                			+ Math.berechneTeilerSumme ( erfasseTeilerSummenZahl ( ) ) );
                }
                else if ( funktion == PRUF_ZIFFER_ISBN_10 )
                {
                	System.out.println 
                			( Math.berechnePrufZifferISBN_10 ( erfasseISBN9 ( ) ) );
                }
                else if ( funktion == BERECHNE_NULL_STELLEN_EINER_QUADRATISCHEN_GLEICHUNG )
                {
                	System.out.println ( Math.berechneNullStellenEinerQuadratischenGleichung 
                						( erfasseFaktorP ( ) 
                						, erfasseSummandQ ( ) )							 );
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
             * 		int, naturliche Zahl deren Teilersumme berechnet wird.
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
             * 		long, positive ganze Zahlen + die Zahl null.
             */
            private long erfasseISBN9 ( ) 
            {
                System.out.print 
                	( "Bitte ISBN-10 ohne Prüfziffer eingeben. "
                			+ "Führende Nullen dürfen weggelassen werden. " );
                return Input.nextLong ( );
            }
            /**
             * Methode, die die Variable p einer quadratischen Funktion
             * der Form F(x)=x^2+px+q einliest.
             * 
             * @return
             * 		double, reelle Zahl
             */
            private double erfasseFaktorP ( ) 
            {
            	System.out.print ( "p = " );
                return Input.nextDouble ( );
            }
            /**
             * Methode, die die Variable q einer quadratischen Funktion
             * der Form F(x)=x^2+px+q einliest.
             * 
             * @return
             * 		double, reelle Zahl
             */
            private double erfasseSummandQ ( ) 
            {
            	System.out.print ( "q = " );
                return Input.nextDouble ( );
            }
            
}