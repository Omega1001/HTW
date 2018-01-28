package ueb11;

import java.io.File;

/**
 * Class to keep track of the lines of code of a given file.
 * <p>
 * @author Jannik ADAM
 * @author Fromm-Borys
 */
public class FileLOC {
	
	private File file;
	private int LOC;
	
	/**
	 * Constructor for a FileLOC object<br>
	 * Constructor to generate a new FileLOC object which contains a file and its lines of code.
	 * <p>
	 * @param file
	 * 		the file
	 * @param LOC
	 * 		the lines of code
	 */
	public FileLOC(File file, int LOC) {
		this.file = file;
		this.LOC = LOC;
	}
	
	/**
	 * Returns the lines of code of the file.
	 * 
	 * @return
	 * 		Lines of code
	 */
	public int getLOC() {
		return LOC;
	}
	
	/**
	 * Method to output the current state of an object of the type {@link FileLOC} as a formatted string.
	 * <p>
	 * @return
	 * 		Current state of the object as a formatted string.
	 */
	@Override
	public String toString() {
		return String.format("%-30s %d %s\r\n", file.getName() + ":", LOC, "LOC" );
	}
}
