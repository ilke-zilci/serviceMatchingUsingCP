package uk.ac.ucl.cs.sse.dino;

/**
 * Thrown to indicate that a session ID is not valid. Either the session ID was
 * never generated or the session corresponding to the ID has been previously
 * quit by the client.
 * 
 */
public class DinoIdException extends Exception {
	private static final long serialVersionUID = -5503959536867692710L;

	/**
	 * Constructs a <tt>DinoIdException</tt> with the specified detail message.
	 * @param m the detail message.
	 */
	public DinoIdException(String m) {
		super(m);
	}

	/**
	 * Constructs a <tt>DinoIdException</tt> with the specified detail message and cause.
	 * @param m the detail message.
	 * @param e the cause.
	 */
	public DinoIdException(String m, Throwable e) {
		super(m, e);
	}
}
