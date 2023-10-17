//ICSI 201. Introduction to Computer Science, 
//Spring 2022
//Pandey, Abhineet Kumar
//Harrison McKenna
//001516105
package Project2;

public class ProductY extends Product {
	// product y constructor
	public ProductY(int i, String n, char t, double p) {
		super(i, n, t, p);
	}

//CalcPrice overrided to make way for type y product prices
	@Override
	public void calcPrice(int q) {
//Quant is set before anything to avoid stale data
		setQuant(getQuant() + q);
//Activated if quant variable is under 100
		if (getQuant() < 100) {
// Total cost is equal to price and quantity to avoid stale data.
			setTotalCost(getQuant() * getPrice());
		}
// Activated if q argument is between 99 and 500.
		else if (getQuant() >= 100 && getQuant() < 500) {
// truerPrice initiated in order to organize equation calculating the price of
// the unit in each if statement
			double truerPrice = getPrice() * .95;
//Cents converter called in order to calculate only the first two decimals. This is then set to truerPrice
			truerPrice = centsConverter(truerPrice);
			setTotalCost(getQuant() * truerPrice);

		}
// Activated if q argument is between 499 1500
		else if (getQuant() >= 500 && getQuant() < 1500) {
			double truerPrice = getPrice() * .85;
			truerPrice = centsConverter(truerPrice);
			setTotalCost(getQuant() * truerPrice);

		}
// Activated if q is greater than or equal to 1500
		else if (getQuant() >= 1500) {
			double truerPrice = getPrice() * .75;
			truerPrice = centsConverter(truerPrice);
			setTotalCost(getQuant() * truerPrice);

		}
	}

//centsConverter used to convert prices
	public double centsConverter(double c) {
//remover initialized to cut off the cents section. newer to be equal to the new value as a string form
		String remover = Double.toString(c);
		String newer = "";
//f initialized to be returned at the end
		double f;
//Parser set to 0 in order to detect end of the cents section
		int parser = 0;
//For loop to parse through remover vairable
		for (int i = 0; i < remover.length(); i++) {
//Activated when the decimal is detected
			if (remover.substring(i, i + 1).equals(".")) {
//Added 3 to parser
				parser = i + 3;
//Newer is then set to only the cents
				newer = remover.substring(i + 1, remover.length());
				break;
			}
		}
//If newer's length is more than 2, then this is activated
		if (newer.length() > 2) {
//fs is a new String initialized in order to be the final product, parser is called as well from before
			String fs = remover.substring(0, parser);
//f is set as final product and returned only if newer's length is more than 2
			f = Double.valueOf(fs);
			return f;
		}
//Returns the parameter if newer's length is 2 or under.
		return c;
	}
}
