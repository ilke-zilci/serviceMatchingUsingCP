package uk.ac.ucl.cs.sse.dino.invocation;

/**
 * Thrown when there has been an error executing a service which is external to
 * the service execution engine.
 * 
 */
public class ServiceExecutionException extends Exception {
	
	/**
	 * Constructs a <tt>ServiceExecutionException</tt> with the specified detail message.
	 * @param m the detail message.
	 */
	public ServiceExecutionException(String m) {
		super(m);
	}

	/**
	 * Constructs a <tt>ServiceExecutionException</tt> with the specified detail message and cause.
	 * @param m the detail message.
	 * @param e the cause.
	 */
	public ServiceExecutionException(String m, Throwable e) {
		super(m, e);
	}

}
