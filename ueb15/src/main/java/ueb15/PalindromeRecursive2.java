package ueb15;

public class PalindromeRecursive2 extends AbstarctPalindrome {

	@Override
	protected boolean _isPalindrom(String word) {
		int index = 0;
		int stringLengthHalf = word.length()/2;
			return recursion(word, index, stringLengthHalf);
	}
	private static boolean recursion (String word, int index, int stringLengthHalf) {
		if (index <  stringLengthHalf) {
			return word.charAt(index) == word.charAt(word.length()-1-index) && recursion(word, ++index, stringLengthHalf);
		}
		return true;
	}
}
