package de.tub.ilke.matcher.core.model;

public class QoSMatchmaker {
	public ServiceRepository repository;
	public QoSRequest request;

	public QoSMatchmaker() {
	}

	public QoSMatchmaker(ServiceRepository repo, QoSRequest req) {
		repository = repo;
		request = req;
	}

	public void solveMatchingProblem() throws ConstraintException {

		repository.populateLists(request);
	}
}
