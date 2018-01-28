package ueb11;

public class InvalidFileFormatException extends RuntimeException {
	
	private static final long serialVersionUID = -7665469756928545233L; 
	
	private String invalidFileFormat;
	private String expected;

	public InvalidFileFormatException(String message, Object invalidFileFormat,
			String expected) {
		super(message != null ? message : "");
		this.invalidFileFormat = invalidFileFormat != null ? invalidFileFormat.toString() : "";
		this.expected = expected != null ? expected : "";
	}

	@Override
	public String getMessage() {
		return super.getMessage().concat(" -> expected :'"
				.concat(expected).concat("' but got '").concat(
						invalidFileFormat).concat("'"));
	}
}
