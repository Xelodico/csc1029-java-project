package part01;

/**
 * The ImageType enum represents the different types/genres of images
 */
public enum ImageType {
	// Enum constants with associated descriptions
	ASTRONOMY(
			"Astronomy - Photography or imaging of astronomical objects, celestial events, or areas of the night sky."),
	ARCHITECTURE(
			"Architecture - Focuses on the capture of images that accurately represent the design and feel of buildings."),
	SPORT("Sport - Covers all types of sports and can be considered a branch of photojournalism"),
	LANDSCAPE("Landscape - The study of the textured surface of the Earth and features images of natural scenes."),
	PORTRAIT("Portrait - mages of a person or a group of people where the face and facial features are predominant."),
	NATURE("Nature - Focused on elements of the outdoors including sky, water, and land, or the flora and fauna."),
	AERIAL("Aerial - Images taken from an aircraft or other airborne platforms."),
	FOOD("Food - Captures everything related to food, from fresh ingredients and plated dishes to the cooking process"),
	OTHER("Other - Covers just about any other type of image and photography genre.");

	// Description associated with each enum constant
	public String desc;

	// Constructor for each enum constant
	private ImageType(String desc) {
		this.desc = desc;
	}

	/**
	 * Get the description associated with the image type
	 *
	 * @return The description of the image type
	 */
	public String toString() {
		return this.desc;
	}
}
