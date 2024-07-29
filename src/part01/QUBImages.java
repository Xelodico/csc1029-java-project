package part01;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.lang.IndexOutOfBoundsException;

/**
 * The QUBImages class serves as the main application class for the ImageManager
 */
public class QUBImages {

	// Static menu options and menu instance
	static String[] menuOptions = { "Add Image", "Search", "Display All", "Exit" };
	static Menu myImagesMenu = new Menu("QUB Images", menuOptions);

	// Static ImageManager instance to manage images
	static ImageManager myImages = initialiseImageManager();

	// Static scanner for user input
	static Scanner input = new Scanner(System.in);

	/**
	 * Main method
	 * 
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		int choice = 0;
		// Loop the application until the user chooses to quit (defined by
		// menuOptions.length)
		do {
			choice = myImagesMenu.getUserChoice(); // Get user choice from the menu
			performUserOperation(choice); // Perform the operation chosen by the user
		} while (choice != menuOptions.length);

		// Display goodbye message before quitting the application
		System.out.println("\nGoodbye!");

	}

	/**
	 * Perform the operation corresponding to the user's menu choice
	 *
	 * @param option The user's menu choice
	 */
	public static void performUserOperation(int option) {
		// Flag to loop the operation until a valid operation is made
		boolean isInRange = false;
		do {
			switch (option) {
			case 1:
				addImage(); // Call method to add an image
				isInRange = true; // Exit loop since action is performed
				break;
			case 2:
				searchImage(); // Call method to search for an image
				isInRange = true; // Exit loop since action is performed
				break;
			case 3:
				displayAllImages(); // Call method to display all images
				isInRange = true; // Exit loop since action is performed
				break;
			case 4:
				isInRange = true; // Exit loop since user chose to quit
				break;
			default:
				// Handle cases where the user enters an integer outside the menu range
				option = printInvalidRangeMessage(myImagesMenu);
				break;
			}
		} while (!isInRange); // Continue looping until valid choice is made
	}

	/**
	 * Display an introductory message with a specified title
	 *
	 * @param msg The message to display
	 */
	public static void printIntroMessage(String msg) {
		System.out.println("\n" + msg);
		for (int i = 0; i < msg.length(); i++) {
			System.out.print("+");
		}

		// Move to the next line after printing the line of '+'s
		System.out.println();
	}

	/**
	 * Display an error message indicating that the user's input is not within the
	 * valid range, then prompt to try again
	 *
	 * @param men The Menu instance for which an invalid range message is displayed
	 * @return The user's choice after the error message is displayed
	 */
	public static int printInvalidRangeMessage(Menu men) {
		System.err.println("Not within the range. Try again.\n"); // Error message
		return men.getUserChoice(); // Prompt user again for valid choice
	}

	/**
	 * Prompt the user to enter a genre and return the entered value
	 *
	 * @return The entered genre
	 */
	public static String selectGenre() {
		System.out.print(
				"Enter a genre (Astronomy, architecture, sport, landscape, portrait, nature, aerial, food, or other): ");
		return input.nextLine().trim();
	}

	/**
	 * Add a new image to the ImageManager. Guide the user through entering metadata
	 * for the new image, including title, description, genre, thumbnail, and date
	 * taken
	 */
	public static void addImage() {
		printIntroMessage("Let's add a new image!");

		// Prompt user to enter the metadata of the image they wish to add
		System.out.print("Enter the title: ");
		String title = input.nextLine().trim();

		System.out.print("Enter the description: ");
		String desc = input.nextLine().trim();

		String genre = selectGenre();

		System.out.print("Enter the thumbnail: ");
		String thumbnail = input.nextLine().trim();

		System.out.print("Enter the date it was taken (YYYY-MM-DD): ");
		String date = input.nextLine().trim();

		try {
			// Create a new ImageRecord instance with the entered metadata
			ImageRecord newImage = new ImageRecord(title, desc, genre, date, thumbnail);

			// Add the new image to the ImageManager
			myImages.addImage(newImage);

			// Print success message
			System.out.println("New image added!\n");
		} catch (Exception e) {
			// Handle exceptions (when validation has failed) by displaying an error message
			System.err.println("Sorry, an error occured: " + e.getMessage());
		}

	}

	/**
	 * Begin searching for an image
	 */
	public static void searchImage() {
		System.out.println();

		// Search options and search menu instance
		String[] searchMenuOptions = { "ID", "Title", "Description", "Genre", "Dates", "Exit" };
		Menu searchMenu = new Menu("Let's search for an image!", searchMenuOptions);

		// Variable to store the user's chosen search operation
		int choice = 0;
		do {
			choice = searchMenu.getUserChoice(); // Get user choice from the search menu
			performSearch(choice, searchMenu); // Perform the chosen search operation
		} while (choice != searchMenuOptions.length); // Loop the search operation until the user decides to quit
	}

	/**
	 * Perform the specific search operation based on the user's choice
	 *
	 * @param option     The user's search menu choice
	 * @param searchMenu The Menu object for the search options
	 */
	public static void performSearch(int option, Menu searchMenu) {
		boolean isInRange = false;
		do {
			switch (option) {
			case 1:
				searchId(); // Call method to search by image ID
				isInRange = true; // Exit loop since action is performed
				break;
			case 2:
				searchTitleDescriptionGenre("title"); // Call method to search by title
				isInRange = true; // Exit loop since action is performed
				break;
			case 3:
				searchTitleDescriptionGenre("description"); // Call method to search by description
				isInRange = true; // Exit loop since action is performed
				break;
			case 4:
				searchTitleDescriptionGenre("genre"); // Call method to search by genre
				isInRange = true; // Exit loop since action is performed
				break;
			case 5:
				searchDates(); // Call method to search by dates
				isInRange = true; // Exit loop since action is performed
				break;
			case 6:
				System.out.println("Returning to main menu...\n");
				isInRange = true; // Exit loop since user chose to quit
				break;
			default:
				// Handle cases where the user enters an integer outside the menu range
				option = printInvalidRangeMessage(searchMenu);
				break;
			}
		} while (!isInRange);
	}

	/**
	 * Search for images based on title, description, or genre
	 *
	 * @param optionMsg The type of search (title, description, or genre)
	 */
	public static void searchTitleDescriptionGenre(String optionMsg) {
		// Display an introductory message for the specific search type
		printIntroMessage("Search by " + optionMsg);

		// Initialise variables to store the user's target and genre
		String target = null;
		ImageType targetGenre = null;

		// Using the default constructor, create a temporary ImageRecord object for
		// accessing utility methods
		ImageRecord temp = new ImageRecord();

		// Check if the search type is for the genre
		if (optionMsg == "genre") {
			target = selectGenre(); // Prompt user to select a genre
			targetGenre = temp.setGenre(target); // Set the target genre using the temporary ImageRecord object
		} else {
			System.out.print("Enter " + optionMsg + ": ");
			target = input.nextLine(); // Prompt user to enter the target (title or description)
		}

		// Initialise an ImageAlbum instance to store the matching images
		ImageAlbum matchingImages = null;

		// Perform the specific search based on the search type
		switch (optionMsg) {
		case "title":
			matchingImages = myImages.searchTitle(target);
			break;
		case "description":
			matchingImages = myImages.searchDescription(target);
			break;
		case "genre":
			matchingImages = myImages.searchGenre(targetGenre);
			break;
		}

		// Display the search results
		printSearchResult(matchingImages, optionMsg, target);

		// Release resources by setting the temporary object to null
		temp = null;

	}

	/**
	 * Search for an image by its ID Prompt the user to enter an ID and validate the
	 * input Then display information about the found image if it exists, otherwise,
	 * show an error message
	 */
	public static void searchId() {
		// Display an introductory message for searching by image ID
		printIntroMessage("Search by image ID");

		// Flag to track whether the user has entered a valid integer
		boolean validInt = false;

		int id = 0;

		// Prompt the user to enter an ID until a valid integer is provided
		do {
			System.out.print("Enter an ID: ");
			try {
				id = input.nextInt();
				validInt = true; // Set the flag to true if an integer is successfully read
			} catch (InputMismatchException ex) {
				// Handle any non-integer exceptions
				System.err.println("Invalid input detected!");
				input.nextLine(); // Clear the input buffer to avoid an infinite loop
			}
		} while (!validInt);

		// Search for the image with the specified ID in the ImageManager
		ImageRecord targetImage = myImages.searchId(id);

		// Display the search results based on whether an image was found or not
		if (targetImage != null) {
			System.out.println("\nImage with ID " + id + " was found!\n\n" + targetImage.getDetails());
		} else {
			System.out.println("\nERROR: No image found with ID " + id + ".\n");
		}

	}

	/**
	 * Display the search results based on the number of matching images and the
	 * search criteria
	 *
	 * @param matchingImages The ImageAlbum containing the matching images
	 * @param optionMsg      The type of search criteria (title, description, genre)
	 * @param target         The user-specified target for the search
	 */
	public static void printSearchResult(ImageAlbum matchingImages, String optionMsg, String target) {
		if (matchingImages.getAlbum().size() == 1) {
			// Display a message for a single matching image
			System.out.println("\n1 image with " + optionMsg + " '" + target + "' found!\n");
			// Display the details of the matching image
			displayImages(matchingImages);
		} else if (matchingImages.getAlbum().size() > 0) {
			// Display a message for multiple matching images
			System.out.println(
					"\n" + matchingImages.getAlbum().size() + " images with " + optionMsg + " '" + target + "' found!");
			// Call method to navigate through the matching images
			navigateImages(matchingImages);
		} else {
			// Display an error message if no matching images are found
			System.err.println("\nERROR: No image found with " + optionMsg + " '" + target + "'.\n");
		}
	}

	/**
	 * Displays and navigates through images in the given ImageAlbum
	 *
	 * @param alb The ImageAlbum containing a collection of images
	 */
	public static void navigateImages(ImageAlbum alb) {

		for (int i = 0; i < alb.getAlbum().size(); i++) {
			// Print brief details of each image
			System.out.println(alb.getAlbum().get(i));
		}

		// Display introductory message with the current image number
		printIntroMessage("Displaying image " + (alb.getCurrentImageValue() + 2));

		// Display information about the first image
		System.out.println(alb.getFirst().getDetails());

		int choice = 0;

		do {
			// Prompt the user to select an option
			System.out.print("Select 1 for next image, 2 for previous image, or 3 to exit: ");
			try {
				// Read the user's choice
				choice = input.nextInt();
				input.nextLine(); // Clear the input buffer

				switch (choice) {
				case 1:
					// Display the next image if available
					ImageRecord nextImage = alb.getNext();
					displayNextOrPreviousImage(alb, nextImage);
					break;
				case 2:
					// Display the previous image if available
					ImageRecord previousImage = alb.getPrevious();
					displayNextOrPreviousImage(alb, previousImage);
					break;
				case 3:
					// Exit the loop
					System.out.println("Exiting...\n");
					break;
				default:
					// If the user enters an integer that is out of bounds, prompt again
					System.err.println("Not within the range. Try again.");
				}
			} catch (InputMismatchException ex) {
				// Inform the user about invalid input and clear the input buffer
				System.err.println("Invalid input detected!");
				input.nextLine();
			} catch (IndexOutOfBoundsException oob) {
				// Inform the user about an error and print the stack trace for debugging
				System.err.println("ERROR: ");
				oob.printStackTrace();
			}
		} while (choice != 3); // Continue looping until the user chooses to exit
	}

	/**
	 * Displays the next or previous image information based on the provided
	 * ImageRecord
	 * 
	 * If the provided ImageRecord is not null, then show the image details.
	 * Otherwise, inform the user that there are no more images available
	 *
	 * @param alb The ImageAlbum containing a collection of images
	 * @param img The ImageRecord representing the next or previous image (can be
	 *            null)
	 */
	public static void displayNextOrPreviousImage(ImageAlbum alb, ImageRecord img) {
		if (img != null) {
			// Display information about the next/previous image
			printIntroMessage("Displaying image " + (alb.getCurrentImageValue() + 1));
			System.out.println(img.getDetails());
		} else {
			// Inform the user when there are no more images available
			System.err.println("No more images available.");
		}
	}

	/**
	 * Search for images within a specified date range and display the results
	 */
	public static void searchDates() {
		printIntroMessage("Search by image dates");

		// Flag to track whether the user has entered a valid date
		boolean validDate = false;

		// Variables to store the start and end dates
		LocalDate startDate = null, endDate = null;

		// Prompt the user to enter start and end dates until valid dates are provided
		do {
			try {
				System.out.print("Enter start date (YYYY-MM-DD): ");
				String start = input.nextLine().trim();
				startDate = formatDate(start); // Validate the start date

				System.out.print("Enter end date (YYYY-MM-DD): ");
				String end = input.nextLine().trim();
				endDate = formatDate(end); // Validate the end date

				validDate = true; // Set the flag to true if both dates are valid

			} catch (Exception ex) {
				// Handle the case where an invalid date format is provided
				System.err.println("Invalid date detected: " + ex.getMessage());
			}
		} while (!validDate);

		// Search for images within the specified date range
		ImageAlbum matchingImages = myImages.searchDates(startDate, endDate);

		if (matchingImages.getAlbum().size() > 0) {
			// Display a message and prepare navigation for images found within the date
			// range
			System.out.println("\n" + matchingImages.getAlbum().size() + " images between " + startDate + " and "
					+ endDate + " found!\n");
			navigateImages(matchingImages);
		} else {
			// Display an error message if no images are found within the date range
			System.out.println("\nERROR: No images found between " + startDate + " and " + endDate + ".\n");
		}

	}

	/**
	 * Parse and validate a date string, returning a LocalDate object
	 *
	 * @param date The date string to be parsed and validated
	 * @return A LocalDate object representing the parsed and validated date
	 * @throws Exception If the provided date is not in the expected format
	 */
	public static LocalDate formatDate(String date) throws Exception {
		// Check if the date string has the expected format (YYYY-MM-DD)
		if (date != null && date.length() == 10 && (date.charAt(4) == '-' && date.charAt(7) == '-')) {
			String[] splitDate = date.split("-");
			try {
				// Attempt to create a LocalDate object from splitDate
				LocalDate properDate = LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]),
						Integer.parseInt(splitDate[2]));
				return properDate;
			} catch (Exception ex) {
				// Throw an exception if properDate cannot be created
				throw new Exception(date);
			}

		}
		throw new Exception(date);
	}

	/**
	 * Display all images in the ImageManager, sorted from oldest to newest
	 */
	public static void displayAllImages() {
		printIntroMessage("Let's display all images!\n(Sorted from oldest to newest)");

		// Get all images from the ImageManager
		ImageAlbum allImages = myImages.getAllImages();

		// Display the details of all images
		displayImages(allImages);

		// Display a message once all images have been printed
		System.out.println("All images printed.\n");

	}

	/**
	 * Display the details of images in a given ImageAlbum
	 *
	 * @param collection The ImageAlbum containing the images to be displayed
	 */
	public static void displayImages(ImageAlbum collection) {
		for (int i = 0; i < collection.getAlbum().size(); i++) {
			// Print the details of each image
			System.out.println(collection.getAlbum().get(i).getDetails());
		}
	}

	/**
	 * Initialise an ImageManager with sample images
	 *
	 * @return The initialised ImageManager containing sample images
	 */
	public static ImageManager initialiseImageManager() {
		try {
			// Create a new ImageManager instance
			ImageManager someImages = new ImageManager();

			// Create and add sample images to the ImageManager
			ImageRecord image1 = new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy.", "Astronomy",
					"2023-01-01", "Andromeda.png");
			someImages.addImage(image1);

			ImageRecord image2 = new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.", "Architecture",
					"2023-01-02", "LanyonQUB.png");
			someImages.addImage(image2);

			ImageRecord image3 = new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf.",
					"Sport", "2023-01-03", "KermitGolf.png");
			someImages.addImage(image3);

			ImageRecord image4 = new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne Mountains.",
					"Landscape", "2023-01-04", "Mournes.png");
			someImages.addImage(image4);

			ImageRecord image5 = new ImageRecord("Homer Simpson", "Homer Simpson - A portrait of the man.", "Portrait",
					"2023-01-03", "Homer.png");
			someImages.addImage(image5);

			ImageRecord image6 = new ImageRecord("Red Kite", "A Red Kite bird of prey in flight.", "Nature",
					"2023-01-04", "RedKite.png");
			someImages.addImage(image6);

			ImageRecord image7 = new ImageRecord("Central Park", "An overhead view of Central Park New York USA.",
					"Aerial", "2023-01-05", "CentralPark.png");
			someImages.addImage(image7);

			ImageRecord image8 = new ImageRecord("Apples", "A bunch of apples.", "Food", "2023-01-06", "Apples.png");
			someImages.addImage(image8);

			ImageRecord image9 = new ImageRecord("Programming Meme", "A Chat GPT programming meme.", "Other",
					"2022-12-31", "ChatGPT.png");
			someImages.addImage(image9);

			return someImages;

		} catch (Exception e) {
			// Print stack trace if an exception occurs during image initialisation
			e.printStackTrace();
		}

		// Return null if initialisation fails
		return null;
	}
}