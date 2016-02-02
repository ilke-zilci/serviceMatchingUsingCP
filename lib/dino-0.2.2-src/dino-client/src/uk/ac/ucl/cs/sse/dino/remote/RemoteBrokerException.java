package uk.ac.ucl.cs.sse.dino.remote;

/**
 * Indicates a problem accessing a remote Dino Broker.
 */
public class RemoteBrokerException extends Exception {
	public RemoteBrokerException(String msg) {
		super(msg);
	}

	public RemoteBrokerException(String msg, Throwable e) {
		super(msg, e);
	}

}
