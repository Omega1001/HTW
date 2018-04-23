package ueb15.output;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WordListReader implements Closeable {

	private Scanner sc;

	public WordListReader(InputStream in) {
		if(in == null) {
			throw new IllegalArgumentException("No inputstream supplied");
		}
		sc = new Scanner(in);
	}
	
	public WordListReader(Scanner in) {
		if(in == null) {
			throw new IllegalArgumentException("No inputstream supplied");
		}
		sc = in;
	}

	public List<String> readAllWords() {
		List<String> res = new LinkedList<>();
		while (sc.hasNext()) {
			res.add(sc.next());
		}
		return res;
	}

	@Override
	public void close() throws IOException {
		sc.close();
	}

}
