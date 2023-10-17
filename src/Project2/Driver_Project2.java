//ICSI 201. Introduction to Computer Science, 
//Spring 2022
//Pandey, Abhineet Kumar
//Harrison McKenna
//001516105
package Project2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver_Project2 {
	/*
	 * zLooped is initialized later down in the code regarding a type z product. It
	 * controls the amount of times the program prompts the user to enter another
	 * product to get the buy 2 get one for free sale.
	 */

	// Array that stores the 'product' objects that are in stock.
	private static ArrayList<Product> inventory = new ArrayList<Product>();

	// Loop that controls the whole program. If set to false, then the main loop
	// ends, thus terminating the program.
	private static boolean cont = true;

	// Count variable initialized, will be used for converting the text file to data
	private static int count = 0;

	// Date variable initialized
	private static Date date = new Date();

	// Main method initialized
	public static void main(String[] args) throws IOException {
		// Scanner object initialized.
		Scanner input = new Scanner(System.in);
		// Displays final price
		System.out.println("What is your name?");
		// customer object initialized by scanner
		User customer = new User(input.nextLine());
		// Order object named 'Final' initialized and customer is put into it.
		Order Final = new Order(customer);
		// Declared for user to enter file prompt
		String nameOfFile = "";
		// testName initiated to check for file name
		boolean testName = false;
		// While loop iterated checking for a valid file name
		while (testName == false) {
			System.out.println("Enter the filepath of the text file you wish to use for making the inventory:");
			// String used for fileName
			nameOfFile = input.nextLine();
			// If the text file name is true it will end loop, or else it will repeat itself
			if (checkTextFile(nameOfFile) == true) {
				// Testname is set to true if the above if statement is true, then the while
				// loop will terminate
				testName = true;
			}
		}
		// file and countreader declared to determine number of count
		FileReader file = new FileReader(nameOfFile);
		BufferedReader countReader = new BufferedReader(file);

		// First iteration started
		String line = countReader.readLine();
		// Checks if line is null
		while (line != null) {
			// Will count how depending how many times the lines aren't null
			count++;
			// line value changes
			line = countReader.readLine();
		}
		// countReader and file objects are closed.
		countReader.close();
		file.close();
		// Added fileReader to let 'inputReader' parse through the lines of the txt
		// file.
		FileReader file2 = new FileReader(nameOfFile);
		// Added BufferedReader to parse through the lines of the txt file
		BufferedReader inputReader = new BufferedReader(file2);
		// Converts lines of txt file to product objects
		initializeInv(inputReader);
		// Main loop
		while (cont == true) {
			// printMenu is called to display what's in stock.
			printMenu();

			// Error initialized to true so the program will repeatedly ask the user for a
			// correct input if they enter an invalid ID for the item.
			boolean error = true;
			// Choice variable initialized, determiner of which item will be chosen
			int choice = 0;
			// While loop to ensure the user inputs the correct product ID
			while (error == true) {
				// Choice prompt
				System.out.println("Your choice: ");
				choice = input.nextInt();
				// Checks for errors if the choice prompt is inputed correctly.
				if (choice > 0 && choice <= inventory.size()) {
					// Error is now false and the while loop terminates itself
					error = false;
				} else {
					// Error message for when user puts invalid entry.
					System.out.println("Invalid item entry!");
				}
			}
			// Prompt that asks for number of units for the selected product.
			System.out.println("Number of units: ");
			// Quant is recorded from the user
			int quant = input.nextInt();
			// dateChecker is initialized and set to false for the below loop to use
			boolean dateChecker = false;
			// Checks to see if the date the user entered is valid.
			while (dateChecker == false) {
				// Prompt asking for date
				System.out
						.println("(If answered incorrectly at least once press enter before putting in a second date)");
				System.out.println("What is the date of your purchase? (mm/dd/yyyy)");
				// .nextLline() is put down twice since the loop will skip over the latter
				// method if the above isn't there. But if wrong once then both will be used.
				input.nextLine();
				// dateRecorded string used for conversion from string to a date object
				String dateRecorder = input.nextLine();
				// Checks to see if the string converter is true
				if (date.StringConverter(dateRecorder) == true) {
					// dateChecker is set to true and the loop ends
					dateChecker = true;
				} else {
					// An error message explaining the error from the date object is called and the
					// loop iterates again
					date.errorMessage();
				}
			}
			// Checks to see if the object is a 'Z' type. Also checks to see if the
			// 'ThirtyDayChance()' is true for the objects boolean variable 'setChance' to
			// true.
			// setChance is a variable that verifies if the seasonal sale can be initiated.
			if (inventory.get(choice - 1).getType() == 'Z' && date.ThirtyDayChance() == true) {
				// setChance is now true
				inventory.get(choice - 1).setChance(true);
			}

			// Adds the price of the selected inventory product to the overall price
			inventory.get(choice - 1).calcPrice(quant);
			// If statement for the scenario when the user adds more to their cart when they
			// want to activate the buy 2 get 1 free sale. Corrects the price
			Final.receipt(inventory.get(choice - 1));
			// Displays total price currently in the user's cart
			System.out.println("The total price is " + dollarsCents(UserPrice()));
			// Prompt that asks the user if they want to buy anything else
			System.out.println("Do you wish to buy anything else? (Enter y to continue)");
			// User inputs data and loop checks
			String letter;
			letter = input.nextLine();
			letter.toLowerCase();
			// Loop ends if 'y' isn't entered
			if (!(letter.equals("y"))) {
				cont = false;
			}
		}
		// Prints final price after all loops are done.
		System.out.println("Final price is " + dollarsCents(UserPrice()));
	}

	// Method used to convert the text file to Product objects
	public static void initializeInv(BufferedReader iReader) throws IOException {
		// Uses for loop to parse through all lines of text file
		for (int x = 0; x < count; x++) {
			// Temporary variable made to store line of text
			String read = iReader.readLine();
			// ID variable declared, will be used later on to declare a new product object
			int id = 0;
			// Name variable set to "" to avoid errors, will be set to product object later
			// on
			String name = "";
			// Type variable set to 'e' to let user know that the char variable wasn't
			// correctly initialized. Will change if text file is read correctly.
			char type = 'e';
			// Type price set to 0 to avoid errors, will be set to product object later on
			double price = 0;
			// Variable n is added with 1 depending on how many times the comma character is
			// detected.
			int n = 0;
			// FirstL initialized, will be later recorded to store the beginning of the id
			// variable
			int firstL = 0;
			// secL initialized, will be later recorded to store the beginning of the type
			// variable
			int secL = 0;
			// thirdL initialized, will be later recorded to store the beginning of the
			// price variable
			int thirdL = 0;
			// For loop for parsing through the 'read' variable
			for (int i = 0; i < read.length(); i++) {
				// Activated if the substring is equal to a comma character
				if (read.substring(i, i + 1).equals(",")) {
					// variable 'n' is incremented
					n++;
					switch (n) {
					// If n is 1, will run code to set the id variable
					case 1:
						// firstL is declared for later use
						firstL = i + 1;
						// idString stores the substring from read.
						String idString = read.substring(0, i);
						// id is set to idString parsed as an int
						id = Integer.parseInt(idString);
						break;
					// If n is 2, the code setting the name variable up will be activated
					case 2:
						// secL is declared for later use
						secL = i + 1;
						// name is set to the substring of firstL to i.
						name = read.substring(firstL, i);
						break;
					// If n is 3, the code setting the type variable will be activated
					case 3:
						// thirdl is declared for later use
						thirdL = i + 1;
						// typeString is activated to record the raw substring of read
						String typeString = read.substring(secL, i);
						// type is set to the char version of the substring, and used for declaring the
						// type of the object
						type = typeString.charAt(0);
						break;
					// If n is 4, then the code setting the price variable will be activated
					case 4:
						// Temporary string made to read the substring of the read variable
						String priceString = read.substring(thirdL, i);
						// price is declared after conversion of string to double
						price = Double.parseDouble(priceString);
						break;
					}

				}

			}
			// All variables are put into a new product that the inventory added
			switch (type) {
			// If the type is an 'X', then the product will be declared as a 'ProductX'
			case 'X':
				inventory.add(new ProductX(id, name, type, price));
				break;
			// If the type is an 'Y', then the product will be declared as a 'ProductY'
			case 'Y':
				inventory.add(new ProductY(id, name, type, price));
				break;
			// If the type is an 'Z', then the product will be declared as a 'ProductZ'
			case 'Z':
				inventory.add(new ProductZ(id, name, type, price));
				break;
			}

		}
	}

	// Method to print the inventory. Goes through the inventory array and activates
	// each product's "toString" method in the product class.
	public static void printMenu() {
		System.out.println("The inventory:");
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(inventory.get(i).toString());
		}

	}

	// Displays price
	public static String dollarsCents(double p) {
		int priceDollars = (int) p;
		long priceCents = Math.round((p - priceDollars) * 100);
		return priceDollars + " dollars and " + priceCents + " cents.";
	}

	// Checks if the file is valid
	public static boolean checkTextFile(String path) {
		// Declares new file variable to check to see if the path is valid
		File f = new File(path);
		// If f has a valid file name, it will go to next int statement
		if (f.exists() == true) {
			// If the file ends with txt, it will return true
			if (f.getName().endsWith(".txt")) {
				return true;
			}
		}
		return false;
	}

	// Calculates the final price of the order.
	public static double UserPrice() {
		// Fin initialized and adds every totalPrice variable from each product and is
		// returned.
		double fin = 0;
		for (int i = 0; i < inventory.size(); i++) {
			fin += inventory.get(i).getTotalCost();
		}
		return fin;
	}
}
