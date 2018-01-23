package ueb11;

/**
 * Class to count all "lines of code" in a given .java file.
 * 
 * @author Jannik ADAM
 * @author Fromm-Borys
 */
public class FileLOC {
	
	private String fileName;
	private int LOC;
	
	public FileLOC(String fileName, int LOC) {
		this.fileName = fileName;
		this.LOC = LOC;
	}
	public int getLOC() {
		return LOC;
	}
	@Override
	public String toString() {
		return String.format("%-20s %d %s\r\n", fileName + ":", LOC, "LOC" );
	}
}
