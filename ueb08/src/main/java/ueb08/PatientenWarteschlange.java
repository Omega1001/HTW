package ueb08;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class to manage a queue of {@link Patient}s<br>
 * This class works after a FIFO principal <br>
 * 
 * @author Adam
 * @author Fromm-Borys
 *
 */
public class PatientenWarteschlange implements Iterable<Patient> {
	private final Patient[] patients;

	/**
	 * This method is used to create a new Patient Queue with a fixed
	 * maximal capacity<br>
	 * The capacity must be 1 or bigger<br>
	 * It is not allowed to change capacity once the Queue is created<br>
	 * To do so anyway would require to create a new Queue with bigger
	 * capacity<br>
	 * 
	 * @param size
	 *            of the Queue to create. Must be 1 or bigger
	 */
	public PatientenWarteschlange(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size must at least be 1");
		}
		patients = new Patient[size];
		Arrays.fill(patients, null);
	}

	/**
	 * This method is used to add a new patient to this Queue<br>
	 * This method is equivalent to {@link #neuerPatient(Patient)}<br>
	 * This method implicitly creates a new {@link Patient} using the
	 * passed data<br>
	 * 
	 * @param patientNumber
	 *            of the patient to add
	 * @param patientName
	 *            of the patient to add
	 * @see #neuerPatient(Patient)
	 */
	public void neuerPatient(int patientNumber, String patientName) {
		neuerPatient(new Patient(patientNumber, patientName));
	}

	/**
	 * This method is used to add a new patient to this Queue<br>
	 * The element to add must not be null<br>
	 * This method only inserts a {@link Patient} if he/she is not already
	 * part of the Queue<br>
	 * To determin if the patient is already part of the Queue, the
	 * {@link Patient#equals(Object)} method is used<br>
	 * This method will throw an exception, if the Queue is already
	 * full<br>
	 * 
	 * @param patient
	 *            to add to this Queue
	 * @throws IllegalArgumentException
	 *             if the patient is null, or already added
	 * @throws IllegalStateException
	 *             if the Queue is full
	 * 
	 */
	public void neuerPatient(Patient patient) {
		if (patient == null) {
			throw new IllegalArgumentException("Patient mus not be null");
		}
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

	/**
	 * This method is used to remove a specific patient from the Queue<br>
	 * This method is equal to {@link #entfernePatient(int)} with
	 * {@link Patient#getNumber()} as parameter<br>
	 * 
	 * @param patient
	 *            to remove
	 * @return the removed patient, or null if the patient is not part of
	 *         this Queue<br>
	 * @see #entfernePatient(int)
	 */
	public Patient entfernePatient(Patient patient) {
		return entfernePatient(patient.getNumber());
	}

	/**
	 * This method is used to remove a specific patient from the Queue<br>
	 * This method will return the element that got removed from the
	 * Queue<br>
	 * If the passed patient is not part of this Queue, null will be
	 * returned<br>
	 * 
	 * @param patientNr
	 *            of the patient to remove
	 * @return the removed patient, or null if the patient is not part of
	 *         this Queue<br>
	 */
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
				patients[i - 1] = patients[i];
				patients[i] = null;
			}
		}
		return result;
	}

	/**
	 * This method is used to remove the first {@link Patient} in this
	 * Queue<br>
	 * The removed element will be returned<br>
	 * If this Queue is empty, an {@link IllegalStateException} is
	 * thrown<br>
	 * 
	 * @return the first {@link Patient} in this Queue
	 * @throws IllegalStateException
	 *             if the Queue is empty
	 */
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

	/**
	 * This method is used to determin, if a patient is part of this list
	 * or not<br>
	 * 
	 * @param p
	 *            The patient to be checked
	 * @return true if the patient is part of this list
	 */
	public boolean contains(Patient p) {
		if (p == null) {
			return false;
		}
		for (Patient pat : patients) {
			if (p.equals(pat)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<Patient> iterator() {
		return new Iterator<Patient>() {
			int i = 0;

			@Override
			public boolean hasNext() {
				return patients[i] != null;
			}

			@Override
			public Patient next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return patients[i++];
			}
		};
	}

}
