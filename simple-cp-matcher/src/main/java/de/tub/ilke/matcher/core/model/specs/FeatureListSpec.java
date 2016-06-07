package de.tub.ilke.matcher.core.model.specs;

import java.util.Arrays;

public class FeatureListSpec extends TresorQoSSpec {
	public int[] providedFeatureNumberCodes;

	public FeatureListSpec() {
	}

	public FeatureListSpec(int[] codes) {
		this.providedFeatureNumberCodes = codes;
	}

	public void setProvidedFeatureNumberCodes(int[] providedFeatureNumberCodes) {
		this.providedFeatureNumberCodes = providedFeatureNumberCodes;
	}

	public int[] getCodes() {
		return providedFeatureNumberCodes;
	}

	@Override
	public String toString() {
		String toreturn = "featurelistSpec "
				+ Arrays.toString(providedFeatureNumberCodes);
		toreturn += " ranking: " + ranking + " matching degree: "
				+ matchingDegree + "\n";
		return toreturn;

	}

}
