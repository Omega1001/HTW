package ueb15;

public class PalindromeIterative extends AbstarctPalindrome {

	@Override
	protected boolean _isPalindrom(String word) {
		
		int stringLengthHalf = word.length()/2;
		
		if (word.length()%2 == 1) {
			stringLengthHalf++;
		}

		for (int i = 0; i < stringLengthHalf; i++) {
			if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
				return false;
			}
		}

		return true;
	}
	

}
