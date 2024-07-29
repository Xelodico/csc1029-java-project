package part01;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Menu class represents a simple text menu with a title and a list of
 * items.
 */
public class Menu {

	private String items[]; // Store the menu items
	private String title; // Store the menu title
	private Scanner input; // Scanner for user input

	/**
	 * Constructs a Menu object with the specified title and items.
	 *
	 * @param title The title of the menu.
	 * @param data  The array of items in the menu.
	 */
	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in);
	}

	/**
	 * Displays the menu title and options.
	 */
	private void display() {
		System.out.println(title);

		// Display a line of '+' characters under the title
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}
		System.out.println();

		// Display the menu options with corresponding numbers
		for (int option = 1; option <= items.length; option++) {
			System.out.println(option + ". " + items[option - 1]);
		}

		System.out.println();
	}

	/**
	 * Gets the user's choice from the menu.
	 *
	 * @return The user's selected menu option.
	 */
	public int getUserChoice() {
		display(); // Display the menu options
		boolean validInt = false;

		// Initialise the user's selected menu option
		int value = 0;

		do {
			System.out.print("Enter Selection: ");
			try {
				value = input.nextInt(); // Attempt to read an integer from the user
				validInt = true; // Set the flag to true if an integer is successfully read
			} catch (InputMismatchException ex) {
				// Handle non-integer exceptions
				System.err.println("Invalid input detected!");
				input.nextLine(); // Clear the input buffer to avoid an infinite loop
			}
		} while (!validInt); // Repeat until a valid integer is obtained

		return value;
	}
}
