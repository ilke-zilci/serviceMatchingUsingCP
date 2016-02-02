package uk.ac.ucl.cs.sse.dino.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;

/**
 * Dino utility class for performing basic I/O operations.
 * 
 */
public class DinoIO {

	/**
	 * Copies the resource at the given URI to the given file.
	 * 
	 * @param uri
	 *            the URI of the resource to copy.
	 * @param file
	 *            the file to which to copy the resource.
	 * @throws MalformedURLException
	 *             if the provided URI cannot be handled as a URL.
	 * @throws IOException
	 *             if an IO error occurs during the copy.
	 */
	public static void copy(URI uri, File file) throws MalformedURLException,
			IOException {
		InputStreamReader input = new InputStreamReader(uri.toURL()
				.openStream());

		if (!file.exists()) {
			file.createNewFile();
		}

		OutputStreamWriter output = new FileWriter(file);
		try {
			copy(input, output);
		} finally {
			output.close();
			input.close();
		}
	}

	/**
	 * Copies a file to the location specified by another file. If the target
	 * file does not exist then it is created. If the target file does exist
	 * then it is overwritten.
	 * 
	 * @param f1
	 *            the file to copy.
	 * @param f2
	 *            the location to copy to.
	 * @throws IOException
	 */
	public static void copy(File f1, File f2) throws IOException {
		if (!f2.exists()) {
			f2.createNewFile();
		}

		FileReader reader = new FileReader(f1);
		FileWriter writer = new FileWriter(f2);

		try {
			copy(reader, writer);
		} finally {
			writer.close();
			reader.close();
		}
	}

	/**
	 * Copies the data from a Reader to a Writer. the client code is responsible
	 * for closing these streams.
	 * 
	 * @param input
	 *            the reader for the input data.
	 * @param output
	 *            the writer where the data should be written.
	 * @throws IOException
	 *             if an IO problem occurs.
	 */
	public static void copy(Reader input, Writer output) throws IOException {
		int b;
		while ((b = input.read()) != -1) {
			output.write(b);
		}
	}

}
