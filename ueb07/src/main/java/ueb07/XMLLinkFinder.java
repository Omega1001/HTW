package ueb07;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class XMLLinkFinder {
	
	private static final Pattern LINK_PAT = Pattern.compile("(<a).+(href=\").+(</a>)");
	private static final Pattern URL_PAT = Pattern.compile("(<a).+(href=\").+(\">)");
	private static final Pattern TEXT_PAT = Pattern.compile("(>).+(</a>)");

	public static void main(String[] args) {
		run(System.in, System.out, System.err);
	}

	public static void run(InputStream in, PrintStream out) {
		run(in,out,out);
	}
	
	public static void run(InputStream in, PrintStream out, PrintStream err) {
		List<LinkContainer> links = findLinksInStream(in);
		for (LinkContainer link : links) {
			//output somehow
			
			System.out.printf ( link.getText() + ": %60s\n", link.getUrl() );
		}
	}
	
	public static List<LinkContainer> findLinksInStream(InputStream in){
		List<LinkContainer> result = new ArrayList<>();
		//Do Stuff
		String inn = new java.util.Scanner(in,"UTF-8").useDelimiter("\\A").next();
		Matcher linkMat = LINK_PAT.matcher(inn);

        while (linkMat.find()) {
        	Matcher urlMat = URL_PAT.matcher(linkMat.group());
        	urlMat.find();
        	String url = urlMat.group().substring( 9, urlMat.group().length() -2 );
        	
        	Matcher textMat = TEXT_PAT.matcher(linkMat.group());
        	textMat.find();
        	String text = textMat.group().substring( 1, ( textMat.group().length() -4 ) );
        	LinkContainer linkContainer = new LinkContainer(text, url);
        	result.add(linkContainer);
        }
		return result;
	}

}
