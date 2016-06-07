package de.tub.ilke.matcher.core.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceRepository {
	public List<ServiceDescription> serviceDescriptions;
	public List<ServiceDescription> superMatches;
	public List<ServiceDescription> exactMatches;
	public List<ServiceDescription> partialMatches;
	public List<ServiceDescription> fail;
	/**
	 * The logger of the class.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	public void registerServiceDescription(ServiceDescription e) {
		serviceDescriptions.add(e);
	}

	// for now it only assigns matching degree to each qosspec in service
	// descriptions
	public void populateLists(QoSRequest request) throws ConstraintException {
		request.evaluate(serviceDescriptions);
		request.evaluateMatrix(serviceDescriptions);
		LOGGER.debug("Evaluated Service Descriptions: \n");
		for (ServiceDescription sd : serviceDescriptions) {
			LOGGER.debug(sd.toString());
		}

	}
}
