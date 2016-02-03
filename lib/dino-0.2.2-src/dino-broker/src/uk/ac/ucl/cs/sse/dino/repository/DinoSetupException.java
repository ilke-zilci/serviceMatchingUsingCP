package uk.ac.ucl.cs.sse.dino.repository;

/**
 * Thrown when there is a problem setting up a Dino component.
 *
 */
public class DinoSetupException extends Exception {

	/**
	 * Constructs a <tt>DinoSetupException</tt> with the specified detail message.
	 * @param m the detail message.
	 */
	public DinoSetupException(String m) {
		super(m);
	}

	/**
	 * Constructs a <tt>DinoSetupException</tt> with the specified detail message and cause.
	 * @param m the detail message.
	 * @param e the cause.
	 */
	public DinoSetupException(String m, Throwable e) {
		super(m, e);
	}

}
