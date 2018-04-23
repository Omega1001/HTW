package ueb15;

public class PalindromeScrapped {

    public static final int ITERATIVE = 0;
	public static final int RECURSIVE = 1;
	
	public boolean isPalindromString (String a, String b, int mode) {
		
		if (!("").equals(a) || !("").equals(b)) {
			
			throw new IllegalArgumentException ("String must not be empty!");
			
		}
		
		if (!a.matches("[a-zA-Z]") && !b.matches("[a-zA-Z]")) {
			
			throw new IllegalArgumentException ("String must consist of letters!");
			
		}
		
		a = a.shiftLowerCase();
		b = b.shiftLowerCase();
		
		if (a.length() != b.length()) {
			
			return false;
			
		}
		
		if (mode == ITERATIVE) {
			
			return isPalindromIt (a, b);
			
		}
		else {
			
			return isPalindromRe (a, b);
			
		}
		
	}
	
	public boolean isPalindromIt (String a, String b) {
		
		boolean is = true;
		
		for (int i = 0; i < a.length(); i++) {
			
			if (a.charAt(i) != b.charAt(a.length() - 1 - i)) {
				is = false;
			}
			
		}
		
		return is;
	}
	
	public boolean isPalindromRe (String a, String b) {
		
		if (a.charAt(0) != b.charAt(a.length() - 1)) {
			return false;
		}
		
		if (a.length() == 1) {
			
			return true;
			
		}
		
		return isPalindromRe(a.substring(1), b);
	}
	
	public String shiftLowerCase (String a) {
		
		for (int i = 0; i < a.length()-1; i++) {
			
			if (a.charAt(i).matches("[a-z]")) {
				
				a.charAt(i) = a.charAt(i) - 32;
			
			}
			
		}
		
		return a;
		
	}
}
