package uk.ac.ucl.cs.sse.dino.doc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class representing the common elements of Dino ReqDocs and CapDocs.
 * 
 */
abstract class DinoDoc {
	private Map<String, Mode> modeMap = new HashMap<String, Mode>();

	private String name;

	/**
	 * Creates a new Dino document with the given name.
	 * @param name the name of the Dino document.
	 */
	protected DinoDoc(final String name) {
		// Preconditions
		if (name == null) {
			throw new NullPointerException("null name");
		}

		this.name = name;
	}

	/**
	 * Adds a mode to this Dino document.
	 * @param modeName the name of the mode to add.
	 */
	void addMode(final String modeName) {
		assert modeName != null;

		Mode mode = modeMap.get(modeName);
		if (mode == null) {
			mode = new Mode(modeName);
			modeMap.put(modeName, mode);
		}

		assert modeMap.containsKey(modeName);
	}

	/**
	 * Gets the mode with the given name.
	 * 
	 * @param modeName
	 *            the name of the mode to get.
	 * @return the mode with the given name.
	 */
	public Mode getMode(final String modeName) {
		if (modeName == null) {
			throw new NullPointerException("Null parameter: modeName");
		}

		return modeMap.get(modeName);
	}

	/**
	 * Creates a new mode in this Dino docuemnt.
	 * @param modeName the name of the mode to create.
	 */
	protected void createMode(final String modeName) {
		Mode mode = new Mode(modeName);
		modeMap.put(modeName, mode);
	}

	/**
	 * Gets all the modes in this document.
	 * @return a collection of the modes in this Dino document.
	 */
	protected Collection<Mode> getAllModes() {
		return modeMap.values();
	}

	/**
	 * Gets the name of this document.
	 * 
	 * @return the name of this document.
	 */
	public String getName() {
		return name;
	}
}
