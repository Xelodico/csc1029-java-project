package part01;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The ImageManager class manages a collection of ImageRecord objects
 */
public class ImageManager {

	// ArrayList to store ImageRecord objects
	private ArrayList<ImageRecord> images;

	/**
	 * Constructor for the ImageManager class
	 */
	public ImageManager() {
		// Initialise the ArrayList to store ImageRecord objects
		this.images = new ArrayList<ImageRecord>();
	}

	/**
	 * Add an ImageRecord to the collection
	 *
	 * @param image The ImageRecord to be added. It must not be null
	 */
	public void addImage(ImageRecord image) {
		// Check if the provided image is not null before adding to the ArrayList
		if (image != null) {
			images.add(image);
		}
	}

	/**
	 * Search for an ImageRecord by its ID
	 *
	 * @param id The ID of the ImageRecord to search for
	 * @return The ImageRecord with the specified ID, or null if not found
	 */
	public ImageRecord searchId(int id) {
		for (int i = 0; i < images.size(); i++) {
			ImageRecord target = images.get(i);
			if (target.getId() == id) {
				return target;
			}
		}

		return null;
	}

	/**
	 * Search for ImageRecords by title and create an album from the matching
	 * results
	 *
	 * @param str The string to search for in image titles
	 * @return An ImageAlbum containing ImageRecords with titles matching the
	 *         specified string
	 */
	public ImageAlbum searchTitle(String str) {
		// ArrayList to store ImageRecord objects matching the search criteria
		ArrayList<ImageRecord> result = new ArrayList<ImageRecord>();

		// Iterate through each ImageRecord in the images ArrayList
		for (ImageRecord matchingImage : images) {

			// Check if the title of the current ImageRecord contains the specified string
			if (matchingImage.getTitle().contains(str)) {
				// If there is a match, add the ImageRecord to the result ArrayList
				result.add(matchingImage);
			}
		}

		return new ImageAlbum(result);
	}

	/**
	 * Search for ImageRecords by description and create an album from the matching
	 * results
	 *
	 * @param str The string to search for in image descriptions
	 * @return An ImageAlbum containing ImageRecords with descriptions matching the
	 *         specified string
	 */
	public ImageAlbum searchDescription(String str) {
		// ArrayList to store ImageRecord objects matching the search criteria
		ArrayList<ImageRecord> result = new ArrayList<ImageRecord>();

		// Iterate through each ImageRecord in the images ArrayList
		for (ImageRecord matchingImage : images) {

			// Check if the description of the current ImageRecord contains the specified
			// string
			if (matchingImage.getDescription().contains(str)) {
				// If there is a match, add the ImageRecord to the result ArrayList
				result.add(matchingImage);
			}
		}

		return new ImageAlbum(result);
	}

	/**
	 * Search for ImageRecords by genre and create an album from the matching
	 * results
	 *
	 * @param type The ImageType (genre) to search for
	 * @return An ImageAlbum containing ImageRecords with the specified genre
	 */
	public ImageAlbum searchGenre(ImageType type) {
		// ArrayList to store ImageRecord objects matching the search criteria
		ArrayList<ImageRecord> result = new ArrayList<ImageRecord>();

		// Iterate through each ImageRecord in the images ArrayList
		for (ImageRecord matchingImage : images) {
			// Check if the genre of the current ImageRecord contains the specified
			// ImageType
			if (matchingImage.getGenre().equals(type)) {
				// If there is a match, add the ImageRecord to the result ArrayList
				result.add(matchingImage);
			}
		}

		return new ImageAlbum(result);
	}

	/**
	 * Search for ImageRecords by date range and create an album from the matching
	 * results
	 *
	 * @param start The start date of the date range
	 * @param end   The end date of the date range
	 * @return An ImageAlbum containing ImageRecords taken within the specified date
	 *         range
	 */
	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		// ArrayList to store ImageRecord objects matching the search criteria
		ArrayList<ImageRecord> result = new ArrayList<ImageRecord>();

		// Iterate through each ImageRecord in the images ArrayList
		for (ImageRecord matchingImage : images) {

			// Check if the date taken of the current ImageRecord is after the start date
			// and before the end date, indicating it falls within the specified date range
			if (matchingImage.getDateTaken().isAfter(start) && matchingImage.getDateTaken().isBefore(end)) {
				// If there is a match, add the ImageRecord to the result ArrayList
				result.add(matchingImage);
			}
		}

		return new ImageAlbum(result);
	}

	/**
	 * Get an ImageAlbum containing all images in the collection
	 *
	 * @return An ImageAlbum containing all ImageRecords in the collection
	 */
	public ImageAlbum getAllImages() {
		return new ImageAlbum(this.images);
	}
}