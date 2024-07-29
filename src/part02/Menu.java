package part02;

import console.Console;

/**
 * The Menu class represents a simple text menu with a title and a list of
 * items.
 */
public class Menu {

	private String items[]; // Store the menu items
	private String title; // Store the menu title

	/**
	 * Constructs a Menu object with the specified title and items.
	 *
	 * @param title The title of the menu.
	 * @param data  The array of items in the menu.
	 */
	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
	}

	/*
	 * Displays the menu title and options.
	 */
	private void display(Console con) {
		con.println(title);

		// Display a line of '+' characters under the title
		for (int count = 0; count < title.length(); count++) {
			con.print("+");
		}
		con.println();

		// Display the menu options with corresponding numbers
		for (int option = 1; option <= items.length; option++) {
			con.println(option + ". " + items[option - 1]);
		}

		con.println();
	}

	/**
	 * Gets the user's choice from the menu.
	 *
	 * @param con1 The primary console used for user interaction.
	 * @param con2 The secondary console used for error messages.
	 * @return The user's selected menu option.
	 */
	public int getUserChoice(Console con1, Console con2) {
		display(con1); // Display the menu options on the primary console
		boolean validInt = false;
		int value = 0;

		do {
			con1.print("Enter Selection: ");
			try {
				String valueString = con1.readLn(); // Read the user's input as a string
				value = Integer.parseInt(valueString); // Attempt to parse the string to an integer
				validInt = true; // Set the flag to true if an integer is successfully obtained
			} catch (NumberFormatException ex) {
				// Handle non-integer exceptions
				con2.println("Invalid input detected!"); // Display an error message on the secondary console
				pressReturnToContinue(con2); // Prompt the user to press RETURN to continue
			}
		} while (!validInt); // Repeat until a valid integer is obtained

		return value;
	}

	/**
	 * Displays a message prompting the user to press RETURN to continue, then waits
	 * for user input. This method is designed to work with two consoles
	 *
	 * @param con1 The first Console object
	 * @param con2 The second Console object
	 */
	public void pressReturnToContinue(Console con1, Console con2) {
		// Display prompt message on the first console
		con1.print("Press RETURN to continue.");

		// Make the first console visible to the user
		con1.setVisible(true);

		// Wait for user to press RETURN
		con1.readLn();

		// Hide the first console after user input
		con1.setVisible(false);

		// Clear the first console
		con1.clear();

		// Clear the second console
		con2.clear();
	}

	/**
	 * Displays a message prompting the user to press RETURN to continue, then waits
	 * for user input. This method is designed to work with only one console
	 *
	 * @param con1 The Console object
	 */
	public void pressReturnToContinue(Console con1) {
		// Display prompt message on the console
		con1.print("Press RETURN to continue.");

		// Make the console visible to the user
		con1.setVisible(true);

		// Wait for user to press RETURN
		con1.readLn();

		// Hide the console after user input
		con1.setVisible(false);

		// Clear the console
		con1.clear();
	}
}