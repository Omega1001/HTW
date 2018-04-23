package ueb15;

import java.io.File;
import java.util.Scanner;

public abstract class AbstarctPalindrome implements Palindrom {
	
	public boolean readFile(String fileName) throws IOException {
		
	return readFile (FileReader fR = new FileReader(fileName));
		
	}
	
	public boolean readFile (File file) throws IOException {
		
		if (!(file.exists())) {

			throw new IllegalArgumentException ("File does not exist!");
			
		}
		
		if (file.isDirectory()) {

			throw new IllegalArgumentException ("File is a directory!");
		
		}
		
		if (file.length() == 0) {
			
			throw new IllegalArgumentException ("File is empty!");
			
		}
		
		BufferedReader bR = new BufferedReader(file);
	    String word = null;

	    try {
	    	
	        word = bR.readLine();
	        
	    }

	    return readString(word);
	    
	    } finally {
	    	
	        bR.close();
	        
	    }
		
	}
	
	public boolean readString (String word) {
		
		if (null == word) {
			
			throw new IllegalArgumentException ("String must not be null!");
			
		}
		
		if (!("").equals(word)) {
			
			throw new IllegalArgumentException ("String must not be empty!");
			
		}
		
		if (!word.matches("[a-zA-Z]")) {
			
			throw new IllegalArgumentException ("String must consist of letters!");
			
		}
		
		word = word.toUpperCase();
			
		return isPalindrom (word);
		
	}
	
	public abstract boolean isPalindrome(String word);

}
