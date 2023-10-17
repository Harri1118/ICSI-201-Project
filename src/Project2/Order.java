//ICSI 201. Introduction to Computer Science, 
//Spring 2022
//Pandey, Abhineet Kumar
//Harrison McKenna
//001516105
package Project2;

public class Order {
//User and date variables initialized in this class
	private static User user;
	private static Date date = new Date();

//Order constructor setting up who the user is.
	public Order(User u) {
		user = u;
	}

	// Prints sales chances if the user is selecting an item of the y type.
	public static void salesChanceMessages(int q, double p) {
//Activates if q is less than 100
		if (q < 100) {
//Shows 5% off unit price
			double fin = p * .95;
			System.out.println("If you buy more than 100 units, you will pay " + dollarsCents(fin)
					+ " per unit instead of paying " + p + " per unit.");
		} // Activates if q is between 99 and 500
		else if (q >= 100 && q < 500) {
//Shows 15% off unit price
			double fin = p * .85;
			System.out.println("If you buy more than 500 units, you will pay " + dollarsCents(fin)
					+ " per unit instead of paying " + p + " per unit.");
		} // Activates if q is between 499 and 1500
		else if (q >= 500 && q < 1500) {
// Shows 5% off unit price
			double fin = p * .75;
			System.out.println("If you buy more than 1500 units, you will pay " + dollarsCents(fin)
					+ " per unit instead of paying " + p + " per unit.");
		}
	}

	// DollarsCents method
	public static String dollarsCents(double p) {
		int priceDollars = (int) p;
		long priceCents = Math.round((p - priceDollars) * 100);
		return priceDollars + " dollars and " + priceCents + " cents.";
	}

	/*
	 * Receipt is activated, and prints the product being bought and how much it
	 * would cost, as well as the user's name and date.
	 */

	public static void receipt(Product p) {
		// line, cieling, and heading initialized and set to strings to make printing
		// the receipt easier.
		String line = "***********************************************";
		String cieling = "+----+--------------+------+-----+---------+";
		String heading = "|ID | Name          |Price |Type |Quantity |";
		System.out.println(line);
		System.out.println("Date: " + date.toString());
		System.out.println(line);
		System.out.println(user.toString() + "\nYour item:");
		System.out.println(cieling);
		System.out.println(heading);
		System.out.println(cieling);
		System.out.println("|" + p.getId() + "   |" + cutOffBlank(p.getName()) + "|$" + p.getPrice() + "  |"
				+ p.getType() + "    |" + p.getQuant() + "        |");
		System.out.println(cieling);
		System.out.println("In the amount of " + p.getQuant());
		if (p.getType() == 'Y') {
			salesChanceMessages(p.getQuant(), p.getPrice());
		}

		System.out.println(line);
	}

	// cutOffBlank method initialized to get rid of blank spaces in the name of
	// Product p. As the menu part of the project was lined up using tabs
	public static String cutOffBlank(String n) {
		// Newer initialized to be set to n. Will be modified later
		String newer = n;
		// For loop established to check for tabs
		for (int i = 0; i < n.length() - 1; i++) {
			// If statement established to get rid of tabs if it detects one.
			if (n.substring(i, i + 1).equals("\t")) {
				newer = n.substring(0, i);
			}
		}
		// If the length of newer is less than 14, then it will add spaces for the
		// product to line up
		if (newer.length() < 14) {
			// P initialized to estimate how many times the loop needs to iterate in order
			// to reach alignment.
			int p = 14 - newer.length();
			for (int i = 0; i < p; i++) {
				// Added space to newer
				newer += " ";
			}
		}
		// Newer returned
		return newer;
	}

}
