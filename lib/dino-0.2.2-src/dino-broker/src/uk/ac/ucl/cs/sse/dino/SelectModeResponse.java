package uk.ac.ucl.cs.sse.dino;

import java.io.Serializable;

/**
 * Provides information about the mode which has been selected by Dino Broker in
 * response to a selectMode request.
 */
public final class SelectModeResponse implements Serializable {
	private static final long serialVersionUID = 2L;

	private String selectedMode;

	/**
	 * Creates an empty response.
	 */
	public SelectModeResponse() {
	}
	
	/**
	 * Creates a response with the given selected mode.
	 * @param selectedMode the mode selected.
	 */
	public SelectModeResponse(final String selectedMode) {
		this.selectedMode = selectedMode;
	}

	/**
	 * Gets the name of the mdoe which was selected.
	 * @return the name of the selected mode.
	 */
	public String getSelectedMode() {
		return selectedMode;
	}

	/**
	 * Sets the name of the selected mode.
	 * @param selectedMode the selected mode.
	 */
	public void setSelectedMode(final String selectedMode) {
		this.selectedMode = selectedMode;
	}

}
