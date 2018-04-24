package ueb15;

public class PalindromeRecursive2 extends AbstarctPalindrome {

	private int stringLength = 0;
	private int stringLengthHalf = 0;
	char [] wd;

	@Override
	protected boolean _isPalindrom(String word) {
		stringLength = word.length();
		stringLengthHalf = word.length() / 2;
		wd = word.toCharArray();
		return recursion(0);
	}

	private boolean recursion(int index) {
		if (index < stringLengthHalf) {
			return wd[index] == wd[stringLength - 1
					- index] && recursion(++index);
		}
		return true;
	}
}
