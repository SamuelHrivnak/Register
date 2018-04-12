package register;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register {
	/** register.Person array. */
	private Person[] persons;

	/** Number of persons in this register. */
	private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/* (non-Javadoc)
	 * @see register.Register#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see register.Register#getSize()
	 */
	@Override
	public int getSize() {
		return persons.length;
	}

	/* (non-Javadoc)
	 * @see register.Register#getPerson(int)
	 */
	@Override
	public Person getPerson(int index) {
		return persons[index];
	}

	/* (non-Javadoc)
	 * @see register.Register#addPerson(register.Person)
	 */
	@Override
	public void addPerson(Person person) {
		if (findPersonByName(person.getName()) != null) 
			throw new RuntimeException("Person with the same name already exists!");
		else if( findPersonByPhoneNumber(person.getPhoneNumber()) != null)
			throw new RuntimeException("Person with the same phone number already exists!");
		else{
			persons[count] = person;
			count++;
		}
	}

	/* (non-Javadoc)
	 * @see register.Register#findPersonByName(java.lang.String)
	 */
	@Override
	public Person findPersonByName(String name) {
		for (int index = 0; index < count; index++) {
			if (persons[index].getName().equals(name)) {
				return persons[index];
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see register.Register#findPersonByPhoneNumber(java.lang.String)
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (int index = 0; index < count; index++) {
			if (persons[index].getPhoneNumber().equals(phoneNumber)) {
				return persons[index];
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see register.Register#removePerson(register.Person)
	 */
	@Override
	public void removePerson(Person person) {
		int personsIndex = findIndexOfPerson(person);
		if (personsIndex > -1) {
			for (int index = personsIndex; index < count - 1; index++) {
				persons[index] = persons[index + 1];
			}
			persons[count] = null;
			count--;
		}
	}

	/**
	 * Returns index of a specified person from register
	 * 
	 * @param person
	 *            person to find
	 * @return index of a person, returns -1 if not found
	 */
	private int findIndexOfPerson(Person person) {
		for (int index = 0; index < count; index++) {
			if (persons[index].equals(person)) {
				return index;
			}
		}
		return -1;
	}
}
