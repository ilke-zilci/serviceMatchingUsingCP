package uk.ac.ucl.cs.sse.dino.broker;

import java.util.List;

import uk.ac.ucl.cs.sse.dino.InvocationResponse;
import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationEngine;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;
import uk.ac.ucl.cs.sse.dino.repository.ServiceInformationRepository;

/**
 * Stores the services selected to satisfy a requirement. The services which
 * satisfy a particular requirement are ordered according to their quality of
 * service.
 * 
 */
class SelectedServicesList {
	private List<ServiceImplementation> selectedServices;

	/**
	 * Creates a list of services which satisfy the provided requirement,
	 * ordered according to their quality of service characteristics.
	 * 
	 * @param repository
	 *            the repository from which services should be selected.
	 * @param requirement
	 *            the service requirement for which implementations should be
	 *            listed.
	 */
	SelectedServicesList(final ServiceInformationRepository repository,
			final ServiceRequirement requirement) {
		selectedServices = repository
				.getMatchingOrderedImplementations(requirement);
	}

	/**
	 * Invokes the best service from this list. If this service is unavailable
	 * then the next best service is tried and so on until a service is invoked
	 * successfully or there are no services left.
	 * 
	 * @param invocationEngine
	 *            engine used to invoke service (e.g. an OWL-S invocation
	 *            engine).
	 * 
	 * @param params
	 *            the input parameters with which the service should be invoked.
	 * @return the response from invoking the service or null if no service
	 *         could be invoked.
	 * @throws ServiceInvocationException
	 *             if there was a problem invoking the service.
	 */
	InvocationResponse invokeBestService(
			final ServiceInvocationEngine invocationEngine, final Param[] params)
			throws ServiceInvocationException {
		assert invocationEngine != null;
		assert params != null;

		for (ServiceImplementation impl : selectedServices) {
			Param[] outputParameters = invocationEngine.invokeService(impl,
					params);
			if (outputParameters != null) {
				return new InvocationResponse(outputParameters, impl
						.getServiceDescriptionURI().toString());
			}

		}
		return null;
	}
}
