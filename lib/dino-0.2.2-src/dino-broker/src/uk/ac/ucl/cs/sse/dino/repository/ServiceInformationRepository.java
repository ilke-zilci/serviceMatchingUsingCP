package uk.ac.ucl.cs.sse.dino.repository;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.selector.ServiceSelectionListener;

/**
 * Interface to a repository that stores matches between service requirements
 * and implementations and statistics about service executions.
 */
public interface ServiceInformationRepository {
	/**
	 * Informs this repository that a new service requirement has been
	 * registered.
	 * 
	 * @param requirement
	 *            the requirement which has been registered.
	 */
	public void newRequirementRegistered(final ServiceRequirement requirement);

	/**
	 * Adds a listener to this repository.
	 * 
	 * @param l
	 *            the listener to add.
	 */
	public void addListener(ServiceImplementationRepositoryListener l);

	/**
	 * Adds the given service implementation to this repository.
	 * 
	 * @param impl
	 *            the implementation to add.
	 * @throws ServiceNotRegisteredException
	 */
	public void addServiceImplementation(final ServiceImplementation impl)
			throws ServiceNotRegisteredException;

	/**
	 * Updates an attribute by adding a new data point.
	 * 
	 * @param serviceIdentifier
	 *            the URI which identifies the service implementation to which
	 *            this attribute relates.
	 * @param attributeName
	 *            the name of the attribute.
	 * @param value
	 *            the value of the new data point.
	 */
	public void updateAttribute(final URI serviceIdentifier,
			final String attributeName, final double value);

	/**
	 * Resets an attribute by removing all existing data points.
	 * 
	 * @param serviceIdentifier
	 *            the URI which identifies the service implementation to which
	 *            this attribute relates.
	 * @param attributeName
	 *            the name of the attribute.
	 */
	public void resetAttribute(final URI serviceIdentifier,
			final String attributeName);

	/**
	 * Gets the average value of the specified attribute.
	 * 
	 * @param serviceIdentifier
	 *            the URI which identifies the service implementation for which
	 *            to get the attribute.
	 * @param attributeName
	 *            the name of the attribute to get the value.
	 * @return the average value of the attribute.
	 */
	public double getAttributeAverage(final URI serviceIdentifier,
			final String attributeName);

	/**
	 * Adds a <tt>ServiceSelectionListener</tt> to the
	 * <tt>ServiceSelector</tt> used by this repository.
	 * 
	 * @param l
	 *            the <tt>ServiceSeelctionListener</tt> to add.
	 */
	public void addServiceSelectionListener(final ServiceSelectionListener l);

	/**
	 * Checks the information repository for new matches for the given
	 * requirements. The matches which are made should be stored so that the
	 * results can be used when satisfiesAllRequirements or
	 * getMatchingImpelmentations are called.
	 * 
	 * @param reqs
	 *            the requirements for which to check for new requirements.
	 */
	public void checkForNewMatches(final Collection<ServiceRequirement> reqs);

	/**
	 * Gets the set of all requirements which have at least one match.
	 * 
	 * @return the set of matched requirements.
	 */
	public Set<ServiceRequirement> getMatchedRequriements();

	/**
	 * Gets the service implementations which match the given requirement.
	 * 
	 * @param requirement
	 *            the requirement for which to find implementations.
	 * @return the set of service implementations which match the requirement.
	 */
	public Set<ServiceImplementation> getMatchingImplementations(
			final ServiceRequirement requirement);

	/**
	 * Removes a listener from this repository.
	 * 
	 * @param l
	 *            the listener to remove.
	 */
	public void removeListener(ServiceImplementationRepositoryListener l);

	/**
	 * Determines whether the provided requirements can be satisfied by service
	 * implementations held in this repository.
	 * 
	 * @param reqs
	 *            the collection of requirements for which to find
	 *            implementations.
	 * @return true if the requirements can be satisfied, false otherwise.
	 */
	public boolean satisfiesAllRequirements(
			final Collection<ServiceRequirement> reqs);

	/**
	 * Remove the given service implementation from this repository.
	 * 
	 * @param impl
	 *            the service implementation to remove.
	 */
	public void removeServiceImplementation(final ServiceImplementation impl);

	/**
	 * Gets the service implementations which match the given requirement,
	 * sorted according to the service selector.
	 * 
	 * @param requirement
	 *            the requirement for which to find implementations.
	 * @return the ordered set of service implementations which match the
	 *         requirement, sorted by the service selector.
	 */
	public List<ServiceImplementation> getMatchingOrderedImplementations(
			final ServiceRequirement requirement);
	
	/**
	 * Gets the sum of the specified attribute.
	 * 
	 * @param serviceIdentifier
	 *            the URI which identifies the service implementation for which
	 *            to get the attribute.
	 * @param attributeName
	 *            the name of the attribute to get the value.
	 * @return the sum value of the attribute.
	 */
	public double getAttributeSum(final URI serviceIdentifier,
			final String attributeName);

}
