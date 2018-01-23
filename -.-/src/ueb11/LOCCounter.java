package ueb11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to count all "lines of code" in a given .java file.
 * 
 * @author Jannik ADAM
 * @author Fromm-Borys
 */
public class LOCCounter {
	
	private static final Pattern JAV_DOC = Pattern.compile("^ *?//.*$");
	
	private List<FileLOC> fileLOCs = new ArrayList<>();
	
	
	public void main(String[] args) {
		run(System.in, System.out, System.err);
	}
	
	public void run(InputStream in, PrintStream out) {
		run(in, out, out);
	}
	
	public void run(InputStream in, PrintStream out,
			PrintStream err) {
		if (in == null || out == null || err == null) {
			throw new IllegalArgumentException(
					"Passed Streams must not be undefined");
		}
		
		File file = null;
		char run = 'j';

		while (run == 'j') {
			out.print("Bitt eine Datei angeben > \r\n");
			file = FileReader(getString());
			List<FileLOC> fileLOCs = countLOC(file);
			out.print("Eine weitere Datei? <j/n>\r\n");
			run = getChar();
			if (run != 'j' || run != 'n') {
				throw new IllegalArgumentException ("Eingabe muss j oder n sein!");
			}
		}
		out.print("Auswertung Lines Of Code (LOC)");
		int number = 0;
		int sum = 0;
		for (FileLOC fileLOC : fileLOCs) {
			out.print(fileLOC.toString());
			sum += fileLOC.getLOC();
			number++;
		}
		out.print("Gesamt:\r\n");
		String gesamt = String.format("%-20s %s\r\n", (number + "Datei/en"), (sum + "LOC")); 
		out.print(gesamt);
	}
	
	public List<FileLOC> countLOC(File file) throws FileNotFoundException {
		
		int LOC = 0;
		Scanner scanner = new Scanner(file);
		String line = "";
		
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			Matcher javDoc1Mat = JAV_DOC.matcher(line);
			if ( !("".equals(line.trim()) && !javDoc1Mat.find())) {
				LOC++;
			}
		fileLOCs.add(new FileLOC(file, LOC));
		}
		
		return fileLOCs;
	}
}
