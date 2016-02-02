package uk.ac.ucl.cs.sse.dino.selector.appval;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.doc.qos.CapDocQosParameter;
import uk.ac.ucl.cs.sse.dino.doc.qos.QosDoc;
import uk.ac.ucl.cs.sse.dino.doc.qos.ReqDocQosParameter;
import uk.ac.ucl.cs.sse.dino.selector.AbstractServiceSelector;

/**
 * Selects the best service implementation by calculating an appropriateness value
 * based on how much the QoS provided exceeds the Qos requested.
 * 
 */
public class AppValServiceSelector extends AbstractServiceSelector {
	
	//Used to sort a list of service implementations according to appropriateness value.
	//Each list entry associates a service implementation with its corresponding
	//appropriateness value. The <tt>compareTo</tt> method sorts the list entries
	//according to the appropriateness value from highest to lowest.
	private static final class AppValListEntry implements
			Comparable<AppValListEntry> {
		private final double appVal;
		private final ServiceImplementation impl;

		private AppValListEntry(final double appVal,
				final ServiceImplementation impl) {
			this.appVal = appVal;
			this.impl = impl;
		}

		public int compareTo(AppValListEntry arg0) {
			return -Double.compare(appVal, arg0.appVal);
		}
	}

	/**
	 * Creates a selector.
	 */
	public AppValServiceSelector() {
	}

	public List<ServiceImplementation> orderServiceImplementations(
			final ServiceRequirement requirement,
			final Collection<ServiceImplementation> impls) {
		long startTime = System.nanoTime();

		if (impls == null) {
			throw new NullPointerException("Null parameter: impls");
		}

		fireServiceSelectionStarted(requirement, "AppropiatnessValueSelector");

		
		//This list associates each service implementation with its appropriateness value. 
		List<AppValListEntry> appValList = new ArrayList<AppValListEntry>(impls
				.size());

		//Calculate the appropriateness value for each service implementation and the
		//service implementation and appropriateness value to the list.
		for (ServiceImplementation impl : impls) {
			double appropiatnessValue = calculateBenefitValue(requirement, impl);
			// + TrustRating

			fireImplementationEvaluated(requirement, impl, String
					.valueOf(appropiatnessValue));

			appValList.add(new AppValListEntry(appropiatnessValue, impl));
		}
		
		
		//sort the list according to appropriateness value.
		Collections.sort(appValList);
		
		//Generate the sorted list of service implementations (without appropriateness values).
		List<ServiceImplementation> sortedImpls = new ArrayList<ServiceImplementation>();
		for (AppValListEntry listEntry : appValList) {
			sortedImpls.add(listEntry.impl);
		}
		
			
		fireServiceSelectionComplete(requirement, sortedImpls, System.nanoTime()
				- startTime);
		
		return sortedImpls;
	}

	private double calculateBenefitValue(ServiceRequirement requirement,
			ServiceImplementation impl) {
		double benefitValue = 0.0d;
		QosDoc providedQosDoc = impl.getQosDoc();

		Collection<ReqDocQosParameter> requiredParameters = requirement
				.getEssentialQosRequirements();
		for (ReqDocQosParameter requiredParameter : requiredParameters) {
			double parameterValue = 0.0d;
			CapDocQosParameter providedParameter = providedQosDoc
					.getEssentialCapability(requiredParameter.getName());

			if (providedParameter != null) {
				if (requiredParameter.getEnumValues() == null) {
					parameterValue = calculateNumericParameterValue(
							requiredParameter, providedParameter);
				} else {
					parameterValue = calculateEnumeratedParameterValue(
							requiredParameter, providedParameter);
				}

				benefitValue += parameterValue
						* providedParameter.getConfidence()
						* requiredParameter.getPriority();
			}
		}

		if (requiredParameters.size() != 0) {
			benefitValue /= requiredParameters.size();
		} else {
			benefitValue = 0.0d;
		}

		assert !Double.isNaN(benefitValue);
		return benefitValue;
	}

	private double calculateNumericParameterValue(
			ReqDocQosParameter requiredParameter,
			CapDocQosParameter providedParameter) {
		double lpVal = requiredParameter.getLpValue();
		double mpVal = requiredParameter.getMpValue();

		double maxVal = providedParameter.getMaxValue();
		double minVal = providedParameter.getMinValue();

		double parameterVal = 0.0d;

		if (mpVal > lpVal) {
			if (maxVal > mpVal) {
				parameterVal = 1.0d;
			} else {
				parameterVal = 1 - ((mpVal - minVal) / (mpVal - lpVal));
			}
		} else {
			if (minVal < mpVal) {
				parameterVal = 1.0d;
			} else {
				parameterVal = 1 - ((maxVal - mpVal) / (lpVal - mpVal));
			}
		}

		assert !Double.isNaN(parameterVal);
		return parameterVal;
	}

	private double calculateEnumeratedParameterValue(
			ReqDocQosParameter requiredParameter,
			CapDocQosParameter providedParameter) {
		String[] requiredValues = requiredParameter.getEnumValues();
		String[] providedValues = providedParameter.getEnumValues();

		double parameterVal = 0.0d;

		if (providedValues != null) {
			outer: for (int i = 0; i < requiredValues.length; i++) {
				for (String providedValue : providedValues) {
					if (requiredValues[i].equals(providedValue)) {
						parameterVal = 1 - i / (requiredValues.length - 1);
						break outer;
					}
				}
			}
		}

		assert !Double.isNaN(parameterVal);
		return 0.0d;
	}

}
