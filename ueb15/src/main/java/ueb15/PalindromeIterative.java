package ueb15;

public class PalindromeIterative extends AbstarctPalindrome {

	@Override
	protected boolean _isPalindrom(String word) {
		boolean is = true;

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
				is = false;
				break;
			}
		}

		return is;
	}
	

}
