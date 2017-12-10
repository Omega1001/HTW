
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Textbasierte Klasse zum verwenden von Lager-Objekten<p>
 * 
 * @author Jannik Adam
 * @author Fromm-Borys
 * @version 1.0
 */
public class LagerDialog {
	private static final int BEENDEN = 0;
	private static final int ERZEUGEN = 1;
	private static final int ERZEUGEN_MAX = 2;
	private static final int EINLAGERN = 3;
	private static final int LOESCHEN = 4;
	private static final int ZUGANG = 5;
	private static final int ABGANG = 6;
	private static final int PREISAENDERUNG = 7;
	
	private static final int ARTIKEL_ANLEGEN_GANZ = 1;
	private static final int ARTIKEL_ANLEGEN_OHNE_BESTAND = 2;
	private static final int ARTIKEL_ANLEGEN_MIT_STANDART_PREIS = 3;
	private static final int ARTIKEL_ANLEGEN_OHNE_BESTAND_MIT_STANDART_PREIS = 4;
	
	private final Scanner IN;
	private final PrintStream OUT;

	private Lager lager;
	private Artikel artikel;
	
	/**
	 * Methode um das Interaktive Programm zu starten<br>
	 * Als Input wird die Standardeingabe benutzt<br>
	 * 
	 * @param args
	 *            Programmargumente, die nicht ausgewertet werden
	 */
	public static void main(String[] args) {
		new LagerDialog().run();
	}

	/**
	 * Konstruktor fuer einen Lagerdialog<br>
	 * Dieser Konstruktor erschafft ein neues, interaktive Dialogobjekt zur
	 * Kommunikation mit Lager-Objekten
	 * <p>
	 * Eingaben fuer die Kommunikation werden von der Sandardeingabe
	 * gelesen<br>
	 * Ausgaben werden auf der Standardausgabe ausgegeben
	 * <p>
	 * Es wird zu Beginn kein Objekt angelegt<br>
	 */
	public LagerDialog() {
		this(null, System.in, System.out);
	}
	/**
	 * Konstruktor fuer einen Lager Dialog<br>
	 * Dieser Konstruktor erschafft ein neues, interaktive Dialogobjekt zur
	 * Kommunikation mit dem Lager-Objekt
	 * <p>
	 * 
	 * Dieser Konstruktor ermoeglicht es ausserdem, ein zuvor angelegtes
	 * Lagerobjekt in den neuen Dialog zu importieren<br>
	 * Das uebergebene Objekt darf auch null sein, falls kein Objekt
	 * importiert werden soll
	 * <p>
	 * 
	 * Es muss ausserdem ein {@link InputStream} uebergeben werden, von dem
	 * Eingaben gelesen werden<br>
	 * Ist der uebergebene {@link InputStream} null, so wird eine
	 * {@link IllegalArgumentException} geworfen<br>
	 * 
	 * Es muss ausserdem ein {@link PrintStream} uebergeben werden, auf dem
	 * die Ausgabe erfolgt<br>
	 * Ist der uebergebene {@link PrintStream} null, so wird eine
	 * {@link IllegalArgumentException} geworfen
	 * 
	 * @param lager
	 *            Lager, das durch den Dialog bearbeitbar werden soll
	 * @param in
	 *            Eingabequelle, von der gelesen wird
	 * @param out
	 *            Ausgabequelle fuer Ergebnisse
	 * @throws IllegalArgumentException
	 *             wenn der uebergebene Ein- oder Ausgabe-Stream null ist
	 */
	public LagerDialog(Lager lager, InputStream in, PrintStream out) {
		if (in == null) {
			throw new IllegalArgumentException(
					"Eingabestrom darf nicht null sein");
		}
		if (out == null) {
			throw new IllegalArgumentException(
					"Ausgabestrom darf nicht null sein");
		}
		this.IN = new Scanner(in);
		this.lager = lager;
		this.OUT = out;
	}
	/**
	 * Methode, die dieses Object in den Leesezyklus versetzt<br>
	 * Diese Methode blockiert den aufrufenden Thread, bis die Interaktion
	 * beendet wird.<br>
	 * Exceptions, sowie Errors fuehren NICHT zum Verlassen der Methode<br>
	 */
	public void run() {
		boolean finished = false;
		OUT.println("Beginn der Interaktion");
		while (!finished) {
			try {
				finished = processNext();
			} catch (Throwable e) {
				processException(e);
			}
		}
		OUT.println("Interaktion beendet");
	}

	/**
	 * Diese Methode uebernimmt die Behandlung jeglicher Fehler, die
	 * waehrend der Ausfuehrung auftreten koennen<br>
	 * Zusaetzlich zur Fehlerbehandlung wird der InputStream IN um eine
	 * Zeile nach vorne gesetzt, um auf Eingabefehler zu reagieren<br>
	 * 
	 * @param ex
	 *            Zu behandelnder Fehler
	 */
	private void processException(Throwable ex) {
		OUT.println(ex.getMessage());
		IN.nextLine();
	}
	/**
	 * Diese Methode gibt zunaechst den aktuellen Status des gemanagten
	 * Objekts aus. <br>
	 * Danach wird eine Liste mit allen verfuegbaren Aktionen angezeigt
	 * <p>
	 * Es wird eine Zahl eingelesen, die eindeutig einer Aktion zugeordnet
	 * wird<br>
	 * Kann die Zahl keiner Aktion zugeordnet werden, so wird eine
	 * {@link UnsupportedOperationException} geworfen
	 * <p>
	 * Abgesehen von der "Erzeugen"- bzw. "Ende"-Opperation, muss ein Objekt
	 * vorhanden sein, bevor eine andere Methode aufgerufen werden
	 * darf.<br>
	 * Wird versucht eine Methode mit Ausnahme der genannten aufzurufen, so
	 * wird eine {@link UnsupportedOperationException} geworfen
	 * <p>
	 * Der Rueckgabewert dieser Funktion sagt aus, ob die "Ende"-Funktion
	 * gewaehlt wurde<br>
	 * Wenn diese Methode true zurueckliefert, soll der Dialog mit dem
	 * Benutzer beendet werden
	 * 
	 * @return ob der Dialog beendet werden soll
	 * @throws UnsupportedOperationException
	 *             wenn die gewaehlte Opperation nicht zugeordnet werden
	 *             kann, oder das Objekt zuerst initialisiert werden muss.
	 */
	private boolean processNext() {
		// Print empty Line
		OUT.println();
		int selected = -1;
		printOptions(lager);
		OUT.print("> ");
		selected = getInt();
		if (selected == BEENDEN) {
			return true;
		} else if (selected == ERZEUGEN) {
			OUT.print("Bitte Lagergroe�e bestimmen. > ");
				lager = new Lager(getInt());
				OUT.println("Lager wurde erzeugt.");
		} else if (selected == ERZEUGEN_MAX){
				lager = new Lager();
				OUT.println("Lager wurde erzeugt.");
		}else if (lager != null) {
				if (selected == EINLAGERN) {
					OUT.print("Wie m�chten sie den Artikel anlegen?\r\n");
					OUT.print(ARTIKEL_ANLEGEN_GANZ + ": Ganz\r\n" +
							+ ARTIKEL_ANLEGEN_OHNE_BESTAND + ": Ohne Bestand\r\n"
							+ ARTIKEL_ANLEGEN_MIT_STANDART_PREIS+ ": Mit Standardpreis\r\n"
							+ ARTIKEL_ANLEGEN_OHNE_BESTAND_MIT_STANDART_PREIS
							+ ": Ohne Bestand und mit Standardpreis\r\n");
					int wahl = getInt();//Da ganzer Artikel es mittschleppen muss.
						if(wahl < ARTIKEL_ANLEGEN_GANZ 
							|| wahl > ARTIKEL_ANLEGEN_OHNE_BESTAND_MIT_STANDART_PREIS) {
							throw new IllegalArgumentException 
								("Eingabe muss den Optionen entsprechen!");
						}
						OUT.print("Bitte geben Sie eine 4-stellige Artikelnummer ein > ");
						int artNr = getInt();
						OUT.print("Bitte geben Sie eine Artikelbezeichnung ein > ");
						String artBez = getLine();
						if (wahl == ARTIKEL_ANLEGEN_OHNE_BESTAND_MIT_STANDART_PREIS) {
							artikel= new Artikel(artNr, artBez);
						}
						if(wahl == ARTIKEL_ANLEGEN_GANZ
							|| wahl == ARTIKEL_ANLEGEN_OHNE_BESTAND
							|| wahl == ARTIKEL_ANLEGEN_MIT_STANDART_PREIS) {
							int artMenge = 0;
								if(wahl == ARTIKEL_ANLEGEN_GANZ
									|| wahl == ARTIKEL_ANLEGEN_MIT_STANDART_PREIS) {
									OUT.print("Bitte geben sie eine Startmenge ein > ");
									artMenge = getInt();
									if(wahl == ARTIKEL_ANLEGEN_MIT_STANDART_PREIS) {
										artikel = new Artikel (artNr, artBez, artMenge);
									}
								}
								if(wahl == ARTIKEL_ANLEGEN_GANZ
										|| wahl == ARTIKEL_ANLEGEN_OHNE_BESTAND) {
								OUT.print("Bitte geben sie einen Startpreis ein > ");
								double preis = getDouble();
									if(wahl == ARTIKEL_ANLEGEN_GANZ) {
										artikel = new Artikel (artNr, artBez, artMenge, preis);
									}
									if(wahl == ARTIKEL_ANLEGEN_OHNE_BESTAND) {
										artikel = new Artikel (artNr, artBez, preis);
									}
								}
						}
					lager.lagereArtikel(artikel);
				}else if(lager.getArtikelAnzahl() > 0) {
				 if (selected == LOESCHEN) {
					OUT.print("Geben Sie die Artikelnummer des zu loeschenden Artikels ein:");
					lager.loescheArtikel(getInt());
				} else if (selected == ZUGANG) {
					OUT.print("Geben Sie die Artikelnummer des zugegangen Artikels ein:");
					int artNr = getInt();
					OUT.print("Geben Sie die zugegangene Menge ein:");
					lager.bucheZugang(artNr, getInt());
				} else if (selected == ABGANG) {
					OUT.print("Geben Sie die Artikelnummer des abgegangen Artikels ein:");
					int artNr = getInt();
					OUT.print("Geben Sie die abgegangene Menge ein:");
				     lager.bucheAbgang(artNr,getInt());
			    } else if (selected == PREISAENDERUNG) {
					OUT.print("Geben sie den Prozentwert der Preisaenderung ein:");
					lager.passePreiseAn(getDouble());
				} else {
				throw new UnsupportedOperationException(
						"Dieser Befehl ist nicht bekannt");
			}
				}else {
					throw new UnsupportedOperationException(
							"Es wurde noch kein Artikel eingelagert. "
									+ "Bitte lagern sie zuerst einen Artikel ein.");
				}
		} else {
			throw new UnsupportedOperationException(
					"Es wurde noch kein Lager erzeugt. "
							+ "Bitte erzeugen sie ein Lager.");
		}
		return false;
	}
	/**
	 * Diese Methode dient dazu, einen nicht leeren Text aus ev. mehreren
	 * Worten einzulesen<br>
	 * Diese Methode liest solange Text von der Eingabe ein, bis ein nicht
	 * leerer Text gefolgt von einem Linefeed/CarrageReturn (/r/n)
	 * eingelesen wurde<br>
	 * 
	 * @return eine nicht leere Textzeile, die von der Eingabe eingelesen
	 *         wurde
	 */
	private String getLine() {
		String line = "";
		while (line.trim().isEmpty()) {
			line = IN.nextLine();
		}
		return line;
	}
	/**
	 * Diese Methode dient dazu, einen Integer von der Eingabe
	 * einzulesen<br>
	 * Wird auf dem Input kein Integer gesendet, sondern undefinierte
	 * andere Zeichen, so wird die Anfrage nach einer Zahl wiederholt<br>
	 * 
 	* @return eine ganze Zahl
 	*/
	private int getInt() {
		while (true) {
			try {
				return IN.nextInt();
			} catch (InputMismatchException e) {
				IN.nextLine(); // Loesche Input
				OUT.print("Bitte geben Sie eine Zahl ein > ");
			}
		}
	}
	/**
	 * Diese Methode dient dazu, einen Double von der Eingabe
	 * einzulesen<br>
	 * Wird auf dem Input kein Double gesendet, sondern undefinierte
	 * andere Zeichen, so wird die Anfrage nach einer Zahl wiederholt<br>
	 * 
 	* @return eine reelle Zahl
 	*/
	private double getDouble() {
		while (true) {
			try {
				return IN.nextDouble();
			} catch (InputMismatchException e) {
				IN.nextLine(); // Loesche Input
				OUT.print("Bitte geben Sie eine Zahl ein > ");
			}
		}
	}
	/**
	 * Diese Methode dient dazu, eine Liste aller Funktionen auf der
	 * Ausgabe auszugeben, die gewaehlt werden koennen<br>
	 * Ausserdem wird jeder Aktion eine Nummer zugewiesen, mit der diese
	 * Methode bei der Methodenauswahl gewaehlt werden kann<br>
	 * Zuvor wird der Status des Objektes ausgegeben, oder eine Meldung,
	 * falls noch kein Objekt angelegt wurde<br>
	 * 
	 * @param lager
	 *            lager dessen Status ausgegeben werden soll
	 */
	private void printOptions(Lager lager) {
		OUT.println(lager != null ? lager : "Artikel ist noch nicht angelegt");
		OUT.println("Bitte waehlen Sie eine Opperation aus:");
		OUT.println(ERZEUGEN + " :Lager erzeuegn\r\n" 
				+ ERZEUGEN_MAX + ": Maximales Lager erzeugen:\r\n"
				+ EINLAGERN + ": Artikel einlagern\r\n"
				+ LOESCHEN+ ": Artikel loeschen \r\n"
				+ ZUGANG + ": Zugang\r\n" + ABGANG + ": Abgang\r\n"
				+ PREISAENDERUNG + ": Preise anpassen\r\n"
				+ BEENDEN + ": Beenden\r\n");
	}
	
}
