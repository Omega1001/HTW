package ueb15;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ueb15.output.CSVWriter;

public class PalimdromSpeedTest {

	public static void main(String[] args) throws IOException {
		new PalimdromSpeedTest().test();
	}

	private CSVWriter out;
	private Random random = new Random();

	public PalimdromSpeedTest() throws IOException {
		out = new CSVWriter(new FileWriter(new File("speedResult.csv")));
	}

	public void test() throws IOException {
		List<String> samples = createSamples();
		Palindrom itterativ = SpeedAnalyser.create(Palindrom.class,
				new PalindromeIterative());
		Palindrom recursive = SpeedAnalyser.create(Palindrom.class,
				new PalindromeRecursive());

		itterativ.isPalindrome("AA");
		recursive.isPalindrome("AA");
		out.putColumns("Number of characters", "Resrult Itterativ",
				"Result Recursive", "Time Itterativ", "Time Recursive");
		out.nextRow();
		for (String sample : samples) {
			out.putColumn(String.valueOf(sample.length()));
			out.putColumn(String.valueOf(itterativ.isPalindrome(sample)));
			out.putColumn(String.valueOf(recursive.isPalindrome(sample)));
			out.putColumn(String.valueOf(SpeedAnalyser.getLastExecutionTime(itterativ)));
			out.putColumn(String.valueOf(SpeedAnalyser.getLastExecutionTime(recursive)));
			out.nextRow();
		}
System.out.println("end");
	}

	private List<String> createSamples() {
		List<String> res = new ArrayList<>();
		for (int i=1; i<5; i++) {
			res.add(createPalimdrom(Math.pow(10, i-1)*2.5D));
			res.add(createPalimdrom(Math.pow(10, i-1)*5));
			res.add(createPalimdrom(Math.pow(10, i-1)*7.5D));
			res.add(createPalimdrom(Math.pow(10, i)));
		}
		return res;
	}

	private String createPalimdrom(double charCount) {
		StringBuilder sb = new StringBuilder();
		if(charCount % 2 == 1) {
			sb.append((char)(random.nextInt(26)+65));
		}
		for(int i =sb.length();i<charCount;i+=2) {
			char c = (char)(random.nextInt(26)+65);
			sb.insert(0,c);
			sb.append(c);
		}
		
		return sb.toString();
	}

}
