package ueb16;

public class PersonenQueue extends AbstractQueue<Person> {

	public PersonenQueue(int size) {
		super(Person.class, size);
	}
	
	public void print () {
		for(Person p : this) {
			System.out.println(p.toString());
		}
	}
	
	public String smallest() {
		String buffer = null;
		for (Person p : this) {
			if(buffer == null || p.getFirstName().compareTo(buffer) > 0) {
				buffer = p.getFirstName();
			}
		}
		return buffer;
	}

}
