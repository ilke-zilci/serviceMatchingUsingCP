package uk.ac.ucl.cs.sse.dino.repository;

/**
 * Thrown when a service could not be properly registered to a matchmaker.
 *
 */
public class ServiceNotRegisteredException extends Exception {

	/**
	 * Constructs a <tt>ServiceNotRegisteredException</tt> with the specified detail message.
	 * @param m the detail message.
	 */
	public ServiceNotRegisteredException(String m) {
		super(m);
	}

	/**
	 * Constructs a <tt>ServiceNotRegisteredException</tt> with the specified detail message and cause.
	 * @param m the detail message.
	 * @param e the cause.
	 */
	public ServiceNotRegisteredException(String m, Throwable e) {
		super(m, e);
	}

}
