package ueb15;

public class PalindromeRecursive2 extends AbstarctPalindrome {

	private int stringLengthHalf = 0;

	@Override
	protected boolean _isPalindrom(String word) {
		stringLengthHalf = word.length() / 2;
		return recursion(word, 0);
	}

	private boolean recursion(String word, int index) {
		if (index < stringLengthHalf) {
			return word.charAt(index) == word.charAt(word.length() - 1
					- index) && recursion(word, ++index);
		}
		return true;
	}
}
