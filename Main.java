import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;
import java.time.LocalDate;

/**
 * Gets the user inputs and makes sure it is all valid.
 *
 * @author  Melody Berhane
 * @version 1.0
 *
 * @since 2023-06-14.
 */

public class Main {
    private static final String USERS_FILE = "users.txt";

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        // Intro to program
        System.out.println("Hello! Welcome to my library!!");
        System.out.println("Please input 1 if you are a"
            + "new member and 2 if you want to sign in.");
        final String choiceString = scanner.nextLine();
        try {
            // Converts choice from string to int
            int choice = Integer.parseInt(choiceString);
            // Gets their info and saves it in a file if they are a new member
            if (choice == 1) {
                System.out.println("Please enter your name:");
                final String userName = scanner.nextLine();

                // Generate a new user number
                final int userNumber = generateUserNumber();

                // Save user information to file
                saveUserToFile(userName, userNumber);

                System.out.println("Hello " + userName + ", welcome to our library. Your user "
                    + " number is "+ userNumber + ", please don't share "
                        + " this information with anyone.");
            // Gets them to use their number to log in
            } else if (choice == 2) {
                System.out.println("Please enter your user number:");
                final int userNumber = Integer.parseInt(scanner.nextLine());

                // Look for user in the file
                final String userName = findUserInFile(userNumber);
                // Checks if the userName exists
                if (userName != null) {
                    System.out.println("Hello " + userName
                        + ", glad to have you back.");
                } else {
                    System.out.println("Invalid user number. "
                        + " Please try again.");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }

            // loop that keeps on going until user enters n
            while (true) {
                System.out.println("Please enter 1 if you want to borrow a "
                    + " book, 2 to return a book, 3 to donate any new books, "
                        + " 4 to see what books we have in the library,"
                            + " and 5 to log out.");
                choice = Integer.parseInt(scanner.nextLine());

                // Allows them to borrow a book from the library
                if (choice == 1) {
                    System.out.println("What is the name of the book "
                        + "you would like to borrow?");
                    final String bookTitle = scanner.nextLine();

                    try (Scanner libraryScanner = new Scanner(
                            new File("library.txt"))) {
                        // Reads the names of the book in the library file.
                        while (libraryScanner.hasNextLine()) {
                            final String line = libraryScanner.nextLine();
                            final String[] bookDetails = line.split(",");
                            final String title = bookDetails[0].trim();
                            int copies = Integer.parseInt(
                                bookDetails[1].trim());

                            if (title.equalsIgnoreCase(bookTitle)
                                && copies > 0) {
                                copies--;
                                System.out.println("You can borrow the book '"
                                    + title + "'.");
                                // Sets borrowing period to 21 days
                                System.out.println("Due Date: "
                                    + calculateDueDate(21));
                                System.out.println("Please return it on time.");

                                // Update the library.txt file with the
                                // reduced number of copies
                                updateLibraryFile(bookTitle, copies);
                                break;
                            // Lets them know if the book is not
                            // there for them to borrow
                            } else if (title.equalsIgnoreCase(bookTitle)
                                && copies == 0) {
                                System.out.println("Sorry, the book '"
                                    + title + "' is currently not available "
                                        + " for borrowing.");
                                break;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("Library file not found. "
                            + "Please check if the file exists.");
                    }
                    // Asks if they need anything else
                    System.out.println("Is there anything I can "
                    + " do for you (y/n)?");
                    final String response = scanner.nextLine();
                    if ("n".equalsIgnoreCase(response)) {
                        System.out.println("Thank you so much for "
                            + "using our services.");
                        break;
                    }
                // Lets the user return the book
                } else if (choice == 2) {
                    System.out.println("What is the name of the book: ");
                    final String returnedBook = scanner.nextLine();
                    System.out.println("You have returned " + returnedBook
                        + " and paid your outstanding balance.");
                    System.out.println("Is there anything I can "
                        + "do for you (y/n)?");
                    final String response = scanner.nextLine();
                    if ("n".equalsIgnoreCase(response)) {
                        System.out.println("Thank you so much for "
                            + "using our services.");
                        break;
                    }
                // Lets the user donate a book
                } else if (choice == 3) {
                    System.out.println("Please enter the name of the book:");
                    final String newBookTitle = scanner.nextLine();
                    try {
                        // Read the current contents of the library file
                        final File libraryFile = new File("library.txt");
                        final BufferedReader reader = new BufferedReader(
                                new FileReader(libraryFile));
                        String line;
                        final StringBuilder libraryContent =
                            new StringBuilder();

                        // reads the existing contents of the library file
                        // and storing them in the libraryContent
                        while ((line = reader.readLine()) != null) {
                            libraryContent.append(line).append("\n");
                        }

                        reader.close();

                        // Append the new book to the library content
                        libraryContent.append(newBookTitle).append(",1\n");

                        // Write the updated library content back to the file
                        final FileWriter writer = new FileWriter(libraryFile);
                        writer.write(libraryContent.toString());
                        writer.close();

                    } catch (IOException e) {
                        System.out.println("An error occurred while "
                            + "updating the library.");
                    }
                    // Gets the name of the author, publisher,
                    // publish date and it stores it in inventory.
                    System.out.println("What is the author's name:");
                    final String author = scanner.nextLine();
                    System.out.println("Who is the publisher:");
                    final String publisher = scanner.nextLine();
                    System.out.println("What was the publishing date "
                        + " (mm/dd/yyyy):");
                    final String publishingDate = scanner.nextLine();
                    System.out.println("Thank you for adding to our library!");
                    System.out.println("Is there anything I "
                        + " can do for you (y/n)?");
                    final String response = scanner.nextLine();
                    if ("n".equalsIgnoreCase(response)) {
                        System.out.println("Thank you so much for "
                            + "using our services.");
                        break;
                    }
                // Lets them look up books in the library
                } else if (choice == 4) {
                    try {
                        final File libraryFile = new File("library.txt");
                        final Scanner fileScanner = new Scanner(libraryFile);

                        System.out.println("What book are you looking for:");
                        final String bookTitle = scanner.nextLine();

                        boolean bookFound = false;
                        while (fileScanner.hasNextLine()) {
                            final String line = fileScanner.nextLine();
                            final String[] bookInfo = line.split(", ");
                            if (bookInfo[0].equalsIgnoreCase(bookTitle)) {
                                System.out.println("We have " + bookInfo[1]
                                    + " copies of " + bookTitle);
                                bookFound = true;
                                break;
                            }
                        }

                        if (!bookFound) {
                            System.out.println("The book " + bookTitle
                                + " is not available in our library.");
                        }

                        System.out.println("Is there anything I can "
                            + "do for you (y/n)?");
                        final String response = scanner.nextLine();
                        if ("n".equalsIgnoreCase(response)) {
                            System.out.println("Thank you so much for "
                                + "using our services.");
                            break;
                        }

                        fileScanner.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Library file not found.");
                    }
                } else if (choice == 5) {
                    System.out.println("Thank you so much for "
                        + "using our services.");
                    break;
                }
            }
        } catch (NumberFormatException error) {
            System.out.println("Sorry but the input was not valid,"
                + " you have been logged out.");
        }

        scanner.close();
    }

    // Calculates the due date
    private static String calculateDueDate(int borrowingPeriod) {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate dueDate = currentDate.plusDays(borrowingPeriod);
        return dueDate.toString();
    }

    // Updates the library file
    private static void updateLibraryFile(String bookTitle, int copies) {
        try (Scanner scanner = new Scanner(new File("library.txt"))) {
            final StringBuilder updatedContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                final String[] bookDetails = line.split(",");
                final String title = bookDetails[0].trim();

                if (title.equalsIgnoreCase(bookTitle)) {
                    line = title + "," + copies;
                }
                updatedContent.append(line).append(System.lineSeparator());
            }

            // Write the updated content back to the library.txt file
            try (PrintWriter writer = new PrintWriter(
                new FileWriter("library.txt"))) {
                writer.print(updatedContent.toString());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while "
                + "updating the library file.");
        }
    }

        // Creates and assigns each user with a special number
        private static int generateUserNumber() {
        try {
            final File usersFile = new File(USERS_FILE);

            // Check if the users file exists
            if (!usersFile.exists()) {
                // Create a new users file
                usersFile.createNewFile();
                return 1;
            }

            // Read the existing user numbers from the file
            final String content = Files.readString(Path.of(USERS_FILE));
            final String[] lines = content.split("\n");

            // Calculate the next user number
            final int nextUserNumber = lines.length + 1;
            return nextUserNumber;
        } catch (IOException e) {
            System.out.println("Error: Failed to generate a user number.");
            return -1;
        }
    }

    // Saves each user to file
    private static void saveUserToFile(String userName, int userNumber) {
        try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(USERS_FILE, true))) {
            final String userLine = userNumber + ", " + userName;
            writer.write(userLine);
            writer.newLine();
            System.out.println("User information saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: Failed to save user"
                + "information to file.");
        }
    }

    // When they log in, finds them in the file
    private static String findUserInFile(int userNumber) {
        try {
            final File usersFile = new File(USERS_FILE);

            // Check if the users file exists
            if (!usersFile.exists()) {
                return null;
            }

            // Read the user information from the file
            final String content = Files.readString(Path.of(USERS_FILE));
            final String[] lines = content.split("\n");

            for (String line : lines) {
                final String[] userInfo = line.split(", ");
                final int storedUserNumber = Integer.parseInt(userInfo[0]);

                if (storedUserNumber == userNumber) {
                    return userInfo[1];
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to read user "
                + "information from file.");
        }

        return null;
    }
}
