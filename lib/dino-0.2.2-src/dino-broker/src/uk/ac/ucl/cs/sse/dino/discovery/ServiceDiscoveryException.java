package uk.ac.ucl.cs.sse.dino.discovery;

/**
 * Thrown to indicate that no set of services could be found that match the
 * requested mode or modes.
 */
public class ServiceDiscoveryException extends Exception {
	private static final long serialVersionUID = -5606508628233658002L;

	/**
	 * Constructs a <tt>ServiceDiscoveryException</tt> with the specified
	 * detail message.
	 * 
	 * @param m
	 *            the detail message.
	 */
	public ServiceDiscoveryException(String m) {
		super(m);
	}

	/**
	 * Constructs a <tt>ServiceDiscoveryException</tt> with the specified
	 * detail message and cause.
	 * 
	 * @param m
	 *            the detail message.
	 * @param e
	 *            the cause.
	 */
	public ServiceDiscoveryException(String m, Throwable e) {
		super(m, e);
	}

}
