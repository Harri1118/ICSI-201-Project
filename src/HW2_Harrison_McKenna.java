import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HW2_Harrison_McKenna {
	// These booleans are used when the user inputs the month, year, or day wrong.
	private static boolean yearError;
	private static boolean monthError;
	private static boolean leapYearError;
	private static boolean outofDayError;

	// Variables for month, day, and year
	private static int month;
	private static int day;
	private static int year;

	/*
	 * zLooped is initialized later down in the code regarding a type z product. It
	 * controls the amount of times the program prompts the user to enter another
	 * product to get the buy 2 get one for free sale.
	 */
	private static int zLooped = 0;

	// Variable regarding the total overall price of the product.
	private static double price = 0;

	// Array that stores the 'product' objects that are in stock.
	private static ArrayList<product> inventory = new ArrayList<product>();

	// Loop that controls the whole program. If set to false, then the main loop
	// ends, thus terminating the program.
	private static boolean cont = true;

	// Counts the inventory products
	private static int count = 0;

	public static void main(String[] args) throws IOException {

		// Scanner object initialized.
		Scanner input = new Scanner(System.in);

		// Declared for user to enter file prompt
		String nameOfFile = "";
		//testName initiated to check for file name
		boolean testName = false;
		//While loop iterated checking for a valid file name
		while(testName == false) {
		System.out.println("Enter the filepath of the text file you wish to use for making the inventory:");
		//String used for fileName
		nameOfFile = input.nextLine();
		//If the text file name is true it will end loop, or else it will repeat itself
		if(checkTextFile(nameOfFile) == true) {
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

					error = false;
				} else {
					// Error message for when user puts invalid entry.
					System.out.println("Invalid item entry!");
				}
			}
			// Prompt that asks for number of units for the selected product.
			System.out.println("Number of units: ");
			int quant = input.nextInt();
			// Added is initialized for if the user selected a type 'Y' product.
			int added;
			input.nextLine();
			/*
			 * Maintainer variable initialized. I've had problems with the .nextLine method
			 * skipping over itself for unknown reasons. I found out that if I forced the
			 * program to prompt it a second time after it skips over the first .nextLine
			 * command, it will let the user actually input info.
			 */
			int maintainer = 0;
			// Checks for a type 'Y' item.
			if (inventory.get(choice - 1).getType() == 'Y') {
				// Boolean is initialized that controls the type Y loop.
				boolean restock = true;
				// This loop is made to prompt the user if they want more of the same product to
				// get a bigger sale off.
				do {
					// salesChanceMessages is called in order to display the appropriate message
					// regarding the quantity and price of the product.
					inventory.get(choice - 1).salesChanceMessages(quant);
					// Prompt asking the user if they would like more of the product they're buying.
					System.out.println(
							"Would you like to buy more of the current item you're buying? (type y to say yes)");
					// Letter initialized to record answer
					String letter;
					letter = input.nextLine();
					letter.toLowerCase();
					// Maintainer called for if the user goes through the loop again so the if
					// statement below can be activated.
					maintainer++;
					// If statement activated if the loop is parsed again
					if (maintainer > 1) {
						letter = input.nextLine();
						letter.toLowerCase();
					}
					// If the letter equals y, the loop starts again
					if (letter.equals("y")) {
						System.out.println("How much more would you like to buy?");
						// Added variable is then assigned a value and added to the quantity variable
						added = input.nextInt();
						quant = quant + added;
						// The item's internal quantity is then set to the quant variable to avoid stale
						// code.
						inventory.get(choice - 1).setQuant(quant);
						// Displays the total units in your shopping cart
						System.out.println(
								"Great! Now you have " + inventory.get(choice - 1).getQuant() + " of this item now!");
					} else {
						// If letter doesn't equal 'y', then the restock will be false and the loop will
						// end.
						restock = false;
					}
				} while (restock == true);
				// Checks for if the if the product type is 'Z'
			} else if (inventory.get(choice - 1).getType() == 'Z') {
				// mdy is initialized if the above 'if else' statement is activated.
				boolean mdy = false;
				do {
					// Prompts asking or month, day, and year. Will record the answers in the
					// variables previously mentioned at the beginning
					System.out.println("When do you plan your purchace?");
					System.out.println("Month of purchase (mm): ");
					month = input.nextInt();
					System.out.println("Day of purchase (dd): ");
					day = input.nextInt();
					System.out.println("Year of purchace (yyyy): ");
					year = input.nextInt();
					// validDate checks to see if day, month, and year are true.
					if (validDate(day, month, year) == true) {
						// If true, mdy is false.
						mdy = true;
						// This nextLine is activated because without it the loop terminates itself for
						// some reason.
						input.nextLine();
						// zLooped is changed to avoid the method getPriceByType in the object class to
						// add too much to the price variable in this class.
						zLooped++;
						// Activated if the user inputs the wrong date. errorMessage is called.
					} else if (validDate(day, month, year) == false) {
						System.out.println(errorMessage(day, month, year));
						// Reaffirms mdy is false. Without it the loop terminates itself.
						mdy = false;
					}
				} while (mdy == false);

			}
			// Adds the price of the selected inventory product to the overall price
			price = price + inventory.get(choice - 1).getPriceByType(quant);
			// If statement for the scenario when the user adds more to their cart when they
			// want to activate the buy 2 get 1 free sale. Corrects the price
			if (inventory.get(choice - 1).getType() == 'Z' && zLooped > 1 && inventory.get(choice - 1).getQuant() >= 2
					&& ThirtyDayChance() == true) {
				// subtracts by priceZ in order to get the sale to work.
				price = price - inventory.get(choice - 1).getPriceZ();
			}
			// Displays total price currently in the user's cart
			System.out.println("The total price is " + dollarsCents(price));
			// Prompt that asks the user if they want to buy anything else
			System.out.println("Do you wish to buy anything else? (Enter y to continue)");
			String letter;

			letter = input.nextLine();
			letter.toLowerCase();
			// Loop ends if 'y' isn't entered
			if (!(letter.equals("y"))) {
				cont = false;
			}
		}
		// Shows the receipt after the main loop is finished.
		System.out.println("Quantities: ");
		receipt();
		// Displays final price
		System.out.println("Final price is " + dollarsCents(price));
	}

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
			inventory.add(new product(id, name, type, price));
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

	/*
	 * Receipt is activated, and is a for loop that parses through each product in
	 * the inventory array. Displays quantity and individual price portion each
	 * product contributed to the main method's 'price' variable
	 */
	public static void receipt() {
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getQuant() > 0) {
				System.out.println(inventory.get(i).getQuant() + " of " + inventory.get(i).getName() + ": which costs "
						+ dollarsCents(inventory.get(i).getFinalPrice()));
			}
		}
	}

	// Displays price
	public static String dollarsCents(double p) {
		int priceDollars = (int) p;
		long priceCents = Math.round((p - priceDollars) * 100);
		return priceDollars + " dollars and " + priceCents + " cents.";
	}

	// Activated if the user is eligible for the seasonal sale. Checks for month and
	// days and returns true if they're within the date range.
	public static boolean ThirtyDayChance() {
		if (month == 9 || month == 10) {
			if ((day >= 15 && day <= 30) && month == 9) {
				return true;
			} else if ((day >= 1 && day <= 15) && month == 10) {

				return true;
			}
		}
		return false;
	}
	
	//Checks if the file is valid
	public static boolean checkTextFile(String path) {
		//Declares new file variable to check to see if the path is valid
	    File f = new File(path);
	    //If f has a valid file name, it will go to next int statement
	    if(f.exists() == true) {
	    //If the file ends with txt, it will return true
	    	if(f.getName().endsWith(".txt")) {
	    		return true;
	    	}
	    }
	    return false;
	  }
	
	// Checks if the user entered a valid date.
	public static boolean validDate(int d, int m, int y) {
		if (y < 2021 || y > 2050) {
			yearError = true;
			return false;
		} else if (m < 1 || m > 12) {
			monthError = true;
			return false;
		}
		if ((m >= 1 && m <= 12) && (y >= 2021 && y <= 2050)) {
			/*
			 * Will be used to determine the size of the day variables. Will be used to
			 * check its validity depending on the month
			 */
			int maxDays = 0;
			/*
			 * Used a switch method for m, which is month. If it is 1, 12, or anything in
			 * between. It will activate one of these cases.
			 */
			switch (m) {
			/*
			 * Max days is set, monthName is set to the respective month name of each case,
			 * and then the case breaks. Thus escaping the case function.
			 */
			case 1:
				maxDays = 31;
				break;
			case 2:
				/*
				 * Since Febuary changed based off the leap year, the max day changes to 29
				 * instead of 28 if depending if year % 4 = 0.
				 */
				if ((y % 4) == 0) {
					maxDays = 29;
				} else {
					maxDays = 28;
				}
				break;
			case 3:
				maxDays = 31;
				break;
			case 4:
				maxDays = 30;
				break;
			case 5:
				maxDays = 31;
				break;
			case 6:
				maxDays = 30;
				break;
			case 7:
				maxDays = 31;
				break;
			case 8:
				maxDays = 31;
				break;
			case 9:
				maxDays = 30;
				break;
			case 10:
				maxDays = 31;
				break;
			case 11:
				maxDays = 30;
				break;
			case 12:
				maxDays = 31;
				break;
			}
			/*
			 * Checks to see if day variable is valid based off the maxDays variable. If it
			 * is it will return true
			 */
			if (d >= 1 && d <= maxDays) {
				return true;
				// This loop activates if Febuary 29th is entered when the year isn't a leap
				// year.
			} else if (d == 29 && month == 2 && (y % 4 != 0)) {
				leapYearError = true;
				return false;
				// Activates if the wrong amount of days possible is inputed.
			} else if (d > maxDays || d < 1) {
				outofDayError = true;
				return false;
			}
		}
		// Returns false if any one of the if statements isn't true.
		return false;
	}

	// errorMessages is activated in order to inform the user why their date is
	// incorrect
	public static String errorMessage(int d, int m, int y) {
		// Checks to see if the date the user entered is a leap year or not.
		if (leapYearError == true) {
			return "You have inputted the incorrect year for this day to be possible!";
			// Activated if the outofDayError is true
		} else if (outofDayError == true) {
			if (d > 31) {
				return "Your day is over the month limit!";
			} else if (d < 1) {
				return "Your day is too low! Must be over 1!";
			}
			// Activated if the yearError variable is true
		} else if (yearError == true) {
			if (y < 2021) {
				return "Year is too low! Cannot be below 2021!";
			} else if (y > 2050) {
				return "Year is too high! Cannot be above 2050!";
			}
			// Activated if the monthError variable is true
		} else if (monthError == true) {
			if (m > 12) {
				return "Month is too high! Must input between 1 or 12.";
			} else if (m < 1) {
				return "Month is too low! Must input between 1 or 12.";
			}
		}
		// Activated if somehow the user inputs everything wrong.
		return "Invalid format!";
	}

}
