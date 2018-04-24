package ueb15;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ueb15.output.WordListReader;

public class Main {

	public static final int ITTERATIV = 0;
	public static final int RECURSIV = 1;
	
	public static final int GGT = 0;
	public static final int PALIMDROM = 1;

	public static void main(String[] args) throws IOException {
		List<String> params = new LinkedList<>();
		int mode = -1;
		int prog = -1;

		Iterator<String> argIt = Arrays.stream(args).iterator();
		while (argIt.hasNext()) {
			String arg = argIt.next();
			if (arg == null) {
				continue;
			} else {
				if(arg.equals("-p")) {
					if (argIt.hasNext()) {
						arg = argIt.next();
						prog = Integer.parseInt(arg);
					} else {
						throw new IllegalArgumentException(
								"-p needs a value");
					}
				}else if (arg.equals("-m")) {
					if (argIt.hasNext()) {
						arg = argIt.next();
						mode = Integer.parseInt(arg);
					} else {
						throw new IllegalArgumentException(
								"-m needs a value");
					}
				} else if (arg.equals("-f")) {
					if (argIt.hasNext()) {
						arg = argIt.next();
						WordListReader reader = null;
						try {
							reader = new WordListReader(
									new FileInputStream(new File(arg)));
							params.addAll(reader.readAllWords());
						} finally {
							if (reader != null) {
								reader.close();
							}
						}
					} else {
						throw new IllegalArgumentException(
								"-f needs a value");
					}
				}else {
					params.add(arg);
				}
			}
		}
		Main instance = new Main();
		instance.exec(prog,mode,params);
		
	}

	private void exec(int prog, int mode, List<String> params) {
		if(prog == GGT) {
			Integer buffer = null;
			for(String s : params) {
				if(buffer == null) {
					buffer = toNumber(s);
				}else {
					Integer second = toNumber(s);
					if(second != null) {
						System.out.printf("GGT of %d and %d is %d\r\n",buffer,second,ggt(buffer,second));
					}
				}
			}
		}else if (prog == PALIMDROM) {
			for(String s : params) {
				boolean res = false;
				if(mode == ITTERATIV) {
					res = getItterativP().isPalindrome(s);
				}else if(mode == RECURSIV){
					res = getRecursiveP().isPalindrome(s);
				}else {
					throw new IllegalArgumentException("Unknown mod key");
				}
				System.out.printf("%s is%s a palimdrom\r\n",s,res ? "":" not");
			}
		}else {
			throw new IllegalArgumentException("Unknown program key");
		}
		
	}

	private Integer toNumber(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.printf("WARNING : Got %s as input, but is not a Number, skipping ...\r\n", s);
		}
		return null;
	}

	private Palindrom itterativP = null;
	private Palindrom recursiveP = null;

	public long ggt(long zahl1, long zahl2) {
		return new GGT().ggTCalc(zahl1, zahl2);
	}

	public boolean isPalimdrom(String word, int mode) {
		Palindrom instance = null;
		if (mode == ITTERATIV) {
			instance = getItterativP();
		} else if (mode == RECURSIV) {
			instance = getRecursiveP();
		} else {
			throw new IllegalArgumentException("Invalid mode");
		}
		return instance.isPalindrome(word);
	}

	/**
	 * @return the itterativP
	 */
	public Palindrom getItterativP() {
		if (itterativP == null) {
			itterativP = new PalindromeIterative();
		}
		return itterativP;
	}

	/**
	 * @return the recursiveP
	 */
	public Palindrom getRecursiveP() {
		if (recursiveP == null) {
			recursiveP = new PalindromeRecursive();
		}
		return recursiveP;
	}

}
