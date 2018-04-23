package ueb15;

public class PalindromeIterative extends AbstarctPalindrome {

	@Override
	protected boolean _isPalindrom(String word) {

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
				return false;
			}
		}

		return true;
	}
	

}
