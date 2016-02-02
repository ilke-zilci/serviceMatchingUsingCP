package uk.ac.ucl.cs.sse.dino.broker;

/**
 * Uniquely identifies a dino operation.
 *
 */
public final class ActionIdentifier {
	private final String sessionID;
	private final long actionID;

	/**
	 * Creates a new <tt>ActionIdentifier</tt> from the provided session ID and action ID.
	 * @param sessionID the session ID of the session which performed the action.
	 * @param actionID the sequential ID numebr of the action which was performed.
	 */
	public ActionIdentifier(final String sessionID, final long actionID) {
		this.sessionID = sessionID;
		this.actionID = actionID;
	}

	/**
	 * Gets the sequential action number of this action.
	 * @return actionID number of action.
	 */
	public long getActionID() {
		return actionID;
	}

	/**
	 * Gets the session ID which the action was performed in.
	 * @return the session identifier.
	 */
	public String getSessionID() {
		return sessionID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof ActionIdentifier)) {
			return false;
		}

		ActionIdentifier id = (ActionIdentifier) o;
		if (sessionID != id.sessionID) {
			return false;
		}
		if (actionID != this.actionID) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		hash = 37 * hash + sessionID.hashCode();
		hash = 37 * hash + (int)(actionID ^ (actionID >>> 32));
		return hash;
	}
	
	@Override
	public String toString() {
		return sessionID + ":" + actionID;
	}

}
