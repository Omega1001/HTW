package ueb07;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class XMLLinkFinder {

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
		}
	}
	
	public static List<LinkContainer> findLinksInStream(InputStream in){
		List<LinkContainer> result = new ArrayList<>();
		//Do Stuff
		return result;
	}

}
