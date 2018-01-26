package ueb11;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class LOCCounterDialog  {
	
	private final Scanner IN;
	private final PrintStream OUT;
	
	private LOCCounter locCounter;

	public static void main(String[] args) {
		new LOCCounterDialog().run();
	}
	
	public LOCCounterDialog() {
		this(null, System.in, System.out);
	}
	
	public LOCCounterDialog(LOCCounter locCounter, InputStream in, PrintStream out) {
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
	}
	
	private void processException(Throwable ex) {
		OUT.println(ex.getMessage());
		IN.nextLine();
	}
	
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
	
	private boolean processNext() throws FileNotFoundException {
		
		locCounter = new LOCCounter();
		
		OUT.println();
		String selected = "j";
		OUT.print("Moechten sie die Anzahl an Codezeilen einer .java-Datei auswerten? <j/n> ");
		selected = getLine();
		if(("n").equals(selected)) {
			return true;
		}
		while (!"n".equals(selected)) {
			OUT.println("Welche .java-Datei soll ausgewertet werden?");
			OUT.println(">");
			locCounter.countLOC(getLine());
			OUT.println("Eine weitere .java-Datei übergeben? <j/n>");
			OUT.println(">");
			selected = getLine();
		}
		OUT.println("Auswertung Lines Of Code (LOC)");
		OUT.println(locCounter.toString());
		OUT.println("Gesamt:");
		OUT.printf(locCounter.countAllFilesAndLOC());
		
		return false;
	}
	
	private String getLine() {
		String line = "";
		while (line.trim().isEmpty()) {
			line = IN.nextLine();
		}
		return line;
	}
}
