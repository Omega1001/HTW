package ueb11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	private List<FileLOC> fileLOCs;

	public LOCCounter(){
		this. fileLOCs = new ArrayList<>();
	}
	
	public void countLOC(String fileName) throws FileNotFoundException {
		
		int LOC = 0;
		Scanner scanner = new Scanner(new FileReader(fileName));
		String line = "";
		
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			Matcher javDoc1Mat = JAV_DOC.matcher(line);
			if ( !("".equals(line.trim()) && !javDoc1Mat.find())) {
				LOC++;
			}
		
		}
		fileLOCs.add(new FileLOC(new File(fileName), LOC));
	}
	
	public String countAllFilesAndLOC() {
		
		int sum = 0;
		int number = 0;
		
		for (FileLOC fileLOC : fileLOCs) {
			sum += fileLOC.getLOC();
			number++;
		}
		
		return String.format("%-20s %d %s\r\n", number + " Dateie/n" + ":", sum + " LOC" );
	}
	
	public String toString() {
		
		String string = "";
		
		for (FileLOC fileLOC : fileLOCs) {
			string += (fileLOC.toString());
		}
		return string;
	}
}