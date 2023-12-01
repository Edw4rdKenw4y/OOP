package classes.util;

import java.util.Scanner;

public class Menu {

	public static String welcome(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║ QUIZ EXAM MANAGEMENT SYSTEM   ║\n"
				    + "\t║     (for IT department)       ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Sign in           [2] Sign up\n"
				    + "\n"
				    + "Please choose 1 of the 2 options above or enter other content to exit\n"
				    + " ";
		System.out.print(text);
		return CheckInput.toStrNumberic(input.nextLine(), 1, 2);
	}
	
	public static void signIn() {
		String text = ""
				+ "Sign in to QMS\n";
		System.out.print(text);
	}
	public static void signUp() {
		String text = ""
				+ "Welcome to QMS!\n"
				+ "Let's start by registering an account\n";
		System.out.print(text);
	}

	public static String menuAdmin(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║			  Admin  			 ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Display user's list      [2] Add new user\n"
				    + "\t[3] Edit user infor 		  [4] Delete user\n"
				    + "\t[5] Log Out\n"
				    + "\n"
				    + "Please choose 1 of the 5 options above or enter other content to exit\n"
				    + " ";
	System.out.print(text);
	return CheckInput.toStrNumberic(input.nextLine(), 1, 5);
	}
	public static void displayUserList() {
		String text = ""
				+ "Display user's list\n";
		System.out.print(text);
	}
	public static void addUser() {
		String text = ""
				+ "Add new user\n";
		System.out.print(text);
	}
	public static void editUser() {
		String text = ""
				+ "Edit user infor\n";
		System.out.print(text);
	}
	public static void deleteUser() {
		String text = ""
				+ "Delete user\n";
		System.out.print(text);
	}
	public static void logOut() {
		String text = ""
				+ "Logout successfully\n";
		System.out.print(text);
	}
	public static String menuProfessor(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║			  Professor  		 ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Display user's list      [2] Add new user\n"
				    + "\t[3] Edit user infor 		  [4] Delete user\n"
				    + "\t[5] Log Out\n"
				    + "\n"
				    + "Please choose 1 of the 5 options above or enter other content to exit\n"
				    + " ";
	System.out.print(text);
	return CheckInput.toStrNumberic(input.nextLine(), 1, 5);
	}
}