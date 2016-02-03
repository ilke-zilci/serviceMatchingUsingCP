package uk.ac.ucl.cs.sse.dino.client.restaurant.osmdisplay;

/**
 * Indicates that there was a problem displaying a map.
 *
 */
public class MapDisplayException extends Exception {
	/**
	 * Constructs a <tt>MapDisplayException</tt> with the specified detail message.
	 * @param msg the detail message.
	 */
	public MapDisplayException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <tt>MapDisplayException</tt> with the
	 * specified detail message and cause.
	 * 
	 * @param msg
	 *            the detail message.
	 * @param e
	 *            the cause.
	 */
	public MapDisplayException(String msg, Throwable e) {
		super(e);
	}

}
