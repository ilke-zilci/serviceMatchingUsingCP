package uk.ac.ucl.cs.sse.dino.matchmaker;

import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Listener interface which is informed when a matchmaker finds one or moree matches for a requirement.
 * @see uk.ac.ucl.cs.sse.dino.matchmaker.AbstractServiceMatcher
 */
public interface MatcherListener {
	/**
	 * Method which is called to indicate that matches have been found for a requirement.
	 * @param matchmaker a string identifing the matchmaker which performed the amtch.
	 * @param requirement the requriement for which one or more matches were found.
	 * @param matchTime the time taken to find the matches.
	 */
	public void foundServiceMatches(String matchmaker,
			ServiceRequirement requirement, long matchTime);

}
