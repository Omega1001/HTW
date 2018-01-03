package ueb08;

import java.util.Arrays;

public class PatientenWarteschlange {
	private final Patient[] patients;

	public PatientenWarteschlange(int size) {
		if (size < 0) {
			throw new IllegalArgumentException("Size must at least be 1");
		}
		patients = new Patient[size];
		Arrays.fill(patients, null);
	}

	public void neuerPatient(int patientNumber, String patientName) {
		neuerPatient(new Patient(patientNumber, patientName));
	}

	public void neuerPatient(Patient patient) {
		for (int i = 0; i < patients.length; i++) {
			if (patients[i] == null) {
				patients[i] = patient;
				return;
			} else if (patients[i].getNumber() == patient.getNumber()) {
				throw new IllegalArgumentException(
						"Patient already in line");
			}
		}
		throw new IllegalStateException(
				"Line currently has maximal capacity");
	}

	public Patient entfernePatient(Patient patient) {
		return entfernePatient(patient.getNumber());
	}

	public Patient entfernePatient(int patientNr) {
		Patient result = null;
		for (int i = 0; i < patients.length; i++) {

			if (result == null) {
				if (patients[i] == null) {
					break;
				} else if (patients[i].getNumber() == patientNr) {
					result = patients[i];
					patients[i] = null;
				}
			} else {
				if (patients[i] == null) {
					break;
				}
				patients[i-1] = patients[i];
				patients[i] = null;
			}
		}
		return result;
	}

	public Patient derNaechsteBitte() {
		if (patients[0] != null) {
			return entfernePatient(patients[0]);
		} else {
			throw new IllegalStateException("No Patient left in line");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Warteliste\r\n");
		sb.append(String.format("%s     %s", "Pnr", "Name"));
		for (Patient p : patients) {
			if (p != null) {
				sb.append(String.format("\r\n%04d    %s", p.getNumber(), p
						.getName()));
			}
		}
		return sb.toString();
	}

}
