//ICSI 201. Introduction to Computer Science, 
//Spring 2022
//Pandey, Abhineet Kumar
//Harrison McKenna
//001516105
package Project2;

public class User {
//name initialized for the user.
	private static String name;

//Constructor is called for the User
	public User(String n) {
		name = n;
	}

//toString returns the user's name.
	public String toString() {
		return name;
	}
}
