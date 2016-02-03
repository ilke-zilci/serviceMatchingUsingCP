package uk.ac.ucl.cs.sse.dino.doc;

/**
 * Thrown to indicate a problem reading a Dino CapDoc, ReqDoc or QosDoc.
 *
 */
public class DinoDocReadException extends Exception {
	private static final long serialVersionUID = 1784984188423096408L;

	/**
	 * Constructs a <tt>ReqDocReadException</tt> with the specified detail message.
	 * @param m the detail message.
	 */
	public DinoDocReadException(final String m) {
		super(m);
	}

	/**
	 * Constructs a <tt>ReqDocReadException</tt> with the specified detail message and cause.
	 * @param m the detail message.
	 * @param e the cause.
	 */
	public DinoDocReadException(final String m, final Throwable e) {
		super(m, e);
	}

}
