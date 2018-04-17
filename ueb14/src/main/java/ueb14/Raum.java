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
		if (r != null) {
			if(r.getReservator() == null) {
				throw new IllegalArgumentException("No reservator specified");
			}
			r.setRaum(this);
			reservierungen.add(r);
		}else {
			//Do nothing
		}
	}

	public List<Mitarbeiter> getAllReservatoren() {
		List<Mitarbeiter> res = new ArrayList<>();
		for (Reservierung r : reservierungen) {
			res.add(r.getReservator());
		}
		return res;
	}

	public boolean isReservedFor(Mitarbeiter m) {
		for (Reservierung r : reservierungen) {
			if (r.getReservator().equals(m)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the geb
	 */
	public int getGeb() {
		return geb;
	}

	/**
	 * @param geb
	 *            the geb to set
	 */
	public void setGeb(int geb) {
		this.geb = geb;
	}

	/**
	 * @return the raum
	 */
	public int getRaum() {
		return raum;
	}

	/**
	 * @param raum
	 *            the raum to set
	 */
	public void setRaum(int raum) {
		this.raum = raum;
	}

	/**
	 * @return the etage
	 */
	public int getEtage() {
		return etage;
	}

	/**
	 * @param etage
	 *            the etage to set
	 */
	public void setEtage(int etage) {
		this.etage = etage;
	}

	/**
	 * @return the reservierungen
	 */
	public List<Reservierung> getReservierungen() {
		return reservierungen;
	}

	/**
	 * @param reservierungen
	 *            the reservierungen to set
	 */
	public void setReservierungen(List<Reservierung> reservierungen) {
		this.reservierungen = reservierungen;
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
