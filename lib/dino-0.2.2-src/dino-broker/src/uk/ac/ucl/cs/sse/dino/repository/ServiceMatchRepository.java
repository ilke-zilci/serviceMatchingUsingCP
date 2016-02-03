package uk.ac.ucl.cs.sse.dino.repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Stores sets of service implementations which are known to match service
 * requirements.
 * 
 */
final class ServiceMatchRepository {
	private final Map<ServiceRequirement, Set<ServiceImplementation>> serviceMatchMap = new ConcurrentHashMap<ServiceRequirement, Set<ServiceImplementation>>();
	private final ServiceInformationRepositoryImpl repository;

	/**
	 * Creates an empty service match repository.
	 * 
	 * @param repository
	 *            the repository to which this match repository belongs.
	 * 
	 */
	ServiceMatchRepository(final ServiceInformationRepositoryImpl repository) {
		this.repository = repository;
	}

	/**
	 * Adds a match to this repository.
	 * 
	 * @param requirement
	 *            the requirement which has been matched.
	 * @param implementation
	 *            the service implementation which matches the requirement.
	 */
	void addMatch(final ServiceRequirement requirement,
			final ServiceImplementation implementation) {
		Set<ServiceImplementation> set = serviceMatchMap.get(requirement);
		if (set == null) {
			set = Collections.synchronizedSet(new HashSet<ServiceImplementation>());
			serviceMatchMap.put(requirement, set);
		}

		set.add(implementation);

		repository.fireAddedMatch(requirement, implementation);
	}

	/**
	 * Removes the specified service implementation from all sets of matches.
	 * 
	 * @param implementation
	 *            the service implementation to remove.
	 */
	void clearMatch(final ServiceImplementation implementation) {
		for (Set<ServiceImplementation> implSet : serviceMatchMap.values()) {
			implSet.remove(implementation);
		}

		repository.fireRemovedImplementation(implementation);
	}

	/**
	 * Gets all the matches which match the given requirement.
	 * 
	 * @param requirement
	 *            the requirement for which the matching implementations should
	 *            be found.
	 * @return the set of service implementations which match the given
	 *         requirement.
	 */
	Set<ServiceImplementation> getMatches(final ServiceRequirement requirement) {
		Set<ServiceImplementation> matches = serviceMatchMap.get(requirement);
		if (matches != null) {
			return new HashSet<ServiceImplementation>(matches);
		} else {
			return Collections.emptySet();
		}
	}

	/**
	 * Gets all the requirements in this match repository.
	 * 
	 * @return the set of requirements matched by this match repository.
	 */
	Set<ServiceRequirement> getRequriements() {
		return new HashSet<ServiceRequirement>(serviceMatchMap.keySet());
	}
}
