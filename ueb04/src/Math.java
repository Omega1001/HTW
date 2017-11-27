 /**
 * Klasse die simple Mathematische Methoden anbietet.
 * 
 * @author Jannik Adam
 * @author Fromm-Borys
 * @version 1.0
 */
public class Math {

    private static final long MIN_ISBN_9 = 0;
    private static final long MAX_ISBN_9 = 999999999;
    private static final double PRESICION = 10e11d;

    /**
     * Leerer Konstruktor zum Verhindern des Erstellens eines Unsinnigen.
     */
    private Math ( )
        {
        }
    
    /**
     * Methode, die die Teilersumme einer naturlichen Zahl berechnet.
     * 
     * @param teilerSummenZahl
     *            Die Zahl von der die Teilersumme berechnet werden soll.
     * @return String, der errechneten Teilersumme.
     */
    public static int berechneTeilerSumme(int teilerSummenZahl) {
        int teilerSumme = 0;
        int secondTeiler = Integer.MAX_VALUE;

        // Prufung ob die Nutzereingabe eine naturliche Zahl ist.
        // PrÃ¼ft ob die uebergebene Zahl kleiner als 0 ist.
        if (teilerSummenZahl <= 0) {
            throw new IllegalArgumentException(
                    "Die Teilersummenzahl muss eine natuerliche Zahl sein!");
        }

        for (int teiler = 1; (teiler < secondTeiler); teiler++) {
            // Ist gnazer Teiler
            if (teilerSummenZahl % teiler == 0) {
                // Teilersummen treten in Paaren auf: x/y = z und z/y = x
                secondTeiler = teilerSummenZahl / teiler;
                teilerSumme += teiler
                        + ((secondTeiler == teiler) ? 0 : secondTeiler);
            }
        }

        // Prufung ob ein Uberlauf stattgefunden hat.
        if (teilerSumme < teilerSummenZahl) {
            // Wenn, dann wird eine Runtime Exception geworfen.
            throw new RuntimeException(
                    "Falsches Ergebnis durch ueberlauf!");
        }
        // Ausgabe fuer Teilersummen.
        return teilerSumme;
    }

    /**
     * Methode die aus den ersten neun Ziffern einer ISBN-10 die Prufziffer
     * errechent.
     * <p>
     * 
     * Eine ISBN 10 besteht aus 9 zahlen von [0-9] und der PrÃ¼fziffer
     * [0-9,x], die hier berechnet wird.<br>
     * Uebergeben werden hier lediglich die ersten 9 stellen der ISBN
     * <p>
     * Zu beachten ist, dass 0 an jeder Stelle erlaubt ist, und daher
     * fÃ¼hrende 0en gestattet sind!<br>
     * 
     * @param isbn
     *            long, das die ersten neun Stellen einer ISBN-10
     *            beinhaltet.
     * @return String, der Prufziffer einer ISBN-10.
     */
    public static String berechnePrufZifferISBN_10(long isbn) {

        long prufSumme = 0;
        String prufZiffer;

        // Prufung ob die Eingabe einer ISBN-10 ihne Prufziffer entspricht
        // wobei
        // die isbn fÃ¼hrende 0en haben darf, die bei der convertierung zu
        // long verloren
        // gingen.
        if (isbn < MIN_ISBN_9 || isbn > MAX_ISBN_9) {
            throw new IllegalArgumentException(
                    " Die einzugebende ISBN muss eine ganze 9-stellige Zahl sein!");
        }

        // Fortlaufendes Abschneiden der letzten Ziffer der 9-stelligen
        // ISBN-10,
        // Anwendung von Modulo 10 auf diese anschliesende Faktorisierung
        // mit einem von
        // 9 ablaufendem Index und anschliessende Addition mit der
        // Prufsumme.
        for (int index = 9; isbn > 0; index--) {
            long ziffer = isbn % 10;
            isbn = isbn / 10;
            prufSumme = prufSumme + (ziffer * index);
        }

        prufSumme = prufSumme % 11;
        // Prufung ob fuer die Prufsumme der Sonderfall 10 gilt und
        // entsprechende
        // Ausgabe der Prufziffer.
        if (prufSumme < 10) {
            prufZiffer = String.valueOf(prufSumme);
        } else {
            prufZiffer = "X";
        }
        return prufZiffer;

    }

    /**
     * Methode zum ermitteln der Nullstellen einer quadratischen funktion
     * der Form f(x)=X^2+px+q.
     * 
     * @param faktorP
     *            double, Variable p einer quadratischen Gleichung.
     * @param summandQ
     *            double, Variable q einer quadratischen Gleichung.
     * @return String, der Nullstellen einer quadratischen Gleichung.
     */
    public static String berechneNullStellenEinerQuadratischenGleichung(
            double faktorP, double summandQ) {
        double diskriminante;
        String ergebnis;

        diskriminante = (faktorP / 2.0d) * (faktorP / 2.0d) - summandQ;

        //
        // Runden der Diskriminante auf eine 10 nachkommerstellen um das
        // Problem der
        // Ungenauigkeit der Gleitpunktarithmetic zu vermindern.
        diskriminante = roundToPrecision(diskriminante);

        // Fallunterscheidung auf Grund der ermittelten Diskriminante und
        // entrsprechende
        // Ermittlung von einer, zwei Nullstellen oder der Hinweis auf
        // komplexe
        // Nullstellen.
        try {
            if (diskriminante < 0.0) {
                ergebnis = ("zu komplex!");
            } else if (diskriminante == 0.0) {
                ergebnis = roundToPrecision(
                        pQFormel(faktorP, summandQ, false)) + ".";
            } else {
                ergebnis =
                        roundToPrecision(pQFormel(faktorP, summandQ, true))
                                + " und "
                                + roundToPrecision(
                                        pQFormel(faktorP, summandQ, false))
                                + ".";
            }
        } catch (IllegalArgumentException e) {
            // Noterkennung, falls nichterkannte Komplexe nullstelle durch
            // prezision
            ergebnis = ("zu komplex!");
        }
        return ("Die Nullstellen der quadratischen Gleichung f(x) fuer x*x +"
                + faktorP + "x + (" + summandQ + ") sind " + ergebnis);
    }

    /**
     * Methode zum Runden von double zahlen auf eine Festgelegte
     * Prezision<br>
     * 
     * @param number
     *            die gerundet werden soll
     * @return die gerundete Zahl
     */
    private static double roundToPrecision(double number) {
        return ((long) (number * PRESICION)) / PRESICION;
    }

    /**
     * Methode zum Anwenden der p-q-Formel
     * 
     * @param p
     *            double, Variable p einer quadratischen Gleichung.
     * @param q
     *            double, Variable q einer quadratischen Gleichung.
     * @param zweiNullStellen
     *            boolean Ausdruck der angibt ob die funktion eine oder
     *            zwei Nullstellen hat.
     * @return String, der Nullstellen der quadratischen Gleichung.
     * @throws IllegalArgumentException
     *             falls das Ergebniss NaN ist
     */
    private static double pQFormel(double faktorP, double summandQ,
            boolean zweiNullStellen) {
        double nullStelle;
        nullStelle = -(faktorP / 2)
                + ((zweiNullStellen ? 1 : (-1)) * (java.lang.Math
                        .sqrt((faktorP / 2) * (faktorP / 2) - summandQ)));
        if (nullStelle == Double.NaN) {
            // Werfe Exception, wenn das Ergebnis komplex ist
            throw new IllegalArgumentException("Ergebniss ist Komplex");
        }
        return nullStelle;
    }
    
    /**
     * Methode zum Berechnen von Zahlentripel die die Gleichung a^3+b^3=c^2 erfuellen.
     * 
     * @param schrankeMax
     *      long, vom Nutzer eingegebener Grenzwert den die Variable c nicht erreichen darf.
     * 
     * @param b
     *      double, Wert der Variable b der Gleichung.
     * 
     * @param ZahlenTripel
     *      array, Zahlentrippel die die Gleichung erfuellen.
     * 
     * @param zeilenNummer
     *      long, gibt die zu beschreibende speicherstelle des arrays an 
     * 
     * @param a
     *      long, Wert der Variable a der Gleichung.
     * 
     * @param c
     *      long, Wert der Variable c der Gleichung
     * 
     * @return
     *      long Array, der Tripel die die Gleichung loesen.
     * 
     * @throws IllegalArgumentException
     *      Falls schrankeMAX keine natuerliche Zahl ist.
     */
    public static long [ ] [ ] berechneTripel ( long schrankeMAX )
    {
    
    long ZahlenTripel [ ] [ ] = new long [ 1000 ] [ 3 ];
    int zeilenNummer = 0;

    if ( schrankeMAX < 1 )
    {
        throw new IllegalArgumentException 
            ( "Die Schranke muss eine natuerliche Zahl sein!" );
    }
    if ( schrankeMAX >= java.lang.Math.sqrt(Long.MAX_VALUE))
    {
        throw new IllegalArgumentException 
            ( "Die Schranke muss kleiner " + (long)java.lang.Math.sqrt(Long.MAX_VALUE) + " sein um einen Ueberlauf zu verhindern!" );
    }
        for ( long c = 1; c < schrankeMAX; c++ )
        {
            for ( long a = 1; ( a * a * a ) <= ( ( c * c ) / 2 ) ; a++ )
            {
                    if ( berechneB ( a, c ) % 1 == 0 )
                    {
                    	ZahlenTripel [ zeilenNummer ] [0] =  a ;
                    	ZahlenTripel [ zeilenNummer ] [1] =  ( long ) berechneB ( a, c ) ;
                    	ZahlenTripel [ zeilenNummer ] [2] =  c ;
                        zeilenNummer++;
                    }
            }
    }
        	long AuszugebendesZahlenTripel [ ] [ ] = new long [ ( zeilenNummer-- ) ] [ 3 ];
        	
    		while ( zeilenNummer > -1 ) 
            {
                AuszugebendesZahlenTripel [zeilenNummer] [0] = ZahlenTripel [ zeilenNummer ] [0];
                AuszugebendesZahlenTripel [zeilenNummer] [1] = ZahlenTripel [ zeilenNummer ] [1];
                AuszugebendesZahlenTripel [zeilenNummer] [2] = ZahlenTripel [ zeilenNummer ] [2];
                zeilenNummer--;
            }
    return AuszugebendesZahlenTripel;
    }
    
    /**
     * Methode zum aufloesen der Gleichung a^3+b^3=c^2 nach b.
     * 
     * @param a
     *      long, von der Hauptmethode Uebergebener Wert der Variable a.
     * 
     * @param c
     *      long, von der Hauptmethode Uebergebener Wert der Variable c.
     * 
     * @return
     *      double, Wert der der Variable b der Gleichung. 
     */
    private static double berechneB ( long a, long c)
    {
        return java.lang.Math.cbrt ( c * c - a * a * a );
    }
    
    /**
     * Methode zum berechnen der Summe von i=1 bis n : ((x-1)^i)/(i*(x^i))
     * 
     * @param n
     *      long, Wert der variable n
     * 
     * @param x
     *      long, Wert der Variable x
     */
    public static double berechneSumme ( long n, double x )
    {
        double Summe = 0;
        double xPotenz = x;// die beiden x variablen verf�lschen  alles wenn ich sie in der for Schleife initiiere.
    	double xMinusEinsPotenz = x - 1;
        
        if ( n < 1)
        {
            throw new IllegalArgumentException ( "n muss eine natuerliche Zahl sein!" );
        }
        for ( long i = 1; i <= n; i++)
        {
            Summe += xMinusEinsPotenz / ( i * xPotenz );// NaN n>64 n=55555. Exception für NaN einbauen oder code überarbeiten?
            xPotenz = xPotenz * x;
        	xMinusEinsPotenz = xMinusEinsPotenz * ( x - 1 );
        }
        
        return Summe;
    }
}


