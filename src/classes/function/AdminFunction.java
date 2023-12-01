package classes.function;

import java.io.File;
import java.time.Year;
import java.util.Scanner;

import classes.repository.AccountRepository;
import classes.user.Account;
import classes.user.Admin;
import classes.user.Professor;
import classes.user.Student;
import classes.user.UserInfo;
import classes.util.CheckInput;
import classes.util.Constant;

public class AdminFunction {

    public static void DisplayUser()
    {
        AccountRepository accountRepository = new AccountRepository(Constant.dataPath.accounts_File);
        System.out.println("User list:");
        for (Account acc : accountRepository.getAcclist().getArr()) {
            System.out.println(acc);
        }
    }

    public static void editUser(Scanner userInput) {
        AccountRepository accountRepository = new AccountRepository(Constant.dataPath.accounts_File);
        System.out.println("Enter the username of the user you want to edit:");
        System.out.print(" ");
        String username = userInput.nextLine();

        Account userToEdit = accountRepository.findUserByUserName(username);

        if (userToEdit != null) {
            UserInfo userInfoToEdit = userToEdit.getInfo();

            System.out.println("Edit user information for " + username + ":");
            System.out.println("1. Full Name");
            System.out.println("2. Year of Birth");
            System.out.println("3. Gender");
            System.out.println("4. Phone Number");

            System.out.print("Enter the number of the information you want to edit: ");
            int choice = userInput.nextInt();
            userInput.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter new full name:");
                    System.out.print(" ");
                    userInfoToEdit.setFullName(CheckInput.toFullName(userInput.nextLine()));
                    break;
                case 2:
                    System.out.println("Enter new year of birth:");
                    System.out.print(" ");
                    String year = CheckInput.toStrNumberic(userInput.nextLine(), 1950, Year.now().getValue());
                    userInfoToEdit.setYearOfBirth(year == null ? -1 : Integer.parseInt(year));
                    break;
                case 3:
                    System.out.println("Enter new gender (Male/Female/Other):");
                    System.out.print(" ");
                    userInfoToEdit.setGender(CheckInput.toGender(userInput.nextLine()));
                    break;
                case 4:
                    System.out.println("Enter new phone number:");
                    System.out.print(" ");
                    userInfoToEdit.setPhoneNumber(CheckInput.toPhoneNumber(userInput.nextLine()));
                    break;
                default:
                    System.out.println("Invalid choice. No information edited.");
                    return;
            }

            // Update the user information in the repository
            accountRepository.changeAccountInfo(username, userToEdit);

            System.out.println("User information updated successfully!");
        } else {
            System.out.println("User not found. Unable to edit.");
        }
    }

    public static void deleteUser(Scanner sc)
    {
        AccountRepository accountRepository = new AccountRepository(Constant.dataPath.accounts_File);
        System.out.println("Enter user name you want to delete: ");
        String deleteUsername = sc.nextLine();

        if (accountRepository.removeUser(deleteUsername)) {
            System.out.println("User has been delete from system!");
        } else {
            System.out.println("Wrong user name!!");
        }
    }

    public static void addUser(Scanner sc)
    {
        AccountRepository accountRepository = new AccountRepository(Constant.dataPath.accounts_File);
		Account newAccount = null;
		String username = null;
		String role = null;
		String password = null;
		UserInfo info = new UserInfo();

		do {
			System.out.println("Please enter your student ID (professor ID)");
			System.out.print(" ");
			username = sc.nextLine();
			role = CheckInput.toRole(username);
			if (role == null)
				System.out.println("This ID is invalid");
		} while (role == null);
		System.out.println("Create a password");
		System.out.print(" ");
		password = sc.nextLine();
		do {
			System.out.println("Enter your full name (Ex: First_Mid_LastName)");
			System.out.print(" ");
			info.setFullName(CheckInput.toFullName(sc.nextLine()));
			if (info.getFullName() == null)
				System.out.println("You entered the name in the wrong format");
		} while (info.getFullName() == null);
		do {
			System.out.println("Enter your year of birth");
			System.out.print(" ");
			String year = CheckInput.toStrNumberic(sc.nextLine(), 1950, Year.now().getValue());
			info.setYearOfBirth(year == null ? -1 : Integer.parseInt(year));
			if (info.getYearOfBirth() == -1)
				System.out.println("Invalid year");
		} while (info.getYearOfBirth() == -1);
		System.out.println("Male or Female or Other?");
		System.out.print(" ");
		info.setGender(CheckInput.toGender(sc.nextLine()));
		do {
			System.out.println("Enter your phone number");
			System.out.print(" ");
			info.setPhoneNumber(CheckInput.toPhoneNumber(sc.nextLine()));
			if (info.getPhoneNumber() == null)
				System.out.println("Invalid phone number in Viet Nam");
		} while (info.getPhoneNumber() == null);

		switch (role) {
		case "admin":
			newAccount = new Admin();
			break;
		case "professor":
			newAccount = new Professor();
			break;
		case "student":
			newAccount = new Student();
			break;
		default:
			break;
		}
		newAccount.setUsername(username);
		newAccount.setPassword(password);
		newAccount.setRole(role);
		newAccount.setInfo(info);

		if (accountRepository.addUser(newAccount)) {
			System.out.println("Registration successful!");
		}
		System.out.println("Registration failed. Username already exists.");
    }

}
