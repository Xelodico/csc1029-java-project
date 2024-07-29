# 🍵 Introduction

For my first-year Computer Science module on Object-Oriented Programming, a Java program was commissioned for QUB's fictitious photography department. The program, developed using Eclipse, manages a collection of 10+ images across various genres to create albums for display purposes.

---

## 💡 Features

- **Utility Classes**: Five utility classes for image metadata, image album, image manager, genre enumeration, and a console-based menu.
- **Application Class**: Integrates menu and image manager instances.
- **Image Management**: Capable of interacting with and managing images from specific albums (in this case, the Images folder).

---

## 🖼️ Screenshots

**Outline UML Diagram**  
![UML](https://github.com/Xelodico/random-resources/blob/main/images/csc1029%20java%20project/UML.png?raw=true)

**Running the Program (Utilising the Console)**: Executed by running `QUBMediaImages.java`. Here, I'm demonstrating searching for an image by its ID.  
![Running the program](https://github.com/Xelodico/random-resources/blob/main/images/csc1029%20java%20project/Search.png?raw=true)

**`QUBMediaImages.java` (Application Class)**  
![QUBMediaImages](https://github.com/Xelodico/random-resources/blob/main/images/csc1029%20java%20project/QUBMediaImages.png?raw=true)

**`Menu.java`**  
![Menu](https://github.com/Xelodico/random-resources/blob/main/images/csc1029%20java%20project/Menu.png?raw=true)

**`ImageType.java` (Enum)**  
![ImageType](https://github.com/Xelodico/random-resources/blob/main/images/csc1029%20java%20project/ImageType.png?raw=true)

**`ImageRecord.java`**  
![ImageRecord](https://github.com/Xelodico/random-resources/blob/main/images/csc1029%20java%20project/ImageRecord.png?raw=true)

**`ImageManager.java`**  
![ImageManager](https://github.com/Xelodico/random-resources/blob/main/images/csc1029%20java%20project/ImageManager.png?raw=true)

**`ImageAlbum.java`**  
![ImageAlbum](https://github.com/Xelodico/random-resources/blob/main/images/csc1029%20java%20project/ImageAlbum.png?raw=true)

---

## 💻 Run Locally

1. I recommend downloading the [Eclipse IDE](https://eclipseide.org/), as this project was built using it.
    - **Note:** The code will work with other IDEs and source-code editors such as Visual Studio Code and IntelliJ IDEA. However, **the images will not be displayed**. I am unsure of the cause, as it works as intended in Eclipse.
2. Download the latest source code.
3. Create a new folder.
4. Extract the zipped file's contents into the folder.
5. Import the Java project:
    - [Eclipse tutorial](https://www.instructables.com/How-to-Import-Java-Projects-Into-Eclipse-for-Begin/)
    - [VSCode tutorial](https://code.visualstudio.com/docs/java/java-project#_import-java-projects)
    - [IntelliJ IDEA tutorial](https://www.jetbrains.com/guide/java/tutorials/import-project/project-from-existing-model/)

6. There should be two folders inside the `src` folder, `part01` and `part02`.
    - `part01` utilises the integrated console/terminal of the IDE, executed by running `QUBImages.java`.
        - **Note:** The images will not be displayed. This is intentional.
    - `part02` utilises the external console (`lib\console.jar`), executed by running `QUBMediaImages.java`.
        - **Note:** You may need to reconfigure `console.jar` for your build path.
            - [Eclipse tutorial](https://stackoverflow.com/a/3280384)
            - [VSCode tutorial](https://stackoverflow.com/a/59645355)
            - [IntelliJ IDEA tutorial](https://stackoverflow.com/a/1051705)
