package uk.ac.ucl.cs.sse.dino.doc;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * A representation of an execution mode from a Dino ReqDoc.
 */
public final class Mode {
	private String name;

	private Map<String, ServiceRequirement> serviceRequirementMap = new HashMap<String, ServiceRequirement>();
	private Set<ServiceImplementation> serviceImplementationSet = new HashSet<ServiceImplementation>();

	/**
	 * Create a new Dino ReqDoc execution mode with the specified name.
	 * 
	 * @param name
	 *            the non-null name of the execution mode.
	 */
	Mode(final String name) {
		// Preconditions
		if (name == null) {
			throw new NullPointerException("null name.");
		}

		this.name = name;
	}

	/**
	 * Adds a required service to this execution mode.
	 * 
	 * @param s
	 *            the service requirement to add. Must not be null.
	 */
	void addServiceRequirement(final ServiceRequirement s) {
		assert s != null;

		serviceRequirementMap.put(s.getName(), s);
	}

	/**
	 * Adds a service implementation to this mode.
	 * @param implementation the service implementation to add.
	 */
	void addServiceImplementation(final ServiceImplementation implementation) {
		assert implementation != null;

		serviceImplementationSet.add(implementation);
	}

	/**
	 * Gets the service requirement with the specified name.
	 * 
	 * @param serviceName
	 *            the name of the service requirement to retrieve.
	 * @return the service requirement with the provided name or null if the
	 *         service name was unknown.
	 */
	public ServiceRequirement getServiceRequirement(final String serviceName) {
		// Precondition
		if (serviceName == null) {
			throw new NullPointerException("null service name.");
		}

		ServiceRequirement req = serviceRequirementMap.get(serviceName);

		// Postcondition
		assert serviceRequirementMap.containsKey(serviceName) ? req != null : req == null;

		return req;
	}

	/**
	 * Gets the requirements of this mode.
	 * 
	 * @return a collection of service requirements of this mode.
	 */
	public Collection<ServiceRequirement> getServiceRequirements() {
		return new HashSet<ServiceRequirement>(serviceRequirementMap.values());
	}

	/**
	 * Gets the service implementations provided in this mode.
	 * 
	 * @return a collection of service implementations provided in this mode. If
	 *         there are no provided services, the empty list is returned.
	 */
	public Collection<ServiceImplementation> getServiceImpelmentations() {
		return new HashSet<ServiceImplementation>(serviceImplementationSet);
	}

	/**
	 * The name of this mode.
	 * 
	 * @return the non-null name of this mode.
	 */
	public String getName() {
		return name;
	}
}
