package ueb15;

public abstract class AbstarctPalindrome implements Palindrom {


	public final boolean isPalindrome(String word) {
		if (null == word) {

			throw new IllegalArgumentException("String must not be null!");

		}

		if (!("").equals(word)) {

			throw new IllegalArgumentException(
					"String must not be empty!");

		}

		if (!word.matches("[a-zA-Z]")) {

			throw new IllegalArgumentException(
					"String must consist of letters!");

		}

		word = word.toUpperCase();

		return _isPalindrom(word);
	}
	
	protected abstract boolean _isPalindrom (String word);

}
