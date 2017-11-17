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
	 * 		String der die errechnete Teilersumme ausgibt. 
	 */
	public static String berechneTeilerSumme ( int teilerSummenZahl )
	{			
		int teilerSumme = 0;
		int teiler 		= 0;
		int moduloErgebnis ;
		
		if ( teilerSummenZahl < 1)
		{
			throw new IllegalArgumentException ( "Die Teilersummenzahl muss eine natürliche Zahl sein!" );
		}
		else 
		{	
			while ( teiler != teilerSummenZahl ) 
			{
				teiler++;
				moduloErgebnis = teilerSummenZahl % teiler;
				
				if ( moduloErgebnis == 0 ) 
				{
					teilerSumme = teilerSumme + teiler;
				}
			}
			return ( "Die Teilersumme ist " + teilerSumme + "." );
		}
		
	}
	/**
	 * Methode die aus den ersten neun Ziffern einer ISBN-10 die Prufziffer errechent.
	 * 
	 * @param iSBN_10_9
	 * 		long, das die ersten neun Stellen einer ISDN-10 einliest.
	 * @return
	 * 		String der die Prufziffer einer ISBN-10 ausgibt.
	 */
	public static String berechnePrufZifferISBN_10 ( long iSBN_10_9 )
	{
		final long MIN_ISBN_9 = 		1;
		final long MAX_ISBN_9 = 999999999;
		long gekurzteISBN_10_9			 ;
		long ziffer						 ;
		long zwischenPrufZiffer		  = 0;
		long index					  = 9;
		String prufZiffer				 ;
		String losung					 ;
		
		if (iSBN_10_9 < MIN_ISBN_9 | iSBN_10_9 > MAX_ISBN_9) 
		{
			throw new IllegalArgumentException ( " Die einzugebende ISBN muss eine ganze 9-stellige Zahl sein!" );
		}
		else
		{	
			gekurzteISBN_10_9 = iSBN_10_9;
				
			while ( index > 0 )
			{
			ziffer = gekurzteISBN_10_9 % 10;
			gekurzteISBN_10_9 = gekurzteISBN_10_9 / 10;
			zwischenPrufZiffer = zwischenPrufZiffer + ziffer * index--;
			}
		
			zwischenPrufZiffer = zwischenPrufZiffer % 11;
			
			if (zwischenPrufZiffer < 10 )
			{
				prufZiffer = ( "" + zwischenPrufZiffer );// warum brauch ich hier die Apostrophe? funktioniert an anderer Stelle ohne String.
			}
			else
			{
				prufZiffer = "X";
			}
		
			losung = ( "ISBN-10 Prüfziffer lautet: " + prufZiffer); //+ " ;Vollständige ISBN-10 lautet: " + iSBN_10_9 + "-" + prufZiffer ); !Funktioniert nicht da führende Nullen geschluckt werden / 9 if Anweisungen sind "unschön" / keine Ahnung wie man Länge eines longs checkt!
		}
		return losung;
	}
	/**
	 * Methode zum ermitteln der Nullstellen einer quadratischen funktion der Form f(x)=X^2+px+q.
	 * 
	 * @param variablePe
	 * 		double, Variable p einer quadratischen Gleichung.
	 * @param variableQu
	 * 		double, Variable q einer quadratischen Gleichung.
	 * @return
	 * 		String der die Nullstellen einer quadratischen Gleichung ausgibt.
	 */
	public static String berechneNullStellenEinerQuadratischenGleichung ( double variablePe, double variableQu )
	{
		double diskriminante;
		String losung		;
		
		diskriminante = ( variablePe / 2 ) * ( variablePe / 2 ) - variableQu;// trim fehlt noch.

		if ( diskriminante > 0.0 )
		{
			losung = pQFormel ( variablePe, variableQu, true);
		}
		else if ( diskriminante == 0.0 )
		{
			losung = pQFormel ( variablePe, variableQu, false);
		}
		else
		{
			losung = ( "Zu komplex!" );
		}
		return losung;
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
	 * 		String der die Nullstellen der quadratischen Gleichung ausgibt.
	 */
	private static String pQFormel ( double p, double q, boolean zweiNullStellen)
	{
		double x 		  ;
		double x2		  ;
		String nullStellen;
		
		x  = - ( p / 2 ) + java.lang.Math.sqrt ( ( p / 2 ) * ( p / 2 ) - q );// Nochmal kurzes if zeigen lassen!
		nullStellen = "x = " + x;
		
		if ( zweiNullStellen )
		{
			x2  = - ( p / 2 ) - java.lang.Math.sqrt ( ( p / 2 ) * ( p / 2 ) - q );
			nullStellen = "x1 = " + x + "x2 = " + x2 ;
		}
		
		return nullStellen;
	}
}
