package ueb15;

public class PalindromeIterative extends AbstractPalindrome {

	@Override
	protected boolean _isPalindrom(String word) {
		
		int stringLengthHalf = word.length()/2;
		int length = word.length() - 1;
		char [] wd = word.toCharArray();
		
		for (int i = 0; i < stringLengthHalf; i++) {
			if (wd[i] != wd[length - i]) {
				return false;
			}
		}

		return true;
	}
	

}
