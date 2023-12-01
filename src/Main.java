import java.util.Scanner;

import classes.function.AdminFunction;
import classes.function.GeneralFunction;
import classes.util.Menu;

public class Main {

	static Scanner userInput = new Scanner(System.in);
	static String role = null;

	public static void main(String[] args) {

		// GeneralFunction.createDataDir();

		switch (Menu.menuAdmin(userInput)) {
		case "1":
			Menu.displayUserList();
			AdminFunction.DisplayUser();
			break;
		case "2":
			Menu.addUser();
			AdminFunction.addUser(userInput);
			break;
		case "3":
			Menu.editUser();
			AdminFunction.editUser(userInput);
		default:
			break;
		}

	}

}
