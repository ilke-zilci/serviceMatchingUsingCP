package uk.ac.ucl.cs.sse.dino.experiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import uk.ac.ucl.cs.sse.dino.util.DinoIO;

/**
 * Copies functional descriptions into repository and generates dummy indexs
 * entries and non-functional QoS files for dummy services for testing
 * performance of broker matchmaking. The parameters of the QoS files are
 * generated semi-randomly.
 * 
 */
public class DocumentGenerator {
	private final static String owlsDir = ".." + File.separator
			+ "owls-services" + File.separator;

	private final static String repositoryDir = ".." + File.separator
			+ "repository" + File.separator;

	private final static File dataFile = new File(repositoryDir + "data.xml");

	private static PrintWriter dataWriter;

	private final static Random rand = new Random(4858365742219644574L);

	/**
	 * Creates the specified number of repository entries.
	 * 
	 * @param number the number of repository entries to create.
	 * @throws IOException if there is a problem reading or writing the files.
	 */
	static void generate(int number) throws IOException {
		// Copy other required files into repository
		DinoIO.copy(new File(owlsDir + "GPS.owl"), new File(repositoryDir
				+ "GPS.owl"));
		DinoIO.copy(new File(owlsDir + "RestaurantSearch.owl"), new File(
				repositoryDir + "RestaurantSearch.owl"));
		DinoIO.copy(new File(owlsDir + "data.xml"), new File(repositoryDir
				+ "data.xml"));

		dataWriter = new PrintWriter(new FileOutputStream(dataFile, true));

		for (int i = 0; i < number; i++) {
			if (i % 2 == 0) {
				writeMatchingFunctionalFile("CityLocator" + (i / 2) + ".owl");
				if (i % 4 == 0) {
					writeMatchingQosFile("CityLocator" + (i / 2) + ".qos");
				} else {
					writeNonMatchingQosFile("CityLocator" + (i / 2) + ".qos");
				}
			} else {
				writeNonMatchingFunctionalFile("AmazonBookSearch" + i + ".owl");
			}
		}

		dataWriter.close();
	}

	private static void writeMatchingFunctionalFile(String name)
			throws IOException {
		File f = new File(repositoryDir + name);
		File source = new File(owlsDir + "CityLocator.owl");

		DinoIO.copy(source, f);

		dataWriter.println("<serviceInformation fileName='" + name + "'");
		dataWriter.println("qosFileName='"
				+ name.substring(0, name.length() - 4) + ".qos'");
		dataWriter
				.println("uri='file:/C:/Documents%20and%20Settings/andy/My%20Documents/sensoria/dino/broker/owls-services/"
						+ name + "'");
		dataWriter
				.println("profileURI='http://www.mindswap.org/2004/owl-s/1.1/MindswapProfileHierarchy.owl#MapService'>");
		dataWriter
				.println("<superType uri='http://www.mindswap.org/2004/owl-s/1.1/MindswapProfileHierarchy.owl#WebService'/>");
		dataWriter
				.println("<superType uri='http://www.daml.org/services/owl-s/1.1/Service.owl#ServiceProfile'/>");
		dataWriter
				.println("<superType uri='http://www.w3.org/2002/07/owl#Thing'/>");
		dataWriter
				.println("<superType uri='http://www.daml.org/services/owl-s/1.1/Profile.owl#Profile'/>");
		dataWriter
				.println("<inputParameter uri='http://www.cs.ucl.ac.uk/staff/a.dingwall-smith/geography.owl#LatLon'>");
		dataWriter
				.println("<superType uri='http://www.w3.org/2002/07/owl#Thing'/>");
		dataWriter.println("</inputParameter>");
		dataWriter
				.println("<outputParameter uri='http://www.cs.ucl.ac.uk/staff/a.dingwall-smith/geography.owl#City'>");
		dataWriter
				.println("<superType uri='http://www.w3.org/2002/07/owl#Thing'/>");
		dataWriter.println("</outputParameter>");
		dataWriter.println("</serviceInformation>");
		dataWriter.println();
	}

	private static void writeNonMatchingFunctionalFile(String name)
			throws IOException {
		File f = new File(repositoryDir + name);
		File source = new File(owlsDir + "AmazonBookSearch.owl");

		DinoIO.copy(source, f);

		dataWriter.println("<serviceInformation fileName='" + name + "'");
		dataWriter
				.println("            uri='file:/C:/Documents%20and%20Settings/andy/My%20Documents/sensoria/dino/broker/owls-services/"
						+ name + "'");
		dataWriter
				.println("profileURI='http://www.mindswap.org/2004/owl-s/1.1/MindswapProfileHierarchy.owl#BookInformationService'>");
		dataWriter
				.println("<superType uri='http://www.mindswap.org/2004/owl-s/1.1/MindswapProfileHierarchy.owl#InformationService'/>");
		dataWriter
				.println("<superType uri='http://www.mindswap.org/2004/owl-s/1.1/MindswapProfileHierarchy.owl#WebService'/>");
		dataWriter
				.println("<superType uri='http://www.daml.org/services/owl-s/1.1/Service.owl#ServiceProfile'/>");
		dataWriter
				.println("<superType uri='http://www.w3.org/2002/07/owl#Thing'/>");
		dataWriter
				.println("<superType uri='http://www.daml.org/services/owl-s/1.1/Profile.owl#Profile'/>");
		dataWriter
				.println("<inputParameter uri='http://www.w3.org/2001/XMLSchema#string'>");
		dataWriter.println("</inputParameter>");
		dataWriter.println("<inputParameter uri='#Book'>");
		dataWriter
				.println("<superType uri='http://www.w3.org/2002/07/owl#Thing'/>");
		dataWriter.println("</inputParameter>");
		dataWriter
				.println("<outputParameter uri='http://purl.org/net/nknouf/ns/bibtex#Book'>");
		dataWriter
				.println("<superType uri='http://www.w3.org/2002/07/owl#Thing'/>");
		dataWriter
				.println("<superType uri='http://purl.org/net/nknouf/ns/bibtex#Entry'/>");
		dataWriter.println("</outputParameter>");
		dataWriter
				.println("<outputParameter uri='http://www.w3.org/2001/XMLSchema#string'>");
		dataWriter.println("</outputParameter>");
		dataWriter.println("</serviceInformation>");
		dataWriter.println();
	}

	private static void writeMatchingQosFile(String name)
			throws FileNotFoundException {
		File f = new File(repositoryDir + name);

		PrintWriter w = new PrintWriter(f);
		w.println("<QosDoc>");
		w.println("    <and>");

		int min = 2 + rand.nextInt(9);
		int max = min + rand.nextInt(10);
		double conf = 0.6 + 0.1 * rand.nextInt(4);
		w.println("        <qos name='resolution' minVal='" + min
				+ "' maxVal='" + max + "' confidence='" + conf + "'/>");

		max = 10000 - 100 * rand.nextInt(80);
		min = max - 100 * rand.nextInt(max / 100);
		conf = 0.9 + 0.01 * rand.nextInt(10);
		w.println("        <qos name='responseTime' minVal='" + min
				+ "' maxVal='" + max + "' confidence='" + conf + "'/>");

		max = 5000 - 100 * rand.nextInt(40);
		min = max - 100 * rand.nextInt(max / 100);
		conf = 0.95 + 0.01 * rand.nextInt(5);
		w.println("        <qos name='requestFrequency' minVal='" + min
				+ "' maxVal='" + max + "' confidence='" + conf + "'/>");

		min = 90 + rand.nextInt(10);
		max = min + rand.nextInt(100 - min);
		w.println("        <qos name='availability' minVal='" + min
				+ "' maxVal='" + max + "'/>");

		final String[] enums = { "Global", "Europe",
				"North America, Europe, Asia", "UK",
				"France, Germany, Poland, UK, Spain, Ireland" };

		int index = rand.nextInt(enums.length);

		w.println("        <qos name='coverage' enum='" + enums[index] + "'/>");
		w.println("    </and>");
		w.println("</QosDoc>");
		w.close();
	}

	private static void writeNonMatchingQosFile(String name)
			throws FileNotFoundException {
		File f = new File(repositoryDir + name);

		PrintWriter w = new PrintWriter(f);
		w.println("<QosDoc>");
		w.println("    <and>");

		int min = 2 + rand.nextInt(9);
		int max = min + rand.nextInt(10);
		double conf = 0.6 + 0.1 * rand.nextInt(4);
		w.println("        <qos name='resolution' minVal='" + min
				+ "' maxVal='" + max + "' confidence='" + conf + "'/>");

		max = 10000 - 100 * rand.nextInt(80);
		min = max - 100 * rand.nextInt(max / 100);
		conf = 0.9 + 0.01 * rand.nextInt(10);
		w.println("        <qos name='responseTime' minVal='" + min
				+ "' maxVal='" + max + "' confidence='" + conf + "'/>");

		max = 5000 - 100 * rand.nextInt(40);
		min = max - 100 * rand.nextInt(max / 100);
		conf = 0.95 + 0.01 * rand.nextInt(5);
		w.println("        <qos name='requestFrequency' minVal='" + min
				+ "' maxVal='" + max + "' confidence='" + conf + "'/>");

		min = 90 + rand.nextInt(10);
		max = min + rand.nextInt(100 - min);
		w.println("        <qos name='availability' minVal='" + min
				+ "' maxVal='" + max + "'/>");

		final String[] enums = { "Asia", "North America, Asia", "France",
				"France, Germany, Poland, Spain, Ireland" };

		int index = rand.nextInt(enums.length);

		w.println("        <qos name='coverage' enum='" + enums[index] + "'/>");
		w.println("    </and>");
		w.println("</QosDoc>");
		w.close();
	}
}
