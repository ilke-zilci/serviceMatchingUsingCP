package uk.ac.ucl.cs.sse.dino.matchmaker;

/**
 * Thrown to indicate an attempt to use a service matcher which is incompatible
 * with the type of the provided service description.
 * 
 */
public class IncompatibleServiceMatcherException extends Exception {
	/**
	 * Creates an IncompatibleServiceMatcherException with the stated detail
	 * message.
	 * 
	 * @param msg a message detailing the exception.
	 */
	public IncompatibleServiceMatcherException(String msg) {
		super(msg);
	}

	/**
	 * Creates an IncompatibleServiceMatcherException with the stated detail
	 * message and cause.
	 * 
	 * @param msg a message detailing the exception.
	 * @param cause the exception which caused this exception.
	 */
	public IncompatibleServiceMatcherException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
