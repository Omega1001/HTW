package ueb07;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLLinkFinder {

	private static final Pattern TAG_START = Pattern.compile("<a (.+?)(/)?>");
	private static final Pattern TAG_ATTRIBUTE = Pattern.compile(" *(.+?)=\"(.+?)\"");
	private static final Pattern TAG_END = Pattern.compile("</a>");

	public static void main(String[] args) {
		run(System.in, System.out, System.err);
	}

	public static void run(InputStream in, PrintStream out) {
		run(in, out, out);
	}

	public static void run(InputStream in, PrintStream out, PrintStream err) {
		List<LinkContainer> links = findLinksInStream(in);
		for (LinkContainer link : links) {
			out.printf("%-10s : %s\r\n", link.getText(), link.getUrl());
		}
	}

	public static List<LinkContainer> findLinksInStream(InputStream in) {
		List<LinkContainer> result = new ArrayList<>();
		@SuppressWarnings("resource") // Stream passed from outside, should be closed outside!
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
						// DO NOTHING, ignore self closing tag, cause it has no content!
					} else {
						attributeMatcher = TAG_ATTRIBUTE.matcher(lineMatcher.group(1));
						while(attributeMatcher.find()) {
							if(attributeMatcher.group(1).equals("href")) {
								hrefTarget = attributeMatcher.group(2);
								hasStart = true;
								line = line.substring(lineMatcher.end(), line.length());
								rescannLine = true;
							}
						}
					}
				}
			} else {
				// Search for End Tag
				lineMatcher = TAG_END.matcher(line);
				if (lineMatcher.find()) {
					//Appand remaining Content
					tagContent = tagContent.concat(line.substring(0, lineMatcher.start()));
					// Found tag End
					if (hrefTarget != null && tagContent != null && !tagContent.equals("")) {
						// Found Data are Valid
						result.add(new LinkContainer(tagContent.trim(), hrefTarget.trim()));
					} else {
						// Logging a warning here, if logging framework was available...
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
