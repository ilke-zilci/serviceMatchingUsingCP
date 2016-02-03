package uk.ac.ucl.cs.sse.dino.matchmaker;

import java.util.LinkedList;
import java.util.List;

import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Abstract implementation of the <tt>ServiceMatcher</tt> interface which
 * provides facilities for adding <tt>MatcherListener</tt>s and firing
 * events.
 */
public abstract class AbstractServiceMatcher implements ServiceMatcher {
	private List<MatcherListener> listenerList = new LinkedList<MatcherListener>();

	/**
	 * Registers a <tt>MatcherListener</tt> to this service matcher.
	 * @param l the lsitener to register.
	 */
	public void addMatcherListener(MatcherListener l) {
		listenerList.add(l);
	}

	/**
	 * Informs the lsiteners registered to this service matcher that some service amtches have been found.
	 * @param matchmaker a string identifing the matchmaker which performed the amtch.
	 * @param requirement the requriement for which one or more matches were found.
	 * @param matchTime the time taken to find the matches.
	 */
	public void fireFoundServiceMatches(String matchmaker,
			ServiceRequirement requirement, long matchTime) {
		for (MatcherListener l : listenerList) {
			l.foundServiceMatches(matchmaker, requirement, matchTime);
		}
	}

}
