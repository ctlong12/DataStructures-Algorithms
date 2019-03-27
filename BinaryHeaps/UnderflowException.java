public class UnderflowException extends Exception {

	/* to send in a specific error message */
	public UnderflowException(String message) {
		super(message);
	}

	/* default message */
	public UnderflowException() {
		super("Empty Tree");
	}
}