package uk.ac.ucl.cs.sse.dino.matchmaker;

/**
 * Indicates a problem registering a new service implementation with a matchmaker.
 */
public class MatchmakerServiceRegistrationException extends Exception {
	/**
	 * Constructs a <tt>MatchmakerServiceRegistrationException</tt> with the specified detail message.
	 * @param msg the detail message.
	 */
	public MatchmakerServiceRegistrationException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <tt>MatchmakerServiceRegistrationException</tt> with the
	 * specified detail message and cause.
	 * 
	 * @param msg
	 *            the detail message.
	 * @param e
	 *            the cause.
	 */
	public MatchmakerServiceRegistrationException(String msg, Throwable e) {
		super(msg, e);
	}

}
