package ueb14;

import java.util.ArrayList;
import java.util.List;

public class Raum {
	private int geb;
	private int raum;
	private int etage;
	private List<Reservierung> reservierungen = new ArrayList<>();

	public Raum(int geb, int etage, int raum) {
		if (geb < 0 || raum < 0) {
			throw new IllegalArgumentException("Not a valid room");
		}
		this.geb = geb;
		this.raum = raum;
		this.etage = etage;
	}

	public void addReservierung(Reservierung r) {
		r.setRaum(this);
		reservierungen.add(r);
	}

	public List<Mitarbeiter> getAllReservatoren() {
		List<Mitarbeiter> res = new ArrayList<>();
		for (Reservierung r : reservierungen) {
			res.add(r.getReservator());
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(geb).append('.').append(etage).append('-').append(
				raum);
		for (Reservierung r : reservierungen) {
			builder.append("\r\ngebucht von ").append(r.getReservator())
					.append(" von ").append(r.getStart()).append(" bis ")
					.append(r.getEnde());
			if (r.getBemerkung() != null && !"".equals(r.getBemerkung())) {
				builder.append(" für ").append(r.getBemerkung());
			}
		}
		return builder.toString();
	}

}
