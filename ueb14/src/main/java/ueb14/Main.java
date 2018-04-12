package ueb14;

public class Main {

	public static void main(String[] args) {
		Mitarbeiter m1 = new Mitarbeiter("Max", "Mustermann",
				"max@htwsaar.de");
		Mitarbeiter m2 = new Mitarbeiter("John", "Doe", "john@htwsaar.de");
		Raum r1 = new Raum(18, 0, 1);
		Raum r2 = new Raum(2, 1, 9);
		Raum r3 = new Raum(2, 1, 10);

		m1.reserviere(r1, new Uhrzeit(12, 30), new Uhrzeit(14, 30),
				"VOOP");
		m1.reserviere(r2, new Uhrzeit(14, 30), new Uhrzeit(16, 30),
				"WebTech");
		m2.reserviere(r2, new Uhrzeit(12, 30), new Uhrzeit(13, 30),
				"Prog II");
		m2.reserviere(r3, new Uhrzeit(9, 30), new Uhrzeit(11, 30), "ITM");
		
		System.out.println(r2);
	}

}
