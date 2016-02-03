package uk.ac.ucl.cs.sse.dino;

/**
 * Thrown when the requested service is not supported in the currect mode (or
 * not supported in any mode).
 * 
 */
public class UnsupportedServiceException extends Exception {
	private static final long serialVersionUID = -6323094952607560312L;

	/**
	 * Constructs a <tt>UnsupportedServiceException</tt> with the specified
	 * detail message.
	 * 
	 * @param m
	 *            the detail message.
	 */
	public UnsupportedServiceException(String m) {
		super(m);
	}

	/**
	 * Constructs a <tt>UnsupportedServiceException</tt> with the specified
	 * detail message and cause.
	 * 
	 * @param m
	 *            the detail message.
	 * @param e
	 *            the cause.
	 */
	public UnsupportedServiceException(String m, Throwable e) {
		super(m, e);
	}

}
