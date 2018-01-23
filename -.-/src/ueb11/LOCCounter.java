package ueb11;

import java.io.BufferedReader;
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
	
	private static final Pattern JAV_DOC_1 = Pattern.compile("^ *?//.*$");
	
	public static void main(String[] args) {
		run(System.in, System.out, System.err);
	}
	
	public static void run(InputStream in, PrintStream out) {
		run(in, out, out);
	}
	
	public static void run(InputStream in, PrintStream out,
			PrintStream err) {
		if (in == null || out == null || err == null) {
			throw new IllegalArgumentException(
					"Passed Streams must not be undefined");
		}
		List<FileLOC> fileLOCs = countLOC(in);
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
	
	public static List<FileLOC> countLOC(InputStream in) {
		
		if (in == null) {
			throw new IllegalArgumentException(
					"Passed Streams must not be undefined");
		}
		
		List<FileLOC> fileLOCs = new ArrayList<>();
		String fileName = "";
		int LOC = 0;
		Scanner scanner = new Scanner(in);
		String line = "";
		
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			Matcher javDoc1Mat = JAV_DOC_1.matcher(line);
			Matcher javDoc2Mat = JAV_DOC_2.matcher(line);
			Matcher javDoc3Mat = JAV_DOC_3.matcher(line);
			if ( !("".equals(line.trim()) && !javDoc1Mat.find() && !javDoc2Mat.find() && !javDoc3Mat.find())) {
				LOC++;
			}
		fileLOCs.add(new FileLOC(fileName, LOC));
		}
		
		return fileLOCs;
	}
}
