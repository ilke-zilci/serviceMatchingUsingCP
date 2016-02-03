package uk.ac.ucl.cs.sse.dino.invocation.owls;

import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;


/**
 * Thrown to indicate that an OWL-S parameter is not a valid parameter in the
 * OWL-S service description being invoked.
 * 
 */
public class InvalidInvocationParameterException extends
		ServiceInvocationException {
	private static final long serialVersionUID = -4042651821116854377L;

	/**
	 * Constructs a <tt>InvalidInvocationParameterException</tt> with the specified detail message.
	 * @param m the detail message.
	 */
	public InvalidInvocationParameterException(String m) {
		super(m);
	}

	/**
	 * Constructs a <tt>InvalidInvocationParameterException</tt> with the
	 * specified detail message and cause.
	 * 
	 * @param m
	 *            the detail message.
	 * @param e
	 *            the cause.
	 */
	public InvalidInvocationParameterException(String m, Throwable e) {
		super(m, e);
	}

}
