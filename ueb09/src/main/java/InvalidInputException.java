
public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3613289305328322102L;

	private String invalidInput;
	private String expected;

	public InvalidInputException(String message, Object invalidInput,
			String expected) {
		super(message != null ? message : "");
		this.invalidInput = invalidInput != null ? invalidInput.toString() : "";
		this.expected = expected != null ? expected : "";
	}

	@Override
	public String getMessage() {
		return super.getMessage().concat(" -> expected :'"
				.concat(expected).concat("' but got '").concat(
						invalidInput).concat("'"));
	}
}
