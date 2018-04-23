package ueb15._;

public class Palindrom {

	public static final int ITERATIVE = 0;
	public static final int RECURSIVE = 1;

	public boolean isPalindromString(String word, int mode) {

		if ("".equals(word)) {
			throw new IllegalArgumentException(
					"String must not be empty!");

		}

		if (!word.matches("[a-zA-Z]")) {
			throw new IllegalArgumentException(
					"String must consist of letters!");

		}

		word = word.toLowerCase();

		if (mode == ITERATIVE) {
			return isPalindromIt(word);

		} else {
			return isPalindromRe(word);
		}

	}

	public boolean isPalindromIt(String word) {

		boolean is = true;

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
				is = false;
				break;
			}
		}

		return is;
	}


	private boolean isPalindromRe(String word) {
		
		if(word.length() > 1) {
			return word.charAt(0) == word.charAt(word.length()-2) &&
					isPalindromRe(word.substring(1, word.length()-1));
		}else {
			return true;
		}
//
//		if (word.charAt(0) != word.charAt(word.length() - 1)) {
//			return false;
//		}
//
//		if (word.length() == 1) {
//
//			return true;
//
//		}
//
//		return isPalindromRe(word.substring(1));
	}
}