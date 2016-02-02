package uk.ac.ucl.cs.sse.dino.matchmaker.owls;

import java.io.PrintWriter;
import java.net.URI;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.mindswap.owl.OWLClass;
import org.mindswap.owls.process.Parameter;
import org.mindswap.owls.process.ParameterList;
import org.mindswap.owls.profile.Profile;
import org.mindswap.owls.service.Service;
import org.xml.sax.Attributes;

import uk.ac.ucl.cs.sse.dino.matchmaker.ExtractedServiceInformation;

/**
 * Represents information about a service which has been extracted from an OWL-S
 * service description. The reason for extracting information is that performing
 * reasoning on OWL-S documents can be very time consuming so it is necessary to
 * do this reasoning once for a given document and then store the important
 * results which can be used for service matching.
 * 
 */
public class OwlsExtractedServiceInformation implements ExtractedServiceInformation {
	private URI profileURI;
	private List<ParameterData> inputs = new LinkedList<ParameterData>();
	private List<ParameterData> outputs = new LinkedList<ParameterData>();

	private Set<URI> superClasses = new HashSet<URI>();
	private Set<URI> subClasses = new HashSet<URI>();

	private ParameterData currentParameter = null;

	/**
	 * Creates an <tt>OwlsExtractedServiceInformation</tt> object for the
	 * given service implementation URI.
	 * 
	 * @param serviceDescriptionURI
	 *            URI of the service implementation.
	 */
	OwlsExtractedServiceInformation() {
	}

	/**
	 * Creates an <tt>OwlsExtractedServiceInformation</tt> object for the
	 * given service implementation URI and the given <tt>Service</tt> object
	 * from the Mindswap OWL-S API.
	 * 
	 * @param serviceDescriptionURI
	 *            URI of the service implementation.
	 * @param service Mindswap OWL-S API representation of the service.
	 */
	OwlsExtractedServiceInformation(final Service service) {
		Profile profile = service.getProfile();
		OWLClass profileClass = profile.getType();
		profileURI = profileClass.getURI();

		Set superC = profileClass.getSuperClasses();
		for (Object o : superC) {
			OWLClass c = (OWLClass) o;
			URI uri = c.getURI();
			if (uri != null) {
				superClasses.add(uri);
			}
		}

		Set subC = profileClass.getSubClasses();
		for (Object o : subC) {
			OWLClass c = (OWLClass) o;
			URI uri = c.getURI();
			if (uri != null) {
				subClasses.add(uri);
			}
		}

		ParameterList inputList = profile.getInputs();
		for (Object o : inputList) {
			inputs.add(new ParameterData((Parameter) o));
		}

		ParameterList outputList = profile.getOutputs();
		for (Object o : outputList) {
			outputs.add(new ParameterData((Parameter) o));
		}
	}

	public void startParentElement(final Attributes attributes) {
		profileURI = URI.create(attributes.getValue("profileURI"));
	}

	public void startChildElementInfo(final String name, final Attributes attributes) {
		URI uri = URI.create(attributes.getValue("uri"));

		if (currentParameter == null) {
			if (name.equals("superType")) {
				superClasses.add(uri);
			} else if (name.equals("subType")) {
				subClasses.add(uri);
			} else if (name.equals("inputParameter")) {
				currentParameter = new ParameterData(uri);
				inputs.add(currentParameter);
			} else if (name.equals("outputParameter")) {
				currentParameter = new ParameterData(uri);
				outputs.add(currentParameter);
			}
		} else {
			if (name.equals("superType")) {
				currentParameter.addSuperClass(uri);
			} else if (name.equals("subType")) {
				currentParameter.addSubClass(uri);
			}
		}
	}

	public void endParentElement() {
		currentParameter = null;
	}

	public void endChildElement(final String name) {
		currentParameter = null;
	}

	public void writeInformation(final PrintWriter writer) {
		writer.println("                    profileURI=\"" + profileURI + "\">");
		
		for (URI uri : superClasses) {
			if (!uri.equals(profileURI)) {
				writer.println("    <superType uri=\"" + uri + "\"/>");
			}
		}

		for (URI uri : subClasses) {
			if (!uri.equals(profileURI)) {
				writer.println("    <subType uri=\"" + uri + "\"/>");
			}
		}

		for (ParameterData data : inputs) {
			writer.println("    <inputParameter uri=\"" + data.getParameterURI() + "\">");
			data.writeInformation(writer);
			writer.println("    </inputParameter>");
		}

		for (ParameterData data : outputs) {
			writer.println("    <outputParameter uri=\"" + data.getParameterURI() + "\">");
			data.writeInformation(writer);
			writer.println("    </outputParameter>");
		}
	}

	/**
	 * Determines whether this profile type is a sub-class of the provided
	 * profile type URI.
	 * 
	 * @param uri
	 *            a profile type URI.
	 * @return true if the profile type of this service is a sub-class of the
	 *         provided type, false otherwise.
	 */
	public boolean isSubClassOf(final URI uri) {
		if (profileURI.equals(uri)) {
			return true;
		}

		return superClasses.contains(uri);
	}

	/**
	 * Determines whether this profile type is a super-class of the provided
	 * profile type URI.
	 * 
	 * @param uri
	 *            a profile type URI.
	 * @return true if the profile type of this service is a super-class of the
	 *         provided type, false otherwise.
	 */
	public boolean isSuperClassOf(final URI uri) {
		if (profileURI.equals(uri)) {
			return true;
		}

		return subClasses.contains(uri);
	}

	/**
	 * Gets the input parameters to the service.
	 * 
	 * @return a list of input parameter data.
	 */
	public List<ParameterData> getInputs() {
		return inputs;
	}

	/**
	 * Gets the output parameters to the service.
	 * 
	 * @return a list of output parameter data.
	 */
	public List<ParameterData> getOutputs() {
		return outputs;
	}

	/**
	 * Gets the URI of the service profile type.
	 * 
	 * @return the service profile type URI.
	 */
	public URI getProfileURI() {
		return profileURI;
	}
}
