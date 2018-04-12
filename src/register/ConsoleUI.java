package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * User interface of the application.
 */
public class ConsoleUI {
	/** register.Register of persons. */
	private Register register;

	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Menu options.
	 */
	private enum Option {
		PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
	};
	
	private enum FindOption{
		FIND_BY_NAME, FIND_BY_NUMBER
	};

	public ConsoleUI(Register register) {
		this.register = register;
	}

	public void run() {
		while (true) {
			switch (showMenu()) {
			case PRINT:
				printRegister();
				break;
			case ADD:
				addToRegister();
				break;
			case UPDATE:
				updateRegister();
				break;
			case REMOVE:
				removeFromRegister();
				break;
			case FIND:
				findInRegister();
				break;
			case EXIT:
				return;
			}
		}
	}

	private String readLine() {
		// In JDK 6.0 and above Console class can be used
		// return System.console().readLine();

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	private void printRegister() {
		for (int index = 0; index < register.getCount(); index++) {
			System.out.println(index + 1 + ". " + register.getPerson(index).toString());
		}
	}

	private void addToRegister() {
		System.out.println("Enter Name: ");
		String name = readLine();
		System.out.println("Enter Phone Number: ");
		String phoneNumber = readLine();

		register.addPerson(new Person(name, phoneNumber));
	}

	private void updateRegister() {
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());
		Person person = register.getPerson(index - 1);
		System.out.println(index + ". " + person.toString());
		
		System.out.print("New name: ");
		String name = readLine();
		person.setName(name);
		
		System.out.print("New phone number: ");
		String phoneNumber = readLine();
		person.setPhoneNumber(phoneNumber);
		
		System.out.println(index + ". " + person.toString());
		
	}

	private void findInRegister() {
		switch(showFindOptions()) {
		case FIND_BY_NAME:
			System.out.print("Name: ");
			System.out.println(register.findPersonByName(readLine()));
			break;
		case FIND_BY_NUMBER:
			System.out.print("Phone number: ");
			System.out.println(register.findPersonByPhoneNumber(readLine()));
			break;
		}
	}

	private FindOption showFindOptions() {
		System.out.println("Find by:");
		for (FindOption option : FindOption.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > FindOption.values().length);

		return FindOption.values()[selection - 1];
	}
	
	private void removeFromRegister() {
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());
		Person person = register.getPerson(index - 1);
		register.removePerson(person);
	}

}
