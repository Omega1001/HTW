package ueb09;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Klasse zum textuellen Arbeiten mit einem Artikel-Objekt
 * <p>
 * Diese Klasse bietet eine Moeglichkeit, mit einem Artikel-Objekt per
 * Texteingabe zu kommunizieren.<br>
 * Der Input fuer diese Kommunikation kann von der Standardeingabe, oder
 * aus einem beliebigen Strom kommen<br>
 * 
 * @author Jannik Adam
 * @author Fromm-Borys
 * @version 1.0
 *
 */
public class ArtikelDialog {

	/**
	 * Identifizierte Option zum Verlassen des Dialogs
	 */
	public static final int QUIT = 0;
	/**
	 * Identifizierte Option zum Erstellen eines neuen Objekts
	 */
	public static final int ANLEGEN = 1;
	/**
	 * Identifizierte Option zum Erstellen eines neuen Objekts ohne
	 * Artikelbestand
	 */
	public static final int ANLEGEN2 = 2;
	/**
	 * Identifizierte Option um einen Zugang zu buchen
	 */
	public static final int ZUGANG = 3;
	/**
	 * Identifizierte Option um einen Abgang zu buchen
	 */
	public static final int ABGANG = 4;
	/**
	 * Identifizierte Option um eine neue Bezeichnung zu setzen
	 */
	public static final int SET_ART_BEZEICHNUNG = 5;

	/**
	 * Input-Scanner, der fuer die Analyse des Inputs benutzt werden soll
	 */
	private final Scanner IN;
	/**
	 * Output, auf dem Ergebnisse ausgegeben werden sollen
	 */
	private final PrintStream ERROR;
	/**
	 * Output, auf dem Ergebnisse ausgegeben werden sollen
	 */
	private final PrintStream OUT;

	/**
	 * Artikel, mit dem in diesem Dialog kommuniziert wird
	 */
	private Artikel art;
	private boolean[] mayExecute = null;

	/**
	 * Methode um das Interaktive Programm zu starten<br>
	 * Als Input wird die Standardeingabe benutzt<br>
	 * 
	 * @param args
	 *            Programmargumente, die nicht ausgewertet werden
	 */
	public static void main(String[] args) {
		new ArtikelDialog().run();
	}

	/**
	 * Konstruktor fuer einen Artikeldialog<br>
	 * Dieser Konstruktor erschafft ein neues, interaktive Dialogobjekt zur
	 * Kommunikation mit Artikel-Objekten
	 * <p>
	 * Eingaben fuer die Kommunikation werden von der Sandardeingabe
	 * gelesen<br>
	 * Ausgaben werden auf der Standardausgabe ausgegeben
	 * <p>
	 * Es wird zu Beginn kein Objekt angelegt<br>
	 */
	public ArtikelDialog() {
		this(null, System.in, System.out, System.err);
	}

	/**
	 * Konstruktor fuer einen Artikel Dialog<br>
	 * Dieser Konstruktor erschafft ein neues, interaktive Dialogobjekt zur
	 * Kommunikation mit Artike-Objekten
	 * <p>
	 * 
	 * Dieser Konstruktor ermoeglicht es ausserdem, ein zuvor angelegtes
	 * Artikelobjekt in den neuen Dialog zu importieren<br>
	 * Das uebergebene Objekt darf auch null sein, falls kein Objekt
	 * importiert werden soll
	 * <p>
	 * 
	 * Es muss ausserdem ein {@link InputStream} uebergeben werden, von dem
	 * Eingaben gelesen werden<br>
	 * Ist der uebergebene {@link InputStream} null, so wird eine
	 * {@link IllegalArgumentException} geworfen
	 * <p>
	 * 
	 * Es muss ausserdem ein {@link PrintStream} uebergeben werden, auf dem
	 * die Ausgabe erfolgt<br>
	 * Ist der uebergebene {@link PrintStream} null, so wird eine
	 * {@link IllegalArgumentException} geworfen
	 * <p>
	 * 
	 * Es kann ausserdem ein {@link PrintStream} uebergeben werden, auf dem
	 * die Fehler ausgegeben werden<br>
	 * Ist der uebergebene {@link PrintStream} null, so werden Fehler auf
	 * dem {@link #OUT} stream ausgegeben.
	 * <p>
	 * 
	 * Es kann außerdem eine Liste von ints variabler größe übergeben
	 * werden<br>
	 * Diese sollen die Nummern aller Operationen enthalten, die vom
	 * Benutzer ausgefuert werden duerfen<br>
	 * Ist die Liste leer, werden alle operationen erlaubt<br>
	 * Der EXIT Befehl ist immer erlaubt
	 * 
	 * @param art
	 *            Artikel, der durch den Dialog bearbeitbar werden soll
	 * @param in
	 *            Eingabequelle, von der gelesen wird
	 * @param out
	 *            Ausgabequelle für Ergebnisse
	 * @param error
	 *            Ausgabequelle für Fehler
	 * @param mayExecute
	 *            auflistung aller befehle, die ausgefürt werden dürfen.
	 * @throws IllegalArgumentException
	 *             wenn der uebergebene Ein- oder Ausgabe-Stream null ist
	 */
	public ArtikelDialog(Artikel art, InputStream in, PrintStream out,
			PrintStream error, int... mayExecute) {
		if (in == null) {
			throw new IllegalArgumentException(
					"Eingabestrom darf nicht null sein");
		}
		if (out == null) {
			throw new IllegalArgumentException(
					"Ausgabestrom darf nicht null sein");
		}
		if (error == null) {
			error = out;
		}
		this.IN = new Scanner(in);
		this.art = art;
		this.OUT = out;
		this.ERROR = error;
		this.mayExecute = createMayExecute(mayExecute);
	}

	/**
	 * Methode, die dieses Object in den Leesezyklus versetzt<br>
	 * Diese Methode blockiert den aufrufenden Thread, bis die Interaktion
	 * beendet wird.<br>
	 * Exceptions, sowie Errors führen NICHT zum Verlassen der Methode<br>
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
		ERROR.println(ex.getMessage());
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
	 * Abgesehen von der "Anlegen"- bzw. "Ende"-Opperation, muss ein Objekt
	 * vorhanden sein, bevor eine andere Methode aufgerufen werden
	 * darf.<br>
	 * Wird versucht eine Methode mit Ausnahme der genannten aufzurufen, so
	 * wird eine {@link UnsupportedOperationException} geworfen
	 * <p>
	 * Der Rückgabewert dieser Funktion sagt aus, ob die "Ende"-Funktion
	 * gewählt wurde<br>
	 * Wenn diese Methode true zurückliefert, soll der Dialog mit dem
	 * Benutzer beendet werden
	 * 
	 * @return ob der Dialog beendet werden soll
	 * @throws UnsupportedOperationException
	 *             wenn die gewählte Opperation nicht zugeordnet werden
	 *             kann, oder das Objekt zuerst initialisiert werden muss.
	 */
	private boolean processNext() {
		// Print empty Line
		OUT.println();
		int selected = -1;
		printOptions(art);
		OUT.print("> ");
		selected = getInt();
		if (selected == QUIT) {
			return true;
		} else if (selected == ANLEGEN || selected == ANLEGEN2) {
			checkExecutionPermission(selected);
			OUT.print(
					"Bitte geben Sie eine 4-stellige Artikelnummer ein > ");
			int artNr = getInt();
			OUT.print("Bitte geben Sie eine Artikelbezeichnung ein > ");
			String artBez = getLine();
			if (selected == ANLEGEN) {
				OUT.print("Bitte geben sie eine Startmenge ein > ");
				int artMenge = getInt();
				art = new Artikel(artNr, artBez, artMenge);
			} else {
				art = new Artikel(artNr, artBez);
			}

			OUT.println("Artikel wurde angelegt");
		} else if (art != null) {
			if (selected == ZUGANG) {
				checkExecutionPermission(selected);
				OUT.print("Geben Sie eine Menge zum Zubuchen an:");
				art.bucheZugang(getInt());
				OUT.println("Artikelmenge betraegt jetzt " + art
						.getArtikelBestand());
			} else {
				if (selected == ABGANG) {
					checkExecutionPermission(selected);
					OUT.print("Geben Sie eine Menge zum Abbuchen an:");
					art.bucheAbgang(getInt());
					OUT.println("Artikelmenge betraegt jetzt " + art
							.getArtikelBestand());
				} else if (selected == SET_ART_BEZEICHNUNG) {
					checkExecutionPermission(selected);
					OUT.print(
							"Geben Sie eine neue Artikelbezeichnung ein:");
					art.setArtikelBezeichnung(getLine());
					OUT.println("Der Artikel heißt jetzt " + art
							.getArtikelBezeichnung());
				} else {
					throw new UnsupportedOperationException(
							"Dieser Befehl ist nicht bekannt");
				}
			}
		} else {
			throw new UnsupportedOperationException(
					"Es wurde noch kein Artikel angelegt. "
							+ "Bitte legen sie einen Artikel an");
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
	 * Diese Methode dient dazu, eine Liste aller Funktionen auf der
	 * Ausgabe auszugeben, die gewaehlt werden koennen<br>
	 * Ausserdem wird jeder Aktion eine Nummer zugewiesen, mit der diese
	 * Methode bei der Methodenauswahl gewaehlt werden kann<br>
	 * Zuvor wird der Status des Objektes ausgegeben, oder eine Meldung,
	 * falls noch kein Objekt angelegt wurde<br>
	 * 
	 * @param art
	 *            Artikel dessen Status ausgegeben werden soll
	 */
	private void printOptions(Artikel art) {
		OUT.println(art != null ? art : "Artikel ist noch nicht angelegt");
		OUT.println("Bitte waehlen Sie eine Opperation aus:");
		StringBuilder sb = new StringBuilder();
		appandOperation(sb, ANLEGEN, "Anlegen");
		appandOperation(sb, ANLEGEN2, "Anlegen2");
		appandOperation(sb, ZUGANG, "Zugang Buchen");
		appandOperation(sb, ABGANG, "Abgang Buchen");
		appandOperation(sb, SET_ART_BEZEICHNUNG, "Artikelbezeichnung Aendern");
		appandOperation(sb, QUIT, "Exit");
		OUT.print(sb.toString());
	}
	
	private StringBuilder appandOperation (StringBuilder sb, int opperation, String name) {
		if(mayExecute(opperation)) {
			sb.append(name).append(" : ").append(opperation).append("\r\n");
		}
		return sb;
	}

	private boolean mayExecute(int operation) {
		if(operation == QUIT) {
			return true; //Quit geht immer!
		}
		if (mayExecute == null || mayExecute.length == 0) {
			return true;
		} else if (operation > mayExecute.length) {
			return false;
		} else {
			return mayExecute[operation];
		}
	}

	private void checkExecutionPermission(int operation) {
		if (!mayExecute(operation)) {
			throw new UnsupportedOperationException(
					"This action is not allowed in this Context");
		}
	}

	private boolean[] createMayExecute(int[] mayExecute) {
		if(mayExecute.length == 0) {
			return new boolean[0];
		}
		boolean[] result = new boolean[6];
		for (int i : mayExecute) {
			if (result.length <= i) {
				boolean[] temp = result;
				result = new boolean[i + 1];
				Arrays.fill(result, false);
				System.arraycopy(temp, 0, result, 0, result.length);
			}
			result[i] = true;
		}
		return result;
	}

}
