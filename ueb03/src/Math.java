/**
 * Klasse die simple Mathematische Methoden anbietet.
 * 
 * @author Jannik Adam
 * @author Fromm-Borys
 * @version 0.4
 */
public class Math 
{	
	/**
	 * Methode, die die Teilersumme einer naturlichen Zahl berechnet.
	 * 
	 * @param teilerSummenZahl
	 * 		Die Zahl von der die Teilersumme berechnet werden soll.
	 * @return
	 * 		String, der errechneten Teilersumme. 
	 */
	public static String berechneTeilerSumme ( int teilerSummenZahl )
	{			
		int teilerSumme = 0;
		int teiler 		= 0;
		int moduloErgebnis ;
		
		/**
		 * Prufung ob die Nutzereingabe eine naturliche Zahl ist.
		 */
		if ( teilerSummenZahl < 1)
		{
			throw new IllegalArgumentException 
				( "Die Teilersummenzahl muss eine natürliche Zahl sein!" );
		}
		else 
		{	
			/**
			 * Fortlaufende Anwendung von Modulo mit naturlichen Zahlen
			 * bis dieese Zahl der Teilersummenzahl entspricht.
			 */
			while ( teiler <= teilerSummenZahl )// 2147483647 führt zu java.lang.ArithmeticException: / by zero
			{
				teiler++;
				moduloErgebnis = teilerSummenZahl % teiler;
				
				/**
				 * Prufung auf ganzzahlige teiler durch das Moduloergebnis
				 * und deren addition falls bestanden.
				 */
				if ( moduloErgebnis == 0 ) 
				{
					teilerSumme = teilerSumme + teiler;
				}
			}
		}
		/**
		 * Prufung ob ein Uberlauf stattgefunden hat.
		 * Wenn, dann wird eine Runtime Exception geworfen,
		 */
		if ( teilerSumme < teilerSummenZahl )
		{
			throw new RuntimeException( "Falsches Ergebnis durch Überlauf!" );
		}
		/**
		 * Prufung ob die Teilersummenzahl eine Primzahl ist.
		 * Wenn, dann primzahlspezifische Ausgabe.
		 */
		else if ( teilerSumme == teilerSummenZahl + 1 )
		{
			return ( "Die Teilersumme ist " + teilerSumme + ". " 
					+ teilerSummenZahl + "ist eine Primzahl!" );
		}
		/**
		 * Ausgabe für gewöhnliche Teilersummen.
		 */
		else
		{
			return ( "Die Teilersumme ist " + teilerSumme + "." );
		}
	}
	/**
	 * Methode die aus den ersten neun Ziffern einer ISBN-10 die Prufziffer errechent.
	 * 
	 * @param iSBN9
	 * 		long, das die ersten neun Stellen einer ISDN-10 einliest.
	 * @return
	 * 		String, der Prufziffer einer ISBN-10.
	 */
	public static String berechnePrufZifferISBN_10 ( long iSBN9 )
	{
		final long MIN_ISBN_9 = 		1;
		final long MAX_ISBN_9 = 999999999;
		long gekurzteISBN9			     ;
		long ziffer						 ;
		long prufSumme				  = 0;
		long index					  = 9;
		String fuhrendeNullen		= "" ;
		String prufZiffer				 ;
		
		/**
		 * Prufung ob die Eingabe einer ISBN-10 ihne Prufziffer entspricht
		 * wobei fuhrende Nullen vom Nutzer weglassen darf.
		 */
		if (iSBN9 < MIN_ISBN_9 | iSBN9 > MAX_ISBN_9) 
		{
			throw new IllegalArgumentException 
				( " Die einzugebende ISBN muss eine ganze 9-stellige Zahl sein!" );
		}
		else
		{	
			gekurzteISBN9 = iSBN9;
			
			/**
			 * Fortlaufendes Abschneiden der letzten Ziffer der 9-stelligen
			 * ISBN-10, Anwendung von Modulo 10 auf diese anschliesende 
			 * Faktorisierung mit einem von 9 ablaufendem Index und anschließende
			 * Addition mit der Prufsumme.
			 */
			while ( gekurzteISBN9 > 0 )
			{
			ziffer = gekurzteISBN9 % 10;
			gekurzteISBN9 = gekurzteISBN9 / 10;
			prufSumme = prufSumme + ziffer * index--;
			}
			
			prufSumme = prufSumme % 11;
			
			/**
			 * Prufung ob für die Prufsumme der Sonderfall 10 gilt
			 * und entsprechende Ausgabe der Prufziffer.
			 */
			if (prufSumme < 10 )
			{
				prufZiffer = ( "" + prufSumme );
			}
			else
			{
				prufZiffer = "X";
			}
			/**
			 * Fortlaufendes Hinzufügen von Führungsnullen 
			 * entsprechend der vom Index ermittelten Anzahl.
			 */
			while ( index > 0)
			{
				fuhrendeNullen = ( fuhrendeNullen + "0");
				index--;
			}
		}
		/**
		 * Erstellung der vollständigen ISBN-10 durch die Verknüpfung der
		 * einzelnen Teilelemente sowie deren Ausgabe.
		 */
		return ( "ISBN-10 Prüfziffer lautet: " + prufZiffer 
				+ " Die vollständige ISBN-10 lautet " 
				+ fuhrendeNullen + iSBN9 + "-" + prufZiffer );
	}
	/**
	 * Methode zum ermitteln der Nullstellen 
	 * einer quadratischen funktion der Form f(x)=X^2+px+q.
	 * 
	 * @param faktorP
	 * 		double, Variable p einer quadratischen Gleichung.
	 * @param summandQ
	 * 		double, Variable q einer quadratischen Gleichung.
	 * @return
	 * 		String, der Nullstellen einer quadratischen Gleichung.
	 */
	public static String berechneNullStellenEinerQuadratischenGleichung 
					( double faktorP, double summandQ )
	{
		double diskriminante	  ;
		long zwischenDiskriminante;
		String ergebnis			  ;
		
		diskriminante = ( faktorP / 2 ) * ( faktorP / 2 ) - summandQ;
		
		/**
		 * Runden der Diskriminante auf eine Nachkommastellen 
		 * um das Problem der Ungenauigkeit der Gleitpunktarithmetic zu vermindern.
		 */
		diskriminante = diskriminante * 10;// dieser Block ist unschön allerdings führt D=((long)(D*100))/100; immer zu X.0 kann keinen dieser 4 Schritte in einen andern integrieren ohne ALLE Nachkommastellen zu verlieren.
		zwischenDiskriminante = ( ( long ) diskriminante );
		diskriminante = zwischenDiskriminante;
		diskriminante = diskriminante /10;

		/**
		 * Fallunterscheidung auf Grund der ermittelten Diskriminante und
		 * entrsprechende Ermittlung von einer, zwei Nullstellen oder 
		 * der Hinweis auf komplexe Nullstellen. 
		 */
		if ( diskriminante > 0.0 )
		{
			ergebnis = ( pQFormel ( faktorP, summandQ, true ) 
						+ " und " + pQFormel ( faktorP, summandQ, false ) + "." );
			
		}
		else if ( diskriminante == 0.0 )
		{
			ergebnis = ( + pQFormel ( faktorP, summandQ, false) + "." ); 
		}
		else
		{
			ergebnis = ( "zu komplex!" );
		}
		return ( "Die Nullstelle/n der quadratischen Gleichung x²+" 
				+ faktorP + "x" + ( summandQ < 0 ? "" : "+" ) + summandQ + " sind " + ergebnis );
	}
	/**
	 * 
	 * @param p
	 * 		double, Variable p einer quadratischen Gleichung.
	 * @param q
	 * 		double, Variable q einer quadratischen Gleichung.
	 * @param zweiNullStellen
	 * 		boolean Ausdruck der angibt ob die funktion eine oder zwei Nullstellen hat.
	 * @return
	 * 		String, der Nullstellen der quadratischen Gleichung.
	 */
	private static double pQFormel ( double faktorP, double summandQ, boolean zweiNullStellen)// p3 q0,001 ist falsch wenn q "zu" klein ist gibt es NaN
	{
		double nullStelle;
		
		nullStelle  = - ( faktorP / 2 ) + 
						( ( zweiNullStellen ? 1 : (-1) ) * (java.lang.Math.sqrt 
						( ( faktorP / 2 ) * ( faktorP / 2 ) - summandQ ) ) );
		
		return nullStelle;
	}
}
