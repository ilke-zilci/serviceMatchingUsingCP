package uk.ac.ucl.cs.sse.dino.matchmaker.owls;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.doc.qos.CapDocQosParameter;
import uk.ac.ucl.cs.sse.dino.doc.qos.ReqDocQosParameter;
import uk.ac.ucl.cs.sse.dino.matchmaker.AbstractServiceMatcher;
import uk.ac.ucl.cs.sse.dino.matchmaker.ExtractedServiceInformation;
import uk.ac.ucl.cs.sse.dino.matchmaker.IncompatibleServiceMatcherException;
import uk.ac.ucl.cs.sse.dino.matchmaker.MatchmakerServiceRegistrationException;
import uk.ac.ucl.cs.sse.dino.matchmaker.ServiceMatcher;

/**
 * Matcher which matches Dino quality of service specifications in RqDocs with
 * those in CapDocs. This matchmaker requires another matcher to perform
 * matchmaking on the functional specification.
 * 
 */
public class OwlsQosMatchmaker extends AbstractServiceMatcher {
	private ServiceMatcher functionalMatcher;

	/**
	 * Creates the quality of service matcher, using the specified matcher to
	 * match functional specifications.
	 * 
	 * @param simpleMatcher
	 *            the matcher for functional specifications.
	 */
	public OwlsQosMatchmaker(ServiceMatcher simpleMatcher) {
		this.functionalMatcher = simpleMatcher;
	}

	public ExtractedServiceInformation createEmptyServiceInforamtion() {
		return functionalMatcher.createEmptyServiceInforamtion();
	}

	public void newRequirementRegistered(final ServiceRequirement requirement) {
		functionalMatcher.newRequirementRegistered(requirement);
	}

	public void newServiceRegistered(ServiceImplementation impl,
			ExtractedServiceInformation info)
			throws IncompatibleServiceMatcherException {
		functionalMatcher.newServiceRegistered(impl, info);
	}

	public ExtractedServiceInformation newServiceRegistered(
			ServiceImplementation impl) throws MatchmakerServiceRegistrationException {
		return functionalMatcher.newServiceRegistered(impl);
	}

	public void serviceUnregistered(ServiceImplementation impl) {
		functionalMatcher.serviceUnregistered(impl);
	}

	public Set<ServiceImplementation> findServiceMatches(
			ServiceRequirement requirement) {
		long startTime = System.nanoTime();
		Set<ServiceImplementation> matchSet = functionalMatcher
				.findServiceMatches(requirement);
		for (Iterator<ServiceImplementation> i = matchSet.iterator(); i
				.hasNext();) {
			ServiceImplementation impl = i.next();
			if (!qosMatch(requirement, impl)) {
				i.remove();
			}
		}
		
		fireFoundServiceMatches("functional matcher", requirement, System.nanoTime() - startTime);
		return matchSet;
	}

	private boolean qosMatch(ServiceRequirement requirement,
			ServiceImplementation impl) {
		Collection<ReqDocQosParameter> qosReqs = requirement
				.getEssentialQosRequirements();
		if (qosReqs == null) {
			return true;
		}

		Collection<CapDocQosParameter> qosCaps = impl
				.getEssentialQosCapabilities();
		if (qosCaps == null) {
			return false;
		}

		for (ReqDocQosParameter qosReq : qosReqs) {
			boolean matched = false;
			for (CapDocQosParameter qosCap : qosCaps) {
				if (qosReq.getName().equals(qosCap.getName())) {
					if (qosMatch(qosReq, qosCap)) {
						matched = true;
						break;
					} else {
						return false;
					}
				}
			}

			if (!matched) {
				return false;
			}
		}

		return true;
	}

	private boolean qosMatch(ReqDocQosParameter req, CapDocQosParameter cap) {
		if (cap.getConfidence() < req.getConfidence()) {
			return false;
		}

		if (!minMaxMatch(req, cap)) {
			return false;
		}

		if (!enumsMatch(req.getEnumValues(), cap.getEnumValues())) {
			return false;
		}

		return true;
	}

	private boolean minMaxMatch(ReqDocQosParameter req, CapDocQosParameter cap) {
		double lpVal = req.getLpValue();
		double mpVal = req.getMpValue();

		if (Double.isNaN(lpVal) || Double.isNaN(mpVal)) {
			return true;
		}

		// Determne whether the attribute needs to be maximised or minimised
		// based on whether lpVal is greater or less then mpVal.
		boolean maximise;
		if (lpVal < mpVal) {
			maximise = true;
		} else {
			maximise = false;
		}

		// Check that the service satisfies at least the lpVal for the
		// attribtue.
		double maxProvided = cap.getMaxValue();
		double minProvided = cap.getMinValue();

		if (Double.isNaN(minProvided)) {
			return false;
		}
		if (Double.isNaN(maxProvided)) {
			return false;
		}

		if (maximise) {
			if (minProvided < lpVal) {
				return false;
			}
			if (maxProvided > mpVal) {
				return false;
			}
		} else {
			if (maxProvided > lpVal) {
				return false;
			}
			if (minProvided < mpVal) {
				return false;
			}
		}

		return true;
	}

	private boolean enumsMatch(String[] requiredEnum, String[] providedEnum) {
		if (requiredEnum == null) {
			return true;
		} else if (providedEnum == null) {
			return false;
		}

		for (String requiredVal : requiredEnum) {
			for (String providedVal : providedEnum) {
				if (requiredVal.equals(providedVal)) {
					return true;
				}
			}
		}

		return false;
	}
}
