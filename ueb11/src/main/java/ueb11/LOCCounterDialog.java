package ueb11;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Text based class to interact with the LOCCounter class.
 * <p>
 * 
 * @author Jannik ADAM
 * @author Fromm-Borys
 */
public class LOCCounterDialog {

	private final Scanner IN;
	private final PrintStream OUT;
	private final PrintStream ERROR;
	private boolean firstrun = true;

	private LOCCounter locCounter;

	/**
	 * Method to start the interactive program.<br>
	 * Std.in is the Input.<br>
	 * 
	 * @param args
	 *            Not interpreted program arguments.
	 */
	public static void main(String[] args) {
		new LOCCounterDialog().run();
	}

	/**
	 * Constructor for a LOCCounterDialog<br>
	 * This constructor generates a new, interactive object of the type
	 * {@link LOCCounterDialog} in order to communicate with an object of
	 * the type {@link LOCCounter}.
	 * <p>
	 * Inputs for the communication are read by Std.in.<br>
	 * Outputs are output by Std.out.
	 * <p>
	 * No object is generated in the beginning.<br>
	 */
	public LOCCounterDialog() {
		this(null, System.in, System.out, System.err);
	}

	/**
	 * Constructor for a LOCCounterDialog<br>
	 * This constructor generates a new, interactive object of the type
	 * {@link LOCCounterDialog} in order to communicate with an object of
	 * the type {@link LOCCounter}.
	 * <p>
	 * This constructor also allows an already existing object of the type
	 * {@link LOCCounter} to be imported.<br>
	 * The imported object can be null in case no object is imported.
	 * <p>
	 * There also has to be a {@link InputStream} imported, which is used
	 * to read input.<br>
	 * If the {@link InputStream} is null a
	 * {@link IllegalArgumentException} will be thrown.
	 * <p>
	 * A {@link PrintStream} has to be imported to pass output.<br>
	 * If the {@link PrintStream} is null a
	 * {@link IllegalArgumentException} will be thrown.
	 * <p>
	 * A {@link PrintStream} can be imported to pass errors.<br>
	 * If the {@link PrintStream} is null errors will be passed to
	 * {@link #OUT}.
	 * <p>
	 * 
	 * @param locCounter
	 *            Object this class interacts with.
	 * @param in
	 *            Input
	 * @param out
	 *            Output
	 */
	public LOCCounterDialog(LOCCounter locCounter, InputStream in,
			PrintStream out, PrintStream err) {
		if (in == null) {
			throw new IllegalArgumentException(
					"Eingabestrom darf nicht null sein");
		}
		if (out == null) {
			throw new IllegalArgumentException(
					"Ausgabestrom darf nicht null sein");
		}
		this.IN = new Scanner(in);
		this.locCounter = locCounter;
		this.OUT = out;
		this.ERROR = err;
	}

	/**
	 * This method handles any errors.<br>
	 * In addition the input will be advanced a line in order to prevent
	 * input errors.
	 * <p>
	 * 
	 * @param ex
	 *            The error to be handled.
	 */
	private void processException(Throwable ex) {
		ERROR.println(ex.getMessage());
		if (ex instanceof InputMismatchException) {
			IN.nextLine();
		}
	}

	/**
	 * Method to initiate read cycle<br>
	 * This method blocks the initiating thread until the interaction is
	 * done.<br>
	 * Exceptions and error do NOT close the Method.<br>
	 */
	private void run() {
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
	 * This method first asks whether the LOC of a file should be counted.
	 * <p>
	 * Input is read in order to decide what to do
	 * <p>
	 * The return value of this method decides whether the Dialog will be
	 * closed.<br>
	 * The method will be closed if the return value is true.
	 * <p>
	 * 
	 * @return Whether the dialog should be closed.
	 * @throws FielIsDirectoryException
	 * @throws FileDoesNotExistException
	 * @throws FileNotFoundException
	 *             If the path contained in the string does not lead to a
	 *             valid file.
	 */
	private boolean processNext() {

		locCounter = new LOCCounter();

		OUT.println();
		boolean selected = true;
		if (firstrun) {
			firstrun = false;
			OUT.print(
					"Moechten sie Beginnen die Anzahl an Codezeilen einer .java-Datei auswerten? ");
			selected = getJN();
			if (!selected) {
				return true;
			}
		}
		while (selected) {
			OUT.println("Welche .java-Datei soll ausgewertet werden?");
			OUT.print("> ");
			try {
				locCounter.countLOCPath((getLine()));
			} catch (FileNotFoundException | FileDoesNotExistException
					| FielIsDirectoryException e) {
				processException(e);
			}
			OUT.println("Moechten Sie eine weitere .java-Datei eingeben?");
			selected = getJN();
		}
		OUT.println("Auswertung Lines Of Code (LOC)");
		OUT.println(locCounter.toString());
		OUT.println("Gesamt:");
		OUT.println(locCounter.countAllFilesAndLOC());
		OUT.println("Moechten Sie weiter machen?");
		return !getJN();
	}

	/**
	 * This method reads a non empty text.<br>
	 * This method reads until it come across a non empty text followed by
	 * a line feed/carriage return.
	 * <p>
	 * 
	 * @return A non empty line of text read from the Input.
	 */
	private String getLine() {
		String line = "";
		while (line.trim().isEmpty()) {
			line = IN.nextLine();
		}
		return line;
	}

	/**
	 * This method reads and return either 'j' or 'n'.<br>
	 * This method reads until it comes across an input solely consisting
	 * of either 'j' or 'n'.
	 * <p>
	 * 
	 * @return Either the char 'j' or 'n'.
	 */
	private boolean getJN() {
		char cha = 0;
		String line = "";
		while ((cha != 'j') && (cha != 'n')) {
			OUT.print("<j/n> ");
			line = (IN.nextLine()).trim();
			if ((!(line.isEmpty()) && line.length() == 1)) {
				cha = line.charAt(0);
			}
		}
		return cha == 'j';
	}
}
