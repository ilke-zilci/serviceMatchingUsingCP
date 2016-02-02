package uk.ac.ucl.cs.sse.dino.experiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.broker.ActionIdentifier;
import uk.ac.ucl.cs.sse.dino.broker.DinoBrokerListener;
import uk.ac.ucl.cs.sse.dino.broker.DinoBrokerSession;
import uk.ac.ucl.cs.sse.dino.doc.DocType;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.matchmaker.MatcherListener;
import uk.ac.ucl.cs.sse.dino.selector.ServiceSelectionListener;

/**
 * Logs data from the DinoBroker.
 * 
 */
public class DataLogger implements ServiceSelectionListener,
		DinoBrokerListener, MatcherListener {
	private PrintWriter w;

	private ServiceRequirement lastRequirement;

	/**
	 * Creates a new data logger which logs to the provided file.
	 * 
	 * @param outputFile
	 *            the file to write data to.
	 * @param number
	 *            the number of fiels in the repository, which is logged at the
	 *            start of the file.
	 * @throws FileNotFoundException
	 */
	public DataLogger(final File outputFile, final int number)
			throws FileNotFoundException {
		w = new PrintWriter(new FileOutputStream(outputFile, true));
		w.println(number);
	}

	public void serviceSelectionComplete(ServiceRequirement requirement,
			List<ServiceImplementation> orderedImplementations, long selectionTime) {
		if ("latlong-to-city".equals(lastRequirement.getName())) {
			w.println(selectionTime);
		}
	}

	public void serviceSelectionStarted(ServiceRequirement requirement,
			String selectionMethod) {
		lastRequirement = requirement;
	}

	public void implementationEvaluated(ServiceRequirement requirement,
			ServiceImplementation impl, String info) {
	}

	public void invokeServiceEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String invokedService,
			Param[] outputParameters) {
	}

	public void invokeServiceEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String errorMsg) {
	}

	public void invokeServiceStarted(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String serviceName, Param[] params) {
	}

	public void registerDocEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session) {
	}

	public void registerDocStarted(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String docURL, DocType docType) {
	}

	public void selectModeEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String selectedMode) {
	}

	public void selectModeStarted(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String[] requestedModes) {
	}

	public void sessionQuit(DinoBrokerSession session) {
		w.println();
		w.close();
	}

	public void sessionStarted(DinoBrokerSession session) {
	}

	public void foundServiceMatches(String matchmaker,
			ServiceRequirement requirement, long matchTime) {
		if ("latlong-to-city".equals(requirement.getName())) {
			w.print(matchTime + ", ");
		}
	}

}
