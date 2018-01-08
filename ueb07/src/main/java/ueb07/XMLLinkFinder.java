package ueb07;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to find all 'a' links in a HTML document
 * @author Adam
 * @author D-Frommborys
 *
 */
public class XMLLinkFinder {

	private static final Pattern TAG_START = Pattern.compile(
			"<a (.+?)(/)?>");
	private static final Pattern TAG_ATTRIBUTE = Pattern.compile(
			" *(.+?)=\"(.+?)\"");
	private static final Pattern TAG_END = Pattern.compile("</a>");

	/**
	 * main Method of this Program, using system in, out and error as
	 * streams
	 * 
	 * @param args
	 *            program args -> won't be evaluated
	 */
	public static void main(String[] args) {
		run(System.in, System.out, System.err);
	}

	/**
	 * Method to run the Link finding<br>
	 * This Method opperates the same as
	 * {@link #run(InputStream, PrintStream, PrintStream)}<br>
	 * The missing error Stream will be piped to the passed output stream
	 * 
	 * @param in
	 *            Stream that contains inputs from the user
	 * @param out
	 *            Stream to print results and errors to
	 */
	public static void run(InputStream in, PrintStream out) {
		run(in, out, out);
	}

	/**
	 * This method reads any html from the passed input stream, filters all
	 * 'a' links and outputs them<br>
	 * This method will print a formated list containing the content of the
	 * tag and the associated link<br>
	 * Content larger than 10 characters will be cut<br>
	 * Links have no length limit<br>
	 * Links will be outputted as they are. There will be no transformation
	 * from relative to absolute links. Anchor links are permitted.<br>
	 * In case any error occures, they will be outputed on the passed error
	 * stream
	 * <p>
	 * 
	 * None of the passed input and output Streams must be null
	 * 
	 * @param in
	 *            Stream containing HTML input to analyze
	 * @param out
	 *            Stream to output results on
	 * @param err
	 *            Stream to output errors on
	 * @throws IllegalArgumentException
	 *             if any of the passed streams are null
	 */
	public static void run(InputStream in, PrintStream out,
			PrintStream err) {
		if (in == null || out == null || err == null) {
			throw new IllegalArgumentException(
					"Passed Streams must not be undefined");
		}
		List<LinkContainer> links = findLinksInStream(in);
		for (LinkContainer link : links) {
			out.printf("%-10s : %s\r\n", link.getText(), link.getUrl());
		}
	}

	/**
	 * This Method reads an Input stream until its end, and creates a list
	 * of found links<br>
	 * This method will only recognise 'a' links<br>
	 * This method wil ignore any self-closing Tags, as they contain no
	 * content by definition<br>
	 * This method does NOT perform normalization. So if the input contains
	 * 'dirty' html, not all links may be recognised, or Content could
	 * contain additional invalid content<br>
	 * 
	 * @param in
	 *            Stream to read HTML from
	 * @return a List of the links and there content found in the HTML read
	 *         from the stream
	 * @throws IllegalArgumentException
	 *             if the passed streams are null
	 */

	public static List<LinkContainer> findLinksInStream(InputStream in) {
		if (in == null) {
			throw new IllegalArgumentException(
					"Passed Streams must not be undefined");
		}
		List<LinkContainer> result = new ArrayList<>();
		@SuppressWarnings("resource") // Stream passed from outside, should
										// be closed outside!
		Scanner sc = new Scanner(in);
		String line = "";
		String hrefTarget = null;
		String tagContent = "";
		Matcher lineMatcher;
		Matcher attributeMatcher;

		boolean hasStart = false;
		boolean rescannLine = false;

		while (rescannLine || sc.hasNextLine()) {

			if (rescannLine) {
				// Scann same line again
				rescannLine = false;
			} else {
				// Scann next line
				line = sc.nextLine();
			}

			if (!hasStart) {
				// Search for Start Tag
				lineMatcher = TAG_START.matcher(line);
				if (lineMatcher.find()) {
					// Found Start Tag
					if (lineMatcher.groupCount() == 3) {
						// DO NOTHING, ignore self closing tag, cause it
						// has no content!
					} else {
						attributeMatcher = TAG_ATTRIBUTE.matcher(
								lineMatcher.group(1));
						while (attributeMatcher.find()) {
							if (attributeMatcher.group(1).equals("href")) {
								hrefTarget = attributeMatcher.group(2);
								hasStart = true;
								line = line.substring(lineMatcher.end(),
										line.length());
								rescannLine = true;
							}
						}
					}
				}
			} else {
				// Search for End Tag
				lineMatcher = TAG_END.matcher(line);
				if (lineMatcher.find()) {
					// Appand remaining Content
					tagContent = tagContent.concat(line.substring(0,
							lineMatcher.start()));
					// Found tag End
					if (hrefTarget != null && tagContent != null
							&& !tagContent.equals("")) {
						// Found Data are Valid
						result.add(new LinkContainer(tagContent.trim(),
								hrefTarget.trim()));
					} else {
						// Logging a warning here, if logging framework was
						// available...
					}
					// Reset fields
					hrefTarget = null;
					tagContent = "";
					hasStart = false;
					line = line.substring(0, lineMatcher.end());
					rescannLine = true;
				} else {
					// add to Content
					tagContent = tagContent.concat(line);
				}
			}
		}
		return result;
	}

}
