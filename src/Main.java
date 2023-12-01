import java.util.Scanner;

import classes.function.AdminFunction;
import classes.function.GeneralFunction;
import classes.util.Menu;

public class Main {

	static Scanner userInput = new Scanner(System.in);
	static String role = null;

	public static void main(String[] args) {

		while(true)
		{
			switch (Menu.menuAdmin(userInput)) {
		case "1":
			Menu.displayUserList();
			AdminFunction.DisplayUser();
			break;
		case "2":
			Menu.addUser();
			GeneralFunction.signUp(userInput);
			break;
		case "3":
			Menu.editUser();
			AdminFunction.editUser(userInput);
		case "4":
			Menu.deleteUser();
			AdminFunction.deleteUser(userInput);
		default:
			break;
		}
		}
	}

}
