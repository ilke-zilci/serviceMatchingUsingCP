package uk.ac.ucl.cs.sse.dino.doc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * A representation of a Dino ReqDoc.
 */
public class ReqDoc extends DinoDoc {


	/**
	 * Creates a new Dino ReqDoc with the specified name.
	 * 
	 * @param name
	 *            the non-null name of the Dino ReqDoc.
	 */
	public ReqDoc(final String name) {
		super(name);
	}
	


	/**
	 * Adds a service requirement to the named mode. If the mode does not exist
	 * then it is created.
	 * 
	 * @param modeName
	 *            the non-null name of the mode to which the service requirement
	 *            should be added.
	 * @param s
	 *            the non-null service requirement to add.
	 */
	void addServiceRequirement(final String modeName, final ServiceRequirement s) {
		// Preconditions
		if (modeName == null) {
			throw new NullPointerException("null mode name.");
		}
		if (s == null) {
			throw new NullPointerException("null service requirement.");
		}

		Mode mode = getMode(modeName);
		if (mode == null) {
			createMode(modeName);
			mode = getMode(modeName);
		}
		mode.addServiceRequirement(s);
	}

	/**
	 * Adds a service requirement to all modes. This is intended to be used to
	 * add modes for the &lt;common&gt; element which specifies service
	 * requirements for all modes.
	 * 
	 * @param s
	 *            the non-null service requirement to add.
	 */
	void addServiceRequirementToAllModes(final ServiceRequirement s) {
		// Preconditions
		if (s == null) {
			throw new NullPointerException("null service requirement.");
		}

		for (Mode mode : getAllModes()) {
			mode.addServiceRequirement(s);
		}

		// Postconditions
	}
	
	/**
	 * Gets all the requirements found in this ReqDoc from all modes.
	 * @return a collection of the requirements in this ReqDoc.
	 */
	public Collection<ServiceRequirement> getAllRequirements() {
		Set<ServiceRequirement> reqs = new HashSet<ServiceRequirement>();
		Collection<Mode> modes = getAllModes();
		for (Mode m : modes) {
			reqs.addAll(m.getServiceRequirements());
		}
		
		return reqs;
	}
	

}
