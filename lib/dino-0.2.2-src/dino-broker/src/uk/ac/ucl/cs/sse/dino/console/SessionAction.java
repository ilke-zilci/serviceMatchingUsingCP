package uk.ac.ucl.cs.sse.dino.console;

import uk.ac.ucl.cs.sse.dino.broker.ActionIdentifier;

/**
 * Stores the details about what action a Dino Broker has performed.
 * 
 */
final class SessionAction {
	private final SessionActionType actionType;
	private final long id;
	private final String inputString;
	private String outputString;
	private long startTime;
	private long executionTime = -1;

	/**
	 * Enumeration of types of actions which a Dino Broker can be performing.
	 */
	enum SessionActionType {
		/**
		 * Represents the action type of registering a Dino ReqDoc.
		 */
		RegisterReqDoc("Registering ReqDoc"),

		/**
		 * Represents the action type of registering a CapDoc.
		 */
		RegisterCapDoc("Registering CapDoc"),
		
		/**
		 * Represents the action type of selecting a mode.
		 */
		SelectMode("Selecting mode"),
		
		/**
		 * Represents the action type of invoking a service.
		 */
		InvokeService("Invoking service");

		private String actionString;

		private SessionActionType(String actionString) {
			this.actionString = actionString;
		}

		@Override
		public String toString() {
			return actionString;
		}
	};

	/**
	 * Creates a new <tt>SessionAction</tt> with the provided information.
	 * 
	 * @param actionID
	 *            ID which identifies this action to the DinoConsole so that
	 *            references to the same action can be correlated.
	 * @param actionType
	 *            the type of action which the DinoBroker performed.
	 * @param inputString
	 *            string representing the input to the action.
	 * @param startTime
	 *            the time at which the action started to be performed.
	 */
	SessionAction(final ActionIdentifier actionID,
			final SessionActionType actionType, final String inputString,
			long startTime) {
		this.actionType = actionType;
		this.id = actionID.getActionID();
		this.inputString = inputString;
		this.startTime = startTime;
	}

	/**
	 * Adds the information to this <tt>SessionAction</tt> object about the
	 * completion fo the action.
	 * 
	 * @param outputString
	 *            string representing the output from the action.
	 * @param completionTime
	 *            the tim at which the action was completed.
	 */
	void executionComplete(String outputString, long completionTime) {
		this.outputString = outputString;
		this.executionTime = completionTime - startTime;
	}

	/**
	 * Gets the output string for this action.
	 * 
	 * @return the output string for the action.
	 */
	String getOutputString() {
		return outputString;
	}

	/**
	 * Gets the type of the action representing by this <tt>SessionAction</tt>.
	 * this is just the type of operation which was called on the Dino Broker.
	 * 
	 * @return the type of action performed.
	 */
	public SessionActionType getActionType() {
		return actionType;
	}

	/**
	 * Gets the ID for this action. this allwos the console to correlate
	 * references to the same action.
	 * 
	 * @return the number representing the action ID.
	 */
	long getID() {
		return id;
	}

	/**
	 * Gets the input string for this action.
	 * 
	 * @return the input string for the action.
	 */
	String getInputString() {
		return inputString;
	}

	/**
	 * Gets the time taken to execute this action.
	 * 
	 * @return the execution time for the action or -1 if the action is not yet
	 *         complete.
	 */
	long getExecutionTime() {
		return executionTime;
	}
}
