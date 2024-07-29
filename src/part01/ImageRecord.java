package part01;

import java.time.LocalDate;

/**
 * The ImageRecord class represents an image with various metadata
 */
public class ImageRecord implements Comparable<ImageRecord> {
	// Instance data
	private int id; // Store the image ID
	private String title; // Store the title of the image
	private String description; // Store the description of the image
	private ImageType Genre; // Store the genre of the image
	private String thumbnail;// Store the thumbnail associated with the image
	private LocalDate dateTaken; // Store the date the image was taken

	// Class data
	private static int nextId = 1; // Index storing the unique image ID

	/**
	 * Constructor for creating an ImageRecord with specified details
	 *
	 * @param title The title of the image
	 * @param desc  The description of the image
	 * @param Genre The genre of the image
	 * @param date  The date the image was taken
	 * @param thumb The thumbnail associated with the image
	 * @throws Exception If there are errors during validation of the setters
	 */
	public ImageRecord(String title, String desc, String Genre, String date, String thumb) throws Exception {
		String errors = "";

		// Validate and set the title
		if (setTitle(title) != true) {
			errors += "Bad title: " + title + "\n";
		}

		// Validate and set the image description
		if (setDescription(desc) != true) {
			errors += "Bad image description: " + desc + "\n";
		}

		// Set the genre based on the provided String parameter
		setGenre(Genre);

		// Validate and set the thumbnail title
		if (setThumbnail(thumb) != true) {
			errors += "Bad thumbnail title: " + thumb + "\n";
		}

		// Validate and set the date taken
		if (setDateTaken(date) != true) {
			errors += "Invalid date: " + date + "\n";
		}

		// If there are errors, throw an exception; otherwise, assign the unique ID
		if (errors.length() > 0) {
			throw new Exception(errors);
		} else {
			this.id = nextId;
			nextId++;
		}
	}

	/**
	 * Default constructor for ImageRecord
	 */
	public ImageRecord() {

	}

	/**
	 * Compare ImageRecords based on their date taken
	 *
	 * @param img The ImageRecord to compare to
	 * @return An integer representing the comparison result
	 */
	public int compareTo(ImageRecord img) {
		return this.dateTaken.compareTo(img.getDateTaken());
	}

	/**
	 * Get the unique ID of the image
	 *
	 * @return The ID of the image
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Get the title of the image
	 *
	 * @return The title of the image
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Get the description of the image
	 *
	 * @return The description of the image
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get the thumbnail associated with the image
	 *
	 * @return The thumbnail of the image
	 */
	public String getThumbnail() {
		return this.thumbnail;
	}

	/**
	 * Get the date the image was taken
	 *
	 * @return The date the image was taken
	 */
	public LocalDate getDateTaken() {
		return this.dateTaken;
	}

	/**
	 * Get the genre of the image
	 *
	 * @return The genre of the image
	 */
	public ImageType getGenre() {
		return this.Genre;
	}

	/**
	 * Set the title of the image
	 *
	 * @param title The title to set
	 * @return True if the title is valid and set successfully, false otherwise
	 */
	public boolean setTitle(String title) {
		if (title != null && title.length() > 0) {
			this.title = title;
			return true;
		}

		return false;
	}

	/**
	 * Set the description of the image
	 *
	 * @param desc The description to set
	 * @return True if the description is valid and set successfully, false
	 *         otherwise
	 */
	public boolean setDescription(String desc) {
		if (desc != null && desc.length() > 0) {
			this.description = desc;
			return true;
		}

		return false;
	}

	/**
	 * Set the genre of the image based on a provided string
	 *
	 * @param genre The string representing the genre
	 * @return The ImageType enum corresponding to the provided string
	 */
	public ImageType setGenre(String genre) {
		switch (genre.toUpperCase()) {
		case "ASTRONOMY":
			return this.Genre = ImageType.ASTRONOMY;
		case "ARCHITECTURE":
			return this.Genre = ImageType.ARCHITECTURE;
		case "SPORT":
			return this.Genre = ImageType.SPORT;
		case "LANDSCAPE":
			return this.Genre = ImageType.LANDSCAPE;
		case "PORTRAIT":
			return this.Genre = ImageType.PORTRAIT;
		case "NATURE":
			return this.Genre = ImageType.NATURE;
		case "AERIAL":
			return this.Genre = ImageType.AERIAL;
		case "FOOD":
			return this.Genre = ImageType.FOOD;
		default:
			return this.Genre = ImageType.OTHER;
		}
	}

	/**
	 * Set the thumbnail associated with the image
	 *
	 * @param thumb The thumbnail to set
	 * @return True if the thumbnail is valid and set successfully, false otherwise
	 */
	public boolean setThumbnail(String thumb) {
		if (thumb != null && thumb.length() > 0) {
			this.thumbnail = thumb;
			return true;
		}

		return false;
	}

	/**
	 * Set the date the image was taken
	 *
	 * @param date The date to set
	 * @return True if the date is valid and set successfully, false otherwise
	 */
	public boolean setDateTaken(String date) {
		if (date != null && date.length() == 10 && (date.charAt(4) == '-' && date.charAt(7) == '-')) {
			// Split the date string into year, month, and day respectively
			String[] splitDate = date.split("-");
			try {
				// Create a LocalDare object from the elements of splitDate
				LocalDate properDate = LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]),
						Integer.parseInt(splitDate[2]));

				// If successful, set the dateTaken field
				this.dateTaken = properDate;
			} catch (Exception ex) {
				// Otherwise return false
				return false;
			}
			// Return true after setting the dateTaken field
			return true;
		}
		// Return false if the date format is invalid
		return false;
	}

	/**
	 * Return a string containing detailed information about the image
	 *
	 * @return A string with image details
	 */
	public String getDetails() {
		return "Image ID: " + this.id + "\nTitle: " + getTitle() + "\nDescription: " + getDescription() + "\nGenre: "
				+ getGenre() + "\nDate Taken: " + getDateTaken() + "\nThumbnail: " + getThumbnail() + "\n";
	}

	/**
	 * Return a string representation of the ImageRecord
	 *
	 * @return A string representation of the image
	 */
	public String toString() {
		return "Image ID " + this.id + " called '" + getTitle() + "' with description '" + getDescription()
				+ "' and genre '" + getGenre() + "' - It was taken on " + getDateTaken()
				+ " and contains the thumbnail " + getThumbnail() + ".";
	}

}