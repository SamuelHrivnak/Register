package register;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

	public static void main(String[] args) throws Exception {
		Register register = new ArrayRegister(20);

		register.addPerson(new Person("Janko Hrasko", "0900123456"));
		register.addPerson(new Person("Janko Hasko", "0912345678"));
		register.addPerson(new Person("Misko Hrasko", "0900133456"));
		register.addPerson(new Person("Misko Velky", "0900123457"));
		register.addPerson(new Person("Lorem Ipsum", "0945236487"));

		ConsoleUI ui = new ConsoleUI(register);
		ui.run();
	}
}
