package part02;

import java.awt.Color;
import java.awt.Font;

import console.Console;
import java.time.LocalDate;
import java.util.Scanner;

import javax.swing.ImageIcon;

import part01.ImageAlbum;
import part01.ImageManager;
import part01.ImageRecord;
import part01.ImageType;

/**
 * Utilising a console, the QUBMediaImages class serves as the main application
 * class for the ImageManager
 */
public class QUBMediaImages {

	// Static menu options and menu instance
	static String[] menuOptions = { "Add Image", "Search", "Display All", "Exit" };
	static Menu myImagesMenu = new Menu("QUB Images", menuOptions);

	// Static ImageManager instance to manage images
	static ImageManager myImages = initialiseImageManager();

	// Static scanner for user input
	static Scanner input = new Scanner(System.in);

	// Initialise Console object for photo media display
	static Console mediaCon = initialiseConsole(true, true, 1200, 500, Color.BLACK, Color.CYAN, "Segoe UI", Font.BOLD,
			20);

	/*
	 * Main method
	 */
	public static void main(String[] args) {
		// Initialise the standard error console
		Console errorCon = initialiseStandardErrorConsole();

		// Initialise the choice stored by the user
		int choice = 0;

		// Display menu and perform user operations until Exit is chosen by the user
		do {
			/*
			 * Get user choice from the main menu.
			 * 
			 * Please note that I had a small problem with formatting the text in errorCon.
			 * It would display correctly only once. To replicate this problem, enter a
			 * non-integer value (e.g. "No") twice when prompted
			 */
			choice = myImagesMenu.getUserChoice(mediaCon, errorCon);

			// Perform the corresponding operation based on user choice
			performUserOperation(choice);

		} while (choice != menuOptions.length);

		// Exit the application with the chosen menu option
		System.exit(choice);
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
				isInRange = true; // Exit loop since user chose to quit
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
		mediaCon.println("\n" + msg);
		for (int i = 0; i < msg.length(); i++) {
			mediaCon.print("+");
		}

		// Move to the next line after printing the line of '+'s
		mediaCon.println();
	}

	/**
	 * Initialises and configures a custom Console instance with the specified
	 * settings
	 *
	 * @param readKB     Determines whether the console reads keyboard input
	 * @param isVisible  Determines the initial visibility state of the console
	 * @param width      The width of the console
	 * @param height     The height of the console
	 * @param bgColour   The background colour of the console
	 * @param textColour The text colour of the console
	 * @param font       The font of the console
	 * @param fontStyle  The style of the font (e.g., Font.BOLD)
	 * @param fontSize   The size of the font
	 * @return A configured Console instance based on the provided settings
	 */
	public static Console initialiseConsole(boolean readKB, boolean isVisible, int width, int height, Color bgColour,
			Color textColour, String font, int fontStyle, int fontSize) {

		Console con = new Console(readKB); // Create a new Console instance
		con.setVisible(isVisible); // Set console visibility
		con.setSize(width, height); // Set console size
		con.setBgColour(bgColour); // Set background colour
		con.setColour(textColour); // Set text colour
		con.setFont(new Font(font, fontStyle, fontSize)); // Set font

		// Return the configured Console instance
		return con;
	}

	/**
	 * Initialises a standard error Console with predefined settings for error
	 * messages
	 *
	 * @return A configured Console instance for displaying error messages
	 */
	public static Console initialiseStandardErrorConsole() {
		return initialiseConsole(true, false, 500, 150, Color.BLACK, Color.RED, "OCR A Extended", Font.PLAIN, 20);
	}

	/**
	 * Display an error message indicating that the user's input is not within the
	 * valid range, then prompt the user to press RETURN to continue.
	 *
	 * @param men The Menu instance for which an invalid range message is displayed
	 * @return The user's choice after the error message is displayed
	 */
	public static int printInvalidRangeMessage(Menu men) {
		Console errorCon = initialiseStandardErrorConsole(); // Initialise error console
		errorCon.println("Not within the range.\n"); // Error message

		// Prompt the user to press RETURN to continue
		myImagesMenu.pressReturnToContinue(errorCon, mediaCon);

		// Return the user's choice after displaying the error message
		return men.getUserChoice(mediaCon, errorCon); // Prompt user again for valid choice
	}

	/**
	 * Prompt the user to enter a genre and return the entered value
	 *
	 * @return The entered genre
	 */
	public static String selectGenre() {
		mediaCon.print(
				"Enter a genre (Astronomy, architecture, sport, landscape, portrait, nature, aerial, food, or other): ");
		return mediaCon.readLn().trim();
	}

	/**
	 * Begin the process of searching for images based on user input. Display a
	 * search menu with various options, allowing the user to choose the search
	 * criteria (ID, Title, Description, Genre, Dates, or Exit).
	 */
	public static void searchImage() {
		mediaCon.println();

		// Array of search menu options
		String[] searchMenuOptions = { "ID", "Title", "Description", "Genre", "Dates", "Exit" };

		// Create a Menu instance with specified options
		Menu searchMenu = new Menu("Let's search for an image!", searchMenuOptions);

		// Initialise a standard error console for error messages
		Console errorCon = initialiseStandardErrorConsole();

		int choice = 0;
		do {
			/*
			 * Get the user's choice from the search menu
			 * 
			 * Please note that I had a small problem with formatting the text in errorCon.
			 * It would display correctly only once. To replicate this problem, enter a
			 * non-integer value (e.g. "No") twice when prompted
			 */
			choice = searchMenu.getUserChoice(mediaCon, errorCon);

			// Perform the search based on the user's choice
			performSearch(choice, searchMenu);

		} while (choice != searchMenuOptions.length); // Loop until the user chooses to quit
	}

	/**
	 * Add a new image to the ImageManager. Guide the user through entering metadata
	 * for the new image, including title, description, genre, thumbnail, and date
	 * taken
	 */
	public static void addImage() {
		// Display an introductory message for adding a new image
		printIntroMessage("Let's add a new image!");

		// Prompt the user to enter metadata for the new image
		mediaCon.print("Enter the title: ");
		String title = mediaCon.readLn().trim();

		mediaCon.print("Enter the description: ");
		String desc = mediaCon.readLn().trim();

		String genre = selectGenre();

		mediaCon.print("Enter the thumbnail: ");
		String thumbnail = mediaCon.readLn().trim();

		try {
			// Check if the specified thumbnail file exists
			isThumbnailFileExists(thumbnail);

			// Prompt the user to enter the date the image was taken
			mediaCon.print("Enter the date it was taken (YYYY-MM-DD): ");
			String date = mediaCon.readLn().trim();

			// Create a new ImageRecord instance with the entered metadata
			ImageRecord newImage = new ImageRecord(title, desc, genre, date, thumbnail);

			// Add the new image to the ImageManager
			myImages.addImage(newImage);

			// Display a success message after adding the new image
			mediaCon.println("New image added!\n");
		} catch (Exception e) {
			// Handle exceptions (when validation has failed, for instance) by displaying an
			// error message and prompting the user to continue
			Console specialErrorCon = initialiseConsole(true, false, 530, 500, Color.BLACK, Color.RED, "OCR A Extended",
					Font.PLAIN, 15);
			ImageIcon img = new ImageIcon(initialiseImagesDirectory() + "magicword.gif");
			specialErrorCon.println(img);
			specialErrorCon.println("\nERROR: " + e.getMessage());
			myImagesMenu.pressReturnToContinue(specialErrorCon, mediaCon);
		}
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
				mediaCon.println("Returning to main menu...\n");
				mediaCon.clear();
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
			mediaCon.print("Enter " + optionMsg + ": ");
			target = mediaCon.readLn(); // Prompt user to enter the target (title or description)
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
			mediaCon.print("Enter an ID: ");
			try {
				String idString = mediaCon.readLn();
				id = Integer.parseInt(idString);
				validInt = true; // Set the flag to true if an integer is successfully read
			} catch (NumberFormatException ex) {
				// Handle any non-integer exceptions
				Console errorCon = initialiseStandardErrorConsole();

				// Display an error message, then prompt the user to press RETURN to continue
				errorCon.println("Invalid input detected!");
				myImagesMenu.pressReturnToContinue(errorCon, mediaCon);
			}
		} while (!validInt);

		// Search for the image with the specified ID in the ImageManager
		ImageRecord targetImage = myImages.searchId(id);

		if (targetImage != null) {
			// Display information about the found image, including its photo
			String currentImageThumbnail = targetImage.getThumbnail();
			ImageIcon img = new ImageIcon(initialiseImagesDirectory() + currentImageThumbnail);

			mediaCon.println("\nImage with ID " + id + " was found!");
			mediaCon.println(img);
			mediaCon.println("\n" + targetImage.getDetails());
		} else {
			// Display an error message if no image is found with the specified ID
			Console errorCon = initialiseStandardErrorConsole();
			errorCon.println("ERROR: No image found with ID " + id + ".\n");
			myImagesMenu.pressReturnToContinue(errorCon, mediaCon);
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
			mediaCon.println("\n1 image with " + optionMsg + " '" + target + "' found!\n");
			// Display the details of the matching image
			displayImages(matchingImages);
		} else if (matchingImages.getAlbum().size() > 0) {
			// Display a message for multiple matching images
			mediaCon.println(
					"\n" + matchingImages.getAlbum().size() + " images with " + optionMsg + " '" + target + "' found!");
			// Call method to navigate through the matching images
			navigateImages(matchingImages);
		} else {
			Console errorCon = initialiseStandardErrorConsole();

			// Display an error message if no matching images are found
			errorCon.println("ERROR: No image found with " + optionMsg + " '" + target + "'.\n");

			// Prompt the user to press RETURN to continue
			myImagesMenu.pressReturnToContinue(errorCon, mediaCon);
		}
	}

	/**
	 * Displays and navigates through images in the given ImageAlbum
	 *
	 * @param alb The ImageAlbum containing a collection of images
	 */
	public static void navigateImages(ImageAlbum alb) {

		for (int i = 0; i < alb.getAlbum().size(); i++) {

			// Get the thumbnail path for the current image
			String currentImageThumbnail = alb.getAlbum().get(i).getThumbnail();

			// Create an ImageIcon using the thumbnail path
			ImageIcon img = new ImageIcon(initialiseImagesDirectory() + currentImageThumbnail);

			// Display the image and its details using the mediaCon console
			mediaCon.println(img);

			// Print brief details of each image
			mediaCon.println(alb.getAlbum().get(i) + "\n");
		}

		// Display introductory message with the current image number
		printIntroMessage("Displaying image " + (alb.getCurrentImageValue() + 2));

		// Display information about the first image
		String currentImageThumbnail = alb.getFirst().getThumbnail();
		ImageIcon photo = new ImageIcon(initialiseImagesDirectory() + currentImageThumbnail);
		mediaCon.println(photo);
		mediaCon.println(alb.getFirst().getDetails());

		int choice = 0;

		do {
			// Prompt the user to select an action
			mediaCon.print("Select 1 for next image, 2 for previous image, or 3 to exit: ");
			try {
				// Read the user's choice
				String choiceString = mediaCon.readLn();

				// Convert to integer
				choice = Integer.parseInt(choiceString);

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
					mediaCon.println("Exiting...\n");
					mediaCon.clear();
					break;
				default:
					// If the user enters an integer that is out of bounds, prompt again
					Console errorCon = initialiseStandardErrorConsole();
					errorCon.println("Not within the range. Try again.\n");
					myImagesMenu.pressReturnToContinue(errorCon, mediaCon);
				}
			} catch (NumberFormatException ex) {
				// Handle non-integer values by informing the user about invalid input
				Console errorCon = initialiseStandardErrorConsole();
				errorCon.println("Invalid input detected!\n");

				// Then prompt the user to continue
				myImagesMenu.pressReturnToContinue(errorCon, mediaCon);
			} catch (IndexOutOfBoundsException oob) {
				// Inform the user about an error while navigating the images and print the
				// stack trace for debugging
				Console errorCon = initialiseStandardErrorConsole();
				errorCon.println("ERROR: Problem with ImageAlbum, see internal console for more info.\n");
				oob.printStackTrace();

				// Then prompt the user to continue
				myImagesMenu.pressReturnToContinue(errorCon, mediaCon);
			}
		} while (choice != 3); // Continue looping until the user chooses to exit
	}

	/**
	 * Display information about the next or previous image in the specified
	 * ImageAlbum. If the provided ImageRecord is not null, then show the image
	 * details along with its photo. Otherwise, inform the user that there are no
	 * more images available
	 *
	 * @param alb The ImageAlbum containing a collection of images
	 * @param img The ImageRecord representing the next or previous image (can be
	 *            null)
	 */
	public static void displayNextOrPreviousImage(ImageAlbum alb, ImageRecord img) {
		if (img != null) {
			// Display information about the next image
			printIntroMessage("Displaying image " + (alb.getCurrentImageValue() + 1));

			// Retrieve the thumbnail and create an ImageIcon for the image
			String currentImageThumbnail = img.getThumbnail();
			ImageIcon photo = new ImageIcon(initialiseImagesDirectory() + currentImageThumbnail);

			// Display the photo and details of the current image
			mediaCon.println(photo);
			mediaCon.println(img.getDetails());
		} else {
			// Inform the user when there are no more images available
			Console errorCon = initialiseStandardErrorConsole();
			errorCon.println("No more images available.\n");
			myImagesMenu.pressReturnToContinue(errorCon, mediaCon);
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
				mediaCon.print("Enter start date (YYYY-MM-DD): ");
				String start = mediaCon.readLn().trim();
				startDate = formatDate(start); // Validate the start date

				mediaCon.print("Enter end date (YYYY-MM-DD): ");
				String end = mediaCon.readLn().trim();
				endDate = formatDate(end); // Validate the end date

				validDate = true; // Set the flag to true if both dates are valid

			} catch (Exception ex) {
				// Handle the case where an invalid date format is provided
				Console errorCon = initialiseStandardErrorConsole();
				errorCon.println("Invalid date detected: " + ex.getMessage());
				myImagesMenu.pressReturnToContinue(errorCon, mediaCon);
			}
		} while (!validDate);

		// Search for images within the specified date range
		ImageAlbum matchingImages = myImages.searchDates(startDate, endDate);

		if (matchingImages.getAlbum().size() > 0) {
			// Display a message and prepare navigation for images found within the date
			// range
			mediaCon.println("\n" + matchingImages.getAlbum().size() + " images between " + startDate + " and "
					+ endDate + " found!");
			navigateImages(matchingImages);
		} else {
			// Display an error message if no images were found
			Console errorCon = initialiseStandardErrorConsole();
			errorCon.println("ERROR: No images found between " + startDate + " and " + endDate + ".\n");
			myImagesMenu.pressReturnToContinue(errorCon, mediaCon);
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

		// Display the details and photos of all images
		displayImages(allImages);

		// Display a message once all images have been printed
		mediaCon.println("All images printed.\n");

	}

	/**
	 * Return the directory path for storing images. It utilises the current working
	 * directory and appends the "Images/" directory to it
	 *
	 * @return The directory path for storing images
	 */
	public static String initialiseImagesDirectory() {
		return System.getProperty("user.dir") + "/Images/";
	}

	/**
	 * Display images and their details from the provided ImageAlbum collection
	 *
	 * @param collection The ImageAlbum collection containing the images to be
	 *                   displayed
	 */
	public static void displayImages(ImageAlbum collection) {
		for (int i = 0; i < collection.getAlbum().size(); i++) {
			// Get the thumbnail path for the current image
			String currentImageThumbnail = collection.getAlbum().get(i).getThumbnail();

			// Create an ImageIcon using the thumbnail path
			ImageIcon img = new ImageIcon(initialiseImagesDirectory() + currentImageThumbnail);

			// Display the image and its details using the mediaCon console
			mediaCon.println(img);
			mediaCon.println(collection.getAlbum().get(i).getDetails());
		}
	}

	/**
	 * Check if the thumbnail file exists in the specified directory
	 *
	 * @param thumbnailTitle The title of the thumbnail file
	 * @return true if the thumbnail file exists, false otherwise
	 * @throws Exception if the thumbnail file is not found
	 */
	public static boolean isThumbnailFileExists(String thumbnailTitle) throws Exception {
		// Get the thumbnail path for the current image
		String thumbnailPath = initialiseImagesDirectory() + thumbnailTitle;

		// Attempt to create an ImageIcon using the thumbnail path
		ImageIcon img = new ImageIcon(thumbnailPath);

		// Check if the icon height is valid
		if (img.getIconHeight() == -1) {
			// Throw an exception if the thumbnail is not found
			throw new Exception("Thumbnail '" + thumbnailTitle + "' not found.");
		}

		// Return true if the thumbnail file exists
		return true;
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
			// Handle any exceptions that occur during initialisation
			Console errorCon = initialiseStandardErrorConsole();
			errorCon.print(e.getMessage());
			myImagesMenu.pressReturnToContinue(errorCon, mediaCon);

		}

		// Return null if initialisation fails
		return null;
	}
}