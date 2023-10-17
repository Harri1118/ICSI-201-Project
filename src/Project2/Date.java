//ICSI 201. Introduction to Computer Science, 
//Spring 2022
//Pandey, Abhineet Kumar
//Harrison McKenna
//001516105
package Project2;

public class Date {

//Month, day, and year variables initialized
	public static int month = 0;
	public static int day = 0;
	public static int year = 0;

// These booleans are used when the user inputs the month, year, or day wrong.
	private boolean yearError = false;
	private boolean monthError = false;
	private boolean leapYearError = false;
	private boolean outofDayError = false;

	public Date() {

	}

//StringConverter initialized to convert the input of the user to month, day, and year
	public boolean StringConverter(String in) {
//If statement that detects if the input has breaks between the numbers.
		if (in.contains(" ") || in.contains("/") || in.contains("-")) {
//spaceCount initialized to detect whether its time to change th eday, month, or year variable
			int spaceCount = 0;
//monthEnd initialized
			int monthEnd = 0;
//dayEnd initialized
			int dayEnd = 0;
			for (int i = 0; i < in.length(); i++) {
				// Detects if the for loop has reached a break.
				if (in.substring(i, i + 1).equals(" ") || in.substring(i, i + 1).equals("-")
						|| in.substring(i, i + 1).equals("/")) {

					// Initialized if spaceCount is 0
					if (spaceCount == 0) {
						/*
						 * Using paseInt method to turn the substring where the number is between 0 and
						 * i to turn into an int. Thus getting the month.
						 */
						month = Integer.parseInt(in.substring(0, i));
						// Made monthend to i + 1 since the i + 1 substring is the space between the
						// numbers.
						monthEnd = i + 1;
						// Added spacecount to not repeat this if statement
						spaceCount++;
						// This part will assign numbers to the month and year variables.
					} else if (spaceCount == 1) {
						/*
						 * Made day equal to everything between month end (start of day section) to i
						 * (end of day)
						 */
						day = Integer.parseInt(in.substring(monthEnd, i));
						// sets day end to skip the space and go into year section.
						dayEnd = i + 1;
						// Year is the substring between the start of the year section to the end of the
						// string.
						year = Integer.parseInt(in.substring(dayEnd, in.length()));
					}
				}

			}
//Calls validDate to check if the month, day, and year variables are true. If they are then the method will return true.
			if (validDate() == true) {
				return true;
			}
		}
//Returns false if not true
		else {
			return false;
		}
		return false;
	}

// Activated if the user is eligible for the seasonal sale. Checks for month and
	// days and returns true if they're within the date range.
	public boolean ThirtyDayChance() {
		if (month == 9 || month == 10) {
			if ((day >= 15 && day <= 30) && month == 9) {
				return true;
			} else if ((day >= 1 && day <= 15) && month == 10) {

				return true;
			}
		}
		return false;
	}

//validDate checks to see if the variables are true, and then will switch the error booleans on if they aren't.
	public boolean validDate() {
		/*
		 * This if statement checks to see if the month and year are in the correct
		 * parameters in the assignment instructions.
		 */
		// Checks for correct year
		if (year < 2022 || year > 2055) {
			yearError = true;
			return false;
			// Checks for correct month
		} else if (month < 1 || month > 12) {
			monthError = true;
			return false;
		}

		/*
		 * Will be used to determine the size of the day variables. Will be used to
		 * check its validity depending on the month
		 */
		int maxDays = 0;
		/*
		 * Used a switch method for m, which is month. If it is 1, 12, or anything in
		 * between. It will activate one of these cases.
		 */
		switch (month) {
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
			if ((year % 4) == 0) {
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
		 * is it will return true. It also checks for leapYear errors if the month is
		 * set for Febuary.
		 */
		if (day >= 1 && day <= maxDays) {
			return true;
		} else if (day == 29 && month == 2 && (year % 4 != 0)) {
			leapYearError = true;
			return false;
		} else if (day > maxDays || day < 1) {
			outofDayError = true;
			return false;
		}

		// Returns false if any one of the if statements isn't true.
		return false;
	}

// errorMessages is activated in order to inform the user why their date is
	// incorrect
	public void errorMessage() {
		// Checks to see if the date the user entered is a leap year or not.
		if (leapYearError == true) {
			System.out.println("You have inputted the incorrect year for this day to be possible!");
			leapYearError = false;
			// Activated if the outofDayError is true
		} else if (outofDayError == true) {
			if (day > 31) {
				System.out.println("Your day is over the month limit!");
			} else if (day < 1) {
				System.out.println("Your day is too low! Must be over 1!");
			}
			outofDayError = false;
			// Activated if the yearError variable is true
		} else if (yearError == true) {
			if (year < 2021) {
				System.out.println("Year is too low! Cannot be below 2021!");
			} else if (year > 2050) {
				System.out.println("Year is too high! Cannot be above 2050!");
			}
			yearError = false;
			// Activated if the monthError variable is true
		} else if (monthError == true) {
			if (month > 12) {
				System.out.println("Month is too high! Must input between 1 or 12.");
			} else if (month < 1) {
				System.out.println("Month is too low! Must input between 1 or 12.");
			}
			monthError = false;
		} else {
			System.out.println("Invalid format!");
		}
	}

//toString method for the Order class to use.
	public String toString() {
		return month + "/" + day + "/" + year;
	}
}
