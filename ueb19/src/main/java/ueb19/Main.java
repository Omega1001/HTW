package ueb19;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		LinkedList<String> o = new LinkedList<>();
		System.out.println(o.isEmpty());
		o.add("Test 123");
		o.add("Das Funktioniert");
		o.add("Nicht");
		o.remove(2);
		o.set(0, "Super Test");
		o.add(1, "Starte Test");
		o.add("WX");
		o.add("WY");
		o.remove(o.indexOf("WX"));
		o.remove(o.get(3));
		o.addAll(Arrays.asList("Diese","Testdaten","Sind","Einzeln"));
		
		System.out.println(o.isEmpty());
		System.out.println(o.size());
		System.out.println(o.contains("Super Test"));
		System.out.println(o.contains("Not so super Test"));
		for(String s : o) {
			System.out.println(s);
		}
		System.out.println(Arrays.deepToString(
				o.toArray(new String[2])
				));
		System.out.println(Arrays.deepToString(
				o.toArray(new String[20])
				));
	}

}
