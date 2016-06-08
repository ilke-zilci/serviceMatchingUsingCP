package de.tub.ilke.matcher.wikimetadata;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.jsefa.Deserializer;
import org.jsefa.csv.CsvIOFactory;


// http://ostermiller.org/utils/javadoc/LabeledCSVParser.html
public class CsvParser {

//	public void parseCsvFileManual(InputStream file) throws IOException {
//		LabeledCSVParser parser = new LabeledCSVParser(new CSVParser(
//
//		new InputStreamReader(file), ';'));
//
//		String[] csvLabels = parser.getLabels();
//		System.out.println("labels");
//		for (String label : csvLabels) {
//
//			System.out.println(label.toString());
//
//		}
//
//		String[][] allValues = parser.getAllValues();
//		System.out.println("there are " + allValues.length + " values and "
//				+ csvLabels.length + " labels");
//		List<WikiMetadata> wikimatrix = new ArrayList<WikiMetadata>();
//		// instantiate wikimatrix objects as you iterate over the file lines
//
//		for (int i = 0; i < 10; i++) {
//			System.out
//					.println("################################entry beginning#######################################");
//			WikiMetadata wikimetadata = new WikiMetadata();
//			for (int j = 0; j < csvLabels.length; j++) {
//				// thousand wikimetadata set... calls? like
//				// wikimetadata.setVersion(parser.getValueByLabel(csvLabels[j]));
//				System.out.println(allValues[i][j]);
//			}
//		}
//		for (String label : parser.getLabels()) {
//			String value = parser.getValueByLabel(label);
//			System.out.println(value);
//		}
//
//	}

	//http://jsefa.sourceforge.net/quick-tutorial.html
	public void parseCsvFileUsingAnnotations(String data) {
		Deserializer deserializer = CsvIOFactory.createFactory(WikiMetadata.class).createDeserializer();
		StringReader reader = new StringReader(data);
		deserializer.open(reader);
		
		while (deserializer.hasNext()) {
		    WikiMetadata wikimetadata = deserializer.next();
		    System.out.println(wikimetadata.getGeneral_features());
		    // do something useful with it
		}
		deserializer.close(true);
	}

	public static void main(String args[]) throws IOException {
		CsvParser parser = new CsvParser();
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
	  			.getResourceAsStream("wikimatrix_consolidated.csv");
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, Charset.defaultCharset());
		String wikimatrixData = writer.toString(); 
		
			parser.parseCsvFileUsingAnnotations(wikimatrixData);
		
	}
}
