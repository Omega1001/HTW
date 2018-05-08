package ueb16;

import java.util.Arrays;

public class NumberCruncherMain {

	public static void main(String[] args) {
		if (args.length > 2) {
			int prg = -1;
			try {
				prg = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println(
						"Program key is not an integer: expected number but got '"
								+ args[0] + '\'');
			}
			int cnt = -1;
			try {
				cnt = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.err.println(
						"Size is not an integer: expected number but got '"
								+ args[1] + '\'');
			}

			NumberCruncher n1 = null;
			NumberCruncher2 n2 = null;

			if (prg == 1) {
				NumberCruncher.addDefaultOpperations();
				n1 = new NumberCruncher(cnt);
				System.out.println("Innitial Values :");
				System.out.println(Arrays.toString(n1.getNumbers()));
				n1.crunch(Arrays.copyOfRange(args, 2, args.length));
				System.out.println("Final Values :");
				System.out.println(Arrays.toString(n1.getNumbers()));
			} else if (prg == 2) {
				NumberCruncher2.addDefaultOpperations();
				n2 = new NumberCruncher2(cnt);
				System.out.println("Innitial Values :");
				System.out.println(Arrays.toString(n2.getNumbers()));
				n2.crunch(Arrays.copyOfRange(args, 2, args.length));
				System.out.println("Final Values :");
				System.out.println(Arrays.toString(n2.getNumbers()));
			} else {
				System.err.println(
						"Unknown Program Key, expeced 1 or 2 but got "
								+ prg);
			}
		} else {
			System.err.println(
					"Insufficient Program Arguments detected, "
					+ "expected 'program Key' 'Size' 'Opperations'"
					+ "... but got a total of "
							+ args.length);
		}

	}

}
