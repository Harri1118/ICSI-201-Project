//ICSI 201. Introduction to Computer Science, 
//Spring 2022
//Pandey, Abhineet Kumar
//Harrison McKenna
//001516105
package Project2;

public class Product {
//id, name, price, type, and quant variables all stores as basic variables in order for the object to be valid.
	private int id;
	private String name;
	private double price;
	private char type;
	private int quant = 0;

//totalCost initialized to return the whole price of the order.
	private double totalCost;
//chance boolean initialized to false for the program to switch if thhe thirty day sale is possible
	private boolean chance = false;

//Product constructor
	public Product(int i, String n, char t, double p) {
		id = i;
		name = n;
		type = t;
		price = p;
	}

//calcPrice used to calculate the total cost of the object.
	public void calcPrice(int q) {
		quant += q;
		totalCost = price * q;
	}

//Displays the object as a string
	public String toString() {
		return id + " " + name + " " + price + " " + type;
	}

//dollarsCents method initialized for the salesChanceMessages method to use.
	public String dollarsCents(double p) {
		int priceDollars = (int) p;
		long priceCents = Math.round((p - priceDollars) * 100);
		return priceDollars + " dollars and " + priceCents + " cents";
	}

//Setters and getters for quant, type, price, name, totalCost, id, and chance boolean
	public void setQuant(int q) {
		quant = q;
	}

	public int getQuant() {
		return quant;
	}

	public char getType() {
		return type;
	}

	public void setPrice(double p) {
		price = p;
	}

	public double getPrice() {
		return price;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double p) {
		totalCost = p;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setChance(boolean b) {
		chance = b;
	}

	public boolean getChance() {
		return chance;
	}
}