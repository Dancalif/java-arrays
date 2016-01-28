import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class TestEmployeeEntryApp {
	public static void main(String[] args) throws Exception {
		// Declare the vars to store some employee data
		String correctOrNot, correctedItem;
		boolean allCorrect = false;
		int totalEmployee;
		int nextEmpl = 0;
		// Declare the vars to store login credentials
		String userNameInput, userPassInput;
		// Create a Scanner object and assign to keyboard variable
		Scanner keyboard = new Scanner(System.in);
		// Array to store all employee data
		String[] emplDataArray = new String[4];

		// Greeting the user
		System.out.printf("%40s%S%s", "<<<<<Welcome to the ", "\"EmployeeEntry app\"", " application.>>>>>\n");
		System.out.printf("%s", "--------------------------------------------------------------------------\n");
		// Requesting login info to validate if user has the permission to enter
		// the app
		System.out.println("Please, provide your username and password below to login.");
		System.out.print("Username: ");
		userNameInput = keyboard.nextLine();
		System.out.print("Password: ");
		userPassInput = keyboard.nextLine();
		// Calling the method to validate if user has the permission to enter
		// the app
		UserLogin(userNameInput, userPassInput);
		// Requesting info about the number of employees and assign it as array
		// lenght
		System.out.print("\nHow many employees you would like input to the system? > ");
		totalEmployee = keyboard.nextInt();

		for (int i = 1; i <= totalEmployee; i++) {

			if (nextEmpl == 1) {
				System.out.print("\n");
				System.out.println("Please, enter details of the next employee.");
			}
			// Requesting info about the employees and assign it to var
			System.out.print("First name: ");
			emplDataArray[0] = keyboard.next();
			System.out.print("Last name: ");
			emplDataArray[1] = keyboard.next();
			System.out.print("Title: ");
			emplDataArray[2] = keyboard.next();
			System.out.print("Address: ");
			keyboard.nextLine();
			emplDataArray[3] = keyboard.nextLine();

			do {

				System.out.print("\n--------------------------------------------------------------------------\n");
				// Creating the headers for the table: First Name, Last Name,
				// LastName, Address
				System.out.printf("%5S%18S%18S%22S", "First Name #", "Last Name", "Title", "Address\n\n");
				// Fill out the table
				System.out.printf("%10s%20s%20s%20s", emplDataArray[0] + "     |", emplDataArray[1] + "     |",
						emplDataArray[2] + "     |", emplDataArray[3]);
				System.out.print("\n--------------------------------------------------------------------------\n");
				System.out.println("\nPlease check the data above and confirm");
				System.out.print("if \"yes\" - everythign is correct and \"no\" if some corrections are needed. > ");
				// Scanner to get the value if anything needs to be corrected
				correctOrNot = keyboard.next();
				// Logic to correct First Name, Last Name, LastName or Address
				// and
				// re-assign them into array
				if ((correctOrNot.equalsIgnoreCase("no")) || (correctOrNot.equalsIgnoreCase("n"))) {
					System.out.print(
							"Woud would you like to correct: \"First Name\", \"Last Name\", \"Title\" or \"Address\"? > ");
					keyboard.nextLine();
					correctedItem = keyboard.nextLine();
					if ((correctedItem.equalsIgnoreCase("First Name")) || (correctedItem.equalsIgnoreCase("First"))) {
						System.out.print("First name, please: ");
						emplDataArray[0] = keyboard.next();
					} else
						if ((correctedItem.equalsIgnoreCase("Last Name")) || (correctedItem.equalsIgnoreCase("Last"))) {
						System.out.print("Last name please: ");
						emplDataArray[1] = keyboard.next();
					} else if (correctedItem.equalsIgnoreCase("Title")) {
						System.out.print("Title please: ");
						emplDataArray[2] = keyboard.next();
					} else if (correctedItem.equalsIgnoreCase("Address")) {
						System.out.print("Address please: ");
						emplDataArray[3] = keyboard.nextLine();
					}
				} else {
					allCorrect = true;
					nextEmpl = 1;

				}
			} while (allCorrect == false);

			if (totalEmployee > 1) {
				for (int b = 0; b < totalEmployee - 1; b++) {
					writeFile(emplDataArray, totalEmployee, b);
				}
			} else if (totalEmployee == 1) {
				writeFile(emplDataArray, totalEmployee, 0);
			}
		}
		keyboard.close();
		System.out.print("\n");
		System.out.printf("%s", "--------------------------------------------------------------------------\n");
		System.out.printf("%40s%S%s", "<<<<<Please check the file ", "\"Employees\" ", " on a desktop .>>>>>\n");
		System.out.printf("%40s%S%s", "<<<<<Thank you for using ", "\"EmployeeEntry\"", " application.>>>>>\n");
		System.out.printf("%s", "--------------------------------------------------------------------------\n");

	}

	// Method to validate if user has the permission to enter the app
	public static void UserLogin(String userNameInput, String userPassInput) {
		// Vars to store the username and password
		String userNameSaved = "Dancalif";
		String userPassSaved = "12345";
		// Flag to exit a loop in login is successful
		boolean successLogin = false;
		// Loop in case if any entered values are wrong
		do {
			Scanner keyboard = new Scanner(System.in);
			if (userNameInput.equals(userNameSaved) && userPassInput.equals(userPassSaved)) {
				System.out.println("You have successfully logged in.");
				System.out.printf("%s",
						"         --------------------------------------------------------------------------\n");
				successLogin = true;
			} else {
				System.out.print("Username or password is incorrect. Please try again.\n");
				System.out.print("Username: ");
				userNameInput = keyboard.nextLine();
				System.out.print("Password: ");
				userPassInput = keyboard.nextLine();
			}
		} while (successLogin == false);
	}

	public static void writeFile(String[] emplDataArray, int totalEmployee, int rownum)
			throws IOException, FileNotFoundException {
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		Sheet sh = wb.createSheet();
		// for (int rownum = 0; rownum < totalEmployee; rownum++)
		Row row = sh.createRow(rownum);
		for (int cellnum = 0; cellnum < emplDataArray.length; cellnum++) {
			Cell cell = row.createCell(cellnum);
			cell.setCellValue(emplDataArray[cellnum]);

		}

		File dest = new File("/Users/dancalif/desktop/Employees.xlsx");

		if (!dest.exists()) {
			dest.createNewFile();
		}

		FileOutputStream out = new FileOutputStream(dest);
		wb.write(out);
		out.close();

		// dispose of temporary files backing this workbook on disk
		wb.dispose();
	}
}
