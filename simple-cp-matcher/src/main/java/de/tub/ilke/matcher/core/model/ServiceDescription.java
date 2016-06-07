package de.tub.ilke.matcher.core.model;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tub.ilke.matcher.core.model.specs.QoSSpec;

public class ServiceDescription {
	/**
	 * The logger of the class.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private Map<String, QoSSpec> specification;

	private int ranking;

	public int calculateRanking() {
		ranking = 0;
		for (QoSSpec qosSpec : specification.values()) {
			ranking += qosSpec.getRanking();
		}
		return ranking;
	}

	public Map<String, QoSSpec> getSpecification() {
		return specification;
	}

	public void setSpecification(Map<String, QoSSpec> specification) {
		this.specification = specification;
	}

	@Override
	public String toString() {
		String toreturn = "";

		for (Entry<String, QoSSpec> entry : specification.entrySet()) {
			toreturn += " property name: " + entry.getKey()
					+ ", property itself: " + entry.getValue().toString()
					+ " ;";
		}
		toreturn += "scores: " + calculateRanking();
		return toreturn;
	}

	private void compareTo() {
		// TODO based on ranking

	}
}
