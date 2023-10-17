//ICSI 201. Introduction to Computer Science, 
//Spring 2022
//Pandey, Abhineet Kumar
//Harrison McKenna
//001516105
package Project2;

/**
 *
 * @author harri
 */
public class ProductZ extends Product {

	// Constructor for ProductZ
	public ProductZ(int i, String n, char t, double p) {
		super(i, n, t, p);
	}

	@Override
	public void calcPrice(int q) {

		// q is added to quant to avoid stale data
		setQuant(getQuant() + q);
		// newQuant initialized as the quant, will be changed if the thirty day sale is
		// possible and quant is more than 2.
		int newQuant = getQuant();
		// If statement is activated if ThirtyDayChance is true
		if (getChance() == true && getQuant() >= 3) {
			// Newer initialized and equal to getCalc with the object's quant parameter put
			// in.
			int newer = getCalc(getQuant());
			// newQuant is set to quant minus the newer in order for the sale to work.
			newQuant = getQuant() - newer;
		}
		// Final command regardless if the if statement occurs.
		setTotalCost(getPrice() * newQuant);
	}

//getCalc method initiated with a q parameter to determine how much money should be taken off the price
	public int getCalc(int q) {
//fin initialized to 0
		int fin = 0;
//For loop initiated to parse through q in order to see how many times quant has divisible by 3 numbers
		for (int i = 0; i <= q; i++) {
//If statement counts divisible by 3 numbers except 0.
			if (i % 3 == 0 && i != 0) {
				fin++;
			}
		}
		return fin;
	}

}
