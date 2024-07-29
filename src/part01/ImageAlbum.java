package part01;

import java.util.ArrayList;

/*
 * The ImageAlbum class outlines an image album with functionality to navigate through images
 */
public class ImageAlbum {

	// Current index to keep track of the image being displayed
	private int currentImageValue = -1;

	// ArrayList to store ImageRecord objects
	private ArrayList<ImageRecord> album;

	/**
	 * Constructor for the ImageAlbum class
	 * 
	 * @param images The initial collection of images to populate the album with
	 */
	public ImageAlbum(ArrayList<ImageRecord> images) {
		this.album = sortAlbumImages(images);
	}

	/**
	 * Get the first image in the album
	 * 
	 * @return The first ImageRecord instance in the album (or null if the album is
	 *         empty)
	 */
	public ImageRecord getFirst() {
		if (this.album.size() > 0) {
			this.currentImageValue = 0;
			return this.album.get(0);
		}

		return null;
	}

	/**
	 * Get the next image in the album
	 * 
	 * @return The next ImageRecord object instance in the album (or null if the
	 *         album is empty or if at the end of the album)
	 */
	public ImageRecord getNext() {
		if (this.album.size() > 0 && this.currentImageValue < this.album.size() - 1) {
			// Move the index to the next image in the album
			this.currentImageValue++;
			return this.album.get(currentImageValue);
		}

		return null;
	}

	/**
	 * Get the previous image in the album
	 * 
	 * @return The previous ImageRecord object instance in the album (or null if the
	 *         album is empty, if at the start of the album, or if no images were
	 *         previously accessed)
	 */
	public ImageRecord getPrevious() {
		if (this.album.size() > 0 && this.currentImageValue > 0) {
			// Move the index to the previous image in the album
			this.currentImageValue--;
			return this.album.get(currentImageValue);
		}

		return null;
	}

	/**
	 * Get the entire album of images
	 * 
	 * @return The ArrayList containing all ImageRecord objects in the album
	 */
	public ArrayList<ImageRecord> getAlbum() {
		return this.album;
	}

	/**
	 * Get the index of the image being displayed
	 * 
	 * @return the index of the image being displayed
	 */
	public int getCurrentImageValue() {
		return this.currentImageValue;
	}

	/**
	 * Sort by insertion the given collection of images based on the date they were
	 * taken
	 * 
	 * @param images The unsorted ArrayList of images
	 * @return The sorted ArrayList of ImageRecord objects
	 */
	public ArrayList<ImageRecord> sortAlbumImages(ArrayList<ImageRecord> images) {
		// ArrayList to store sorted images
		ArrayList<ImageRecord> result = new ArrayList<ImageRecord>();

		// Iterate through each image in the unsorted collection
		for (ImageRecord img : images) {
			// Index to track position where the current image should be inserted
			int index;

			// Find the correct position for the current image
			for (index = 0; index < result.size(); index++) {
				if (img.compareTo(images.get(index)) < 0) {
					// Exit the loop if the current image should be inserted in this position
					break;
				}
			}

			// Insert the current image at the determined index to maintain sorted order
			result.add(index, img);
		}

		return result;
	}
}
