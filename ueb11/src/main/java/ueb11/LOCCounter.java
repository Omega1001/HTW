package ueb11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to count all "lines of code" in a given .java file.
 * <p>
 * @author Jannik ADAM
 * @author Fromm-Borys
 */
public class LOCCounter {
	
	private static final Pattern JAV_DOC = Pattern.compile("^ *?//.*$");
	private static final String DOT_JAV = ".*?\\.java$";
	
	private List<FileLOC> fileLOCs;

	public LOCCounter(){
		this. fileLOCs = new ArrayList<>();
	}
	
	/**
	 * Method to count the lines of code(LOC) of a given .java file.
	 * <p>
	 * @param file
	 * 		The committed file 
	 * @return 
	 * @throws FileNotFoundException
	 * 		If the file does not exist
	 * @throws FileDoesNotExistException 
	 * @throws FielIsDirectoryException 
	 */
	public void countLOC(File file) throws FileNotFoundException, FileDoesNotExistException, FielIsDirectoryException{
	
		if (!(file.exists())) {
			throw new FileDoesNotExistException("Datei existiert nicht!");
		}
		if (file.isDirectory()) {
			throw new FielIsDirectoryException ("Adresse gehoert einem Ordner!");
		}
		int LOC = 0;
		String fileName = file.getName();
		
		if (!(fileName.matches(DOT_JAV))) {
			int formatIndex = fileName.lastIndexOf('.');
			String fileFormat = fileName.substring(formatIndex);
			throw new InvalidFileFormatException ("Ungueltiges Dateiformat", fileFormat, ".java");
		}
		Scanner scanner = new Scanner(file);
		String line = "";
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			Matcher javDoc1Mat = JAV_DOC.matcher(line);
			if ( !("".equals(line.trim()) && !javDoc1Mat.find())) {
				LOC++;
			}
		}
		fileLOCs.add(new FileLOC(file, LOC));
	}
	
	/**
	 * Method to count the lines of code(LOC) of a given path leading to a .java file.
	 * <p>
	 * @param fileName
	 * 		Path to the file.
	 * @return 
	 * @throws FileDoesNotExistException 
	 * @throws FileNotFoundException
	 * 		If the file does not exist.
	 * @throws FielIsDirectoryException 
	 */
	public void countLOCPath(String fileName) throws FileNotFoundException, FileDoesNotExistException, FielIsDirectoryException{
		
		File file = new File(fileName);

		countLOC(file);
	}
	
	/**
	 * Method to output the amount of all files and the sum of their lines of code contained in an object of the type {@link LOCCounter} as a formatted string.
	 * <p>
	 * @return
	 * 		Amount of all files and LOC of the object as a formatted string.
	 */
	public String countAllFilesAndLOC() {
		
		int sum = 0;
		int number = 0;
		
		for (FileLOC fileLOC : fileLOCs) {
			sum += fileLOC.getLOC();
			number++;
		}
		return String.format("%-30s %d %s\r\n", number + " Dateie/n" + ":", sum, " LOC" );
	}
	
	/**
	 * Method to output the current state of all objects of the type {@link FileLOC} contained in the object of the type {@link LOCCounter} as a string.
	 * <p>
	 * @return
	 * 		Current state of the object as a string.
	 */
	@Override
	public String toString() {
		
		String string = "";
		
		for (FileLOC fileLOC : fileLOCs) {
			string += (fileLOC.toString());
		}
		return string;
	}
}