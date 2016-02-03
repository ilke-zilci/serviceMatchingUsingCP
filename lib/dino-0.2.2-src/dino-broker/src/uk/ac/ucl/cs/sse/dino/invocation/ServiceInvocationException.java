package uk.ac.ucl.cs.sse.dino.invocation;

/**
 * Thrown whenever there is any problem invoking a service through the OWL-S API.
 *
 */
public class ServiceInvocationException extends Exception {
	private static final long serialVersionUID = -6465220143937568408L;

	/**
	 * Constructs a <tt>ServiceInvocationException</tt> with the specified detail message.
	 * @param m the detail message.
	 */
	public ServiceInvocationException(String m) {
		super(m);
	}

	/**
	 * Constructs a <tt>ServiceInvocationException</tt> with the specified detail message and cause.
	 * @param m the detail message.
	 * @param e the cause.
	 */
	public ServiceInvocationException(String m, Throwable e) {
		super(m, e);
	}

}
