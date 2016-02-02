package uk.ac.ucl.cs.sse.dino.matchmaker;

import java.util.Set;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Interface for matchmakers which must match a service profile request to the
 * profile of a concrete OWL-S service description. The matched service should
 * have a binding so that it can be invoked by the Dino broker.
 */
public interface ServiceMatcher {
	/**
	 * Creates an empty <tt>ExtractedServiceInformation</tt> object of the
	 * appropriate type for this matcher. This will then be filled in with the
	 * details from a serialised file.
	 * 
	 * @param serviceDescriptionURI
	 *            URI to to which the extracted information will apply.
	 * @return an empty <tt>ExtractedServiceInformation</tt> object.
	 */
	public ExtractedServiceInformation createEmptyServiceInforamtion();

	/**
	 * Informs the service matcher that a new service implementation has been
	 * registered. This method should return its extracted service information
	 * so that this information can be added to the serialised repository.
	 * 
	 * @param impl
	 *            the service implementation which was added.
	 * @return the information extracted from the service or null if the service
	 *         description could not be read or the service matcher wants the
	 *         information to be discarded.
	 * @throws MatchmakerServiceRegistrationException
	 *             if there was a problem registering the new service.
	 */
	public ExtractedServiceInformation newServiceRegistered(
			ServiceImplementation impl) throws MatchmakerServiceRegistrationException;

	/**
	 * Informs the service matcher that a new ServiceRequriement has been
	 * registered so that it can extract the necessary information from the
	 * service description for use in later matching.
	 * 
	 * @param requirement
	 *            the service requirement which has been registered.
	 */
	public void newRequirementRegistered(final ServiceRequirement requirement);

	/**
	 * Informs the service matcher that a new service implementation has been
	 * registered from a serialised repository.
	 * 
	 * @param impl
	 *            the registered service implementation.
	 * @param info
	 *            the extracted service information from the serialised form.
	 * @throws IncompatibleServiceMatcherException
	 */
	public void newServiceRegistered(ServiceImplementation impl,
			ExtractedServiceInformation info)
			throws IncompatibleServiceMatcherException;

	/**
	 * Informs the matcher that a service has been unregistered and the
	 * information about it can be discarded.
	 * 
	 * @param impl
	 *            the service which has been unregistered.
	 */
	public void serviceUnregistered(ServiceImplementation impl);

	/**
	 * Determines whether the provided service profile matches the provided
	 * service description.
	 * 
	 * @param requirement
	 *            the service requirement to match.
	 * @return true if the profile matches the description, false otherwise.
	 */
	public Set<ServiceImplementation> findServiceMatches(
			ServiceRequirement requirement);
}
