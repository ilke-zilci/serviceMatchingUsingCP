package uk.ac.ucl.cs.sse.dino;

/**
 * Thrown when there is a problem communicating with the broker, normally because the broker is remote.
 *
 */
public class BrokerCommunicationException extends Exception {

	/**
	 * Constructs a <code>BrokerCommunicationException</code> with the specified detail message.
	 * @param m the detail message.
	 */
	public BrokerCommunicationException(String m) {
		super(m);
	}

	/**
	 * Constructs a <code>BrokerCommunicationException</code> with the
	 * specified detail message and cause.
	 * 
	 * @param m
	 *            the detail message.
	 * @param e
	 *            the cause.
	 */
	public BrokerCommunicationException(String m, Throwable e) {
		super(m, e);
	}

}
