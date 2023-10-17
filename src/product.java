public class product {
//id, name, price, and type variables all stores as basic variables in order for the object to be valid.
private int id;
private String name;
private double price;
private char type;
//Total quantity of requested objects of any given object. Useful for the receipt method in the Driver class
private int quant = 0;
//Total cost is initialized to record the overall cost of the quantity of objects (including the sales).
private double totalCost = 0;
//Is equal to the price of the overall type z quantity if the object is type z. Helps maintain the buy 2 get 1 free sale.
static private double priceTypeZ;
//Constructor of the item class
public product(int i, String n, char t, double p) {
id = i;
name = n;
type = t;
price = p;
}
//Determiner of the overall price of the object.
public double getPriceByType(int q) {
// If the type variable is 'Y', then this if statement will be activated.
if (type == 'Y') {
// If the q argument is less than 100, then this loop activates.
if (q < 100) {
// Total cost is equal to price and quantity to avoid stale data.
totalCost = (price * quant);
// returns price times quantity for any requested unit of item(s)
return price * q;
}
// Activated if q argument is between 99 and 500.
else if (q >= 100 && q < 500) {
// truerPrice initiated in order to organize equation calculating the price of
// the unit
double truerPrice = price * .95;
totalCost = (quant * truerPrice);
// Returns 5% off
return (price * q) * .95;
}
// Activated if q argument is between 499 1500
else if (q >= 500 && q < 1500) {
totalCost = ((price * quant) * .85);
// Returns 15% off
return (price * q) * .85;
}
// Activated if q is greater than or equal to 1500
else if (q >= 1500) {
totalCost = ((price * quant) * .75);
// Returns 75% off
return (price * q) * .75;
}
}
// Activated if unit type is z
else if (type == 'Z') {
// If statement is activated if ThirtyDayChance is true
if (HW2_Harrison_McKenna.ThirtyDayChance() == true) {
// q is added to quant to avoid stale data
quant = quant + q;
// Activated to check if the quantity of the given product is qualified to get the
// 2 for 1 discount
if (quant >= 3) {
// Notifies the price has been discounted
System.out.println("Your price is discounted!");
// trueQuant is initiated to be a third of the total quantity
int trueQuant = (int) Math.ceil(quant / 3);
// quant is subtracted by trueQuant to activate sale
quant = quant - trueQuant;
// Total cost is updated with new quant value in order to avoid stale data.
totalCost = (price * quant);
// priceTypeZ is recorded in order to remove any risk of the price being too
// high if the user activates this method again
priceTypeZ = price * quant;
return price * quant;
}
// Activates if quant isn't divisible by 3
else {
// Subtracts q from quant so the default return is activated and the user won't
// be over priced
quant = quant - q;
System.out.println("You're eligible for a buy 2 get 1 free of type z items!");
}
}
}
// What type x items and non-seasonal z items reach in this method
quant = quant + q;
totalCost = price * quant;
return price * q;
}
//Prints sales chances if the user is selecting an item of the y type.
public void salesChanceMessages(int q) {
//Activates if q is less than 100
if (q < 100) {
//Shows 5% off unit price
double fin = price * .95;
System.out.println("If you buy more than 100 units, you will pay " +
dollarsCents(fin) + " per unit instead of paying " + dollarsCents(price) + " per unit.");
}
//Activates if q is between 99 and 500
else if (q >= 100 && q < 500) {
//Shows 15% off unit price
double fin = price * .85;
System.out.println("If you buy more than 500 units, you will pay " +
dollarsCents(fin)
+ " per unit instead of paying " + dollarsCents(price) + " per unit.");
}
//Activates if q is between 499 and 1500
else if (q >= 500 && q < 1500) {
// Shows 5% off unit price
double fin = (price * .75);
System.out.println("If you buy more than 1500 units, you will pay " +
dollarsCents(fin)
+ " per unit instead of paying " + dollarsCents(price) + " per unit.");
}
}
//toString method
public String toString() {
return id + " " + name + " " + price + " " + type;
}
//dollarsCents method initialized for the salesChanceMessages method to use.
private String dollarsCents(double p) {
int priceDollars = (int) p;
long priceCents = Math.round((p - priceDollars) * 100);
return priceDollars + " dollars and " + priceCents + " cents";
}
//Setters and getters
public void setId(int i) {
id = i;
}
public int getId() {
return id;
}
public void setName(String n) {
name = n;
}
public String getName() {
return name;
}
public void setPrice(double p) {
price = p;
}
public double getPrice() {
return price;
}
public void setType(char t) {
type = t;
}
public char getType() {
return type;
}
public void setQuant(int i) {
quant += i;
}
public int getQuant() {
return quant;
}
public double getFinalPrice() {
return totalCost;
}
public double getPriceZ() {
return priceTypeZ;
}
}