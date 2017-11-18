
/**
 * Beschreiben Sie hier die Klasse Test.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Test
{
    public static void main (String [] args){
        Artikel a = new Artikel(1234,"Test");
        System.out.println(a.getArtikelnummer() == 1234);
    }
}
