package uk.ac.ucl.cs.sse.dino.matchmaker.owls;

import java.io.PrintWriter;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.mindswap.owl.OWLClass;
import org.mindswap.owl.OWLType;
import org.mindswap.owls.process.Parameter;

/**
 * Stores information about a OWL-S parameter.
 * 
 */
class ParameterData {
	private URI parameterURI;

	private Set<URI> superClasses = new HashSet<URI>();
	private Set<URI> subClasses = new HashSet<URI>();

	/**
	 * Creates a new parameter data object with the given URI.
	 * 
	 * @param parameterURI
	 *            the URI of the parameter type.
	 */
	ParameterData(URI parameterURI) {
		this.parameterURI = parameterURI;
	}

	/**
	 * Creates a new parameter data object from a <tt>Parameter</tt> object
	 * from the Mindswap OWL-S API.
	 * 
	 * @param p
	 *            a Mindswap OWL-S API parameter.
	 */
	ParameterData(Parameter p) {
		OWLType paramType = p.getParamType();

		parameterURI = paramType.getURI();

		if (paramType instanceof OWLClass) {
			OWLClass c = (OWLClass) paramType;

			Set superC = c.getSuperClasses();
			for (Object so : superC) {
				OWLClass sc = (OWLClass) so;
				URI uri = sc.getURI();
				if (uri != null) {
					superClasses.add(uri);
				}
			}

			Set subC = c.getSubClasses();
			for (Object so : subC) {
				OWLClass sc = (OWLClass) so;
				URI uri = sc.getURI();
				if (uri != null) {
					subClasses.add(uri);
				}
			}
		}
	}

	/**
	 * Adds the URI of a super class of this parameter.
	 * @param uri the URI to add.
	 */
	void addSuperClass(URI uri) {
		superClasses.add(uri);
	}

	/**
	 * Adds the URI of a sub-class of this parameter.
	 * @param uri the URI to add.
	 */
	void addSubClass(URI uri) {
		subClasses.add(uri);
	}

	/**
	 * Writes the information stored in this class as an XML fragment to the given writer.
	 * @param writer used to write the XML.
	 */
	void writeInformation(PrintWriter writer) {
		for (URI uri : superClasses) {
			if (!uri.equals(parameterURI)) {
				writer.println("        <superType uri=\"" + uri + "\"/>");
			}
		}

		for (URI uri : subClasses) {
			if (!uri.equals(parameterURI)) {
				writer.println("        <subType uri=\"" + uri + "\"/>");
			}
		}
	}

	/**
	 * Determines if this parameter type is a sub-class of the specified parameter.
	 * @param p the parameter against which to test.
	 * @return true if this parameter is a sub-class of the provided parameter, false otherwise.
	 */
	boolean isSubClassOf(ParameterData p) {
		URI uri = p.parameterURI;
		if (parameterURI.equals(uri)) {
			return true;
		}

		return superClasses.contains(uri);
	}

	/**
	 * Determines if this parameter type is a super-class of the specified parameter.
	 * @param p the parameter against which to test.
	 * @return true if this parameter is a super-class of the provided parameter, false otherwise.
	 */
	boolean isSuperClassOf(ParameterData p) {
		URI uri = p.parameterURI;
		if (parameterURI.equals(uri)) {
			return true;
		}

		return subClasses.contains(uri);
	}

	/**
	 * Gets the URI of this parameter.
	 * @return a URI representing the type of this parameter.
	 */
	URI getParameterURI() {
		return parameterURI;
	}
}
