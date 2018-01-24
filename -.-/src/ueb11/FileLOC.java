package ueb11;

import java.io.File;

/**
 *
 * Class to keep track of the lines of code of a given .java file.
 * 
 * @author Jannik ADAM
 * @author Fromm-Borys
 */
public class FileLOC {
	
	private File file;
	private int LOC;
	
	public FileLOC(File file, int LOC) {
		this.file = file;
		this.LOC = LOC;
	}
	public int getLOC() {
		return LOC;
	}
	@Override
	public String toString() {
		return String.format("%-20s %d %s\r\n", file.getName() + ":", LOC, "LOC" );
	}
}
