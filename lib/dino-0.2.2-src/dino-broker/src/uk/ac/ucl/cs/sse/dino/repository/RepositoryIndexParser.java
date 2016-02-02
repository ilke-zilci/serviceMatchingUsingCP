package uk.ac.ucl.cs.sse.dino.repository;

import java.io.File;
import java.net.URI;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.matchmaker.ExtractedServiceInformation;
import uk.ac.ucl.cs.sse.dino.matchmaker.IncompatibleServiceMatcherException;
import uk.ac.ucl.cs.sse.dino.matchmaker.ServiceMatcher;

/**
 * PArses the repository index file to get the extracted service information
 * which can be used for matching.
 * 
 */
final class RepositoryIndexParser extends DefaultHandler {
	private GeneralServiceInformation generalInfo;
	private ExtractedServiceInformation specificInfo;
	private ServiceImplementation impl;

	private final ServiceMatcher matcher;
	private final File repositoryDirectory;

	/**
	 * Creates a repository index parser for the given repository which should
	 * contain matchmaking information for the given matcher.
	 * 
	 * @param matcher
	 *            a matchmaker which can be used to match using the extracted
	 *            service information stored in the repository.
	 * @param repositoryDirectory
	 *            the location of the repository on the file system.
	 */
	public RepositoryIndexParser(final ServiceMatcher matcher,
			final File repositoryDirectory) {
		this.matcher = matcher;
		this.repositoryDirectory = repositoryDirectory;
	}

	@Override
	public void startElement(final String uri, final String localName,
			final String qName, final Attributes attributes)
			throws SAXException {
		if (qName.equals("serviceInformation")) {
			specificInfo = matcher.createEmptyServiceInforamtion();
			generalInfo = new GeneralServiceInformation(specificInfo);
			generalInfo.startParentElement(attributes);

			URI serviceDescriptionURI = generalInfo.getServiceDescriptionURI();
			String serviceFileName = generalInfo.getFileName();
			File serviceFile = new File(repositoryDirectory, serviceFileName);

			String qosFileName = generalInfo.getQosFileName();
			if (qosFileName == null) {
				impl = new ServiceImplementation(serviceDescriptionURI,
						serviceFile);
			} else {
				File qosFile = new File(repositoryDirectory, qosFileName);
				try {
					impl = new ServiceImplementation(serviceDescriptionURI,
							qosFile.toURI(), serviceFile);
				} catch (DinoDocReadException e) {
					throw new SAXException("Problem reading Dino document.", e);
				}
			}
		} else if (generalInfo != null) {
			generalInfo.startChildElementInfo(qName, attributes);

		}
	}

	@Override
	public void endElement(final String uri, final String localName,
			final String qName) throws SAXException {
		try {
			if (qName.equals("serviceInformation")) {
				matcher.newServiceRegistered(impl, specificInfo);
				generalInfo.endParentElement();
				generalInfo = null;
				impl = null;
			} else if (generalInfo != null) {
				generalInfo.endChildElement(qName);
			}
		} catch (IncompatibleServiceMatcherException e) {
			throw new SAXException(
					"The service information repository tried to give the service matcher an incompatible object for extracted service information.",
					e);
		}
	}

}
