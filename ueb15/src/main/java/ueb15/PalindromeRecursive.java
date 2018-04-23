package ueb15;

public class PalindromeRecursive extends AbstarctPalindrome {
	
	@Override
	protected boolean _isPalindrom(String word) {
		if(word.length() > 1) {
			return word.charAt(0) == word.charAt(word.length()-2) &&
					_isPalindrom(word.substring(1, word.length()-1));
		}else {
			return true;
		}
	}

}
