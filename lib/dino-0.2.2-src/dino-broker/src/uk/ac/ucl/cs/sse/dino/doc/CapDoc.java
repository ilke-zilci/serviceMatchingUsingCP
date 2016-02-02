package uk.ac.ucl.cs.sse.dino.doc;


/**
 * Represents a CapDoc. A Dino CapDoc describes the capabilities of a service
 * provider. this typically includes a number of modes and specifies what
 * services are provided in each mode. For provided services, functional and
 * quality of service specifications are given.
 * 
 */
public final class CapDoc extends DinoDoc {
	/**
	 * Creates an empty CapDoc with the specified name.
	 * 
	 * @param name
	 *            the name of this CapDoc.
	 */
	public CapDoc(final String name) {
		super(name);
	}

	/**
	 * Adds a service implementation to a given mode of this CapDoc.
	 * 
	 * @param modeName
	 *            the name of the mode to which the service implementation
	 *            should be added.
	 * @param implementation
	 *            the service implementation to add to the CapDoc.
	 */
	void addServiceImplementation(final String modeName, final ServiceImplementation implementation) {
		assert modeName != null;
		assert implementation != null;

		// Method body
		Mode mode = getMode(modeName);
		if (mode == null) {
			createMode(modeName);
			mode = getMode(modeName);
		}
		mode.addServiceImplementation(implementation);
	}

	/**
	 * Adds a service implementation to all modes of this CapDoc. It is
	 * important that all modes have been created before this method is called,
	 * as the implementation is only added to existing modes, not to ones which
	 * are subsequently added.
	 * 
	 * @param implementation
	 *            the service implementation to add.
	 */
	void addServiceImplementationToAllModes(final ServiceImplementation implementation) {
		assert implementation != null;

		// Method body
		for (Mode mode : getAllModes()) {
			mode.addServiceImplementation(implementation);
		}
	}

}
