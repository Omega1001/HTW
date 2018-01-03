package ueb08;

public class PWTest {
	public static void main(String[] args) {
		try {
			// Warteschlange für maximal 10 Patienten anlegen
			PatientenWarteschlange pw = new PatientenWarteschlange(10);
			pw.neuerPatient(4711, "Löw, Jogi"); // Neuen Patient anfügen
			pw.neuerPatient(1234, "Schweinsteiger,Bastian");
			pw.neuerPatient(1111, "Neuer, Manuel");
			pw.neuerPatient(2222, "Hummels, Mats");
			System.out.println(pw);
			System.out.println("Patient gelöscht: " + pw.entfernePatient(1234));
			Patient naechster = pw.derNaechsteBitte();
			System.out.println("Nächster Patient: " + naechster);
			System.out.println(pw);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
}