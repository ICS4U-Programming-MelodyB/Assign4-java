import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This is the main file that will
 * read the input file and write the
 * right information to the output file.
 *
 * @author  Melody Berhane
 * @version 1.0
 *
 * @since 2023-06-16.
 */

public class LibraryManagementSystem {
    // The library's inventory
    private Inventory inventory;
    // Currently signed-in member
    private Member currentMember;

    public LibraryManagementSystem() {
        // Initialize the inventory
        this.inventory = new Inventory();
        // No member signed in initially
        this.currentMember = null;
    }

    public void run(String inputFile, String outputFile) {
        // Get the list of books from the inventory
        final List<Book> bookList = inventory.getBooks();
        // Open the input file for reading
        try (BufferedReader reader = new BufferedReader(
            new FileReader(inputFile));
                FileWriter writer = new FileWriter(outputFile)) {

            String line;
            // Read each line from the input file
            while ((line = reader.readLine()) != null) {
                // Split the line into tokens using comma as the delimiter
                final String[] tokens = line.split(",");
                // Extract the command from the first token
                final String command = tokens[0].trim();

                switch (command) {
                    // Add books to the library
                    case "AddBook":
                        final String name = tokens[1];
                        final String author = tokens[2];
                        final String publisher = tokens[3];
                        final String publicationDate = tokens[4];
                        final Book book = new Book(
                            name, author, publisher, publicationDate);
                        // Add the book to the inventory
                        inventory.addBook(book);
                        writer.write("Book added: " + name + "\n");
                        break;

                    // Look for books in the library
                    case "SearchBook":
                        final String bookName = tokens[1];
                        // Search for the book in the inventory
                        final Book searchedBook =
                            inventory.searchBook(bookName);
                        if (searchedBook != null) {
                            writer.write("Book found: "
                                + searchedBook.getName() + "\n");
                            writer.write("Author: "
                                + searchedBook.getAuthor() + "\n");
                            writer.write("Publisher: "
                                + searchedBook.getPublisher() + "\n");
                            writer.write("Publication Date: "
                                + searchedBook.getPublicationDate() + "\n");
                        } else {
                            writer.write("Book not found: " + bookName + "\n");
                        }
                        break;

                    // Allows users to sign in
                    case "SignIn":
                        final String signInMemberName = tokens[1].trim();
                        // Find the member in the inventory
                        currentMember = inventory.findMember(signInMemberName);
                        if (currentMember != null) {
                            writer.write("Member signed in: "
                                + signInMemberName + "\n");
                        } else {
                            writer.write("Member not found: "
                                + signInMemberName + "\n");
                        }
                        break;

                    // Allows users to borrow books
                    case "BorrowBook":
                        final String bookToBorrow = tokens[1];
                        // Search for the book in the inventory
                        final Book borrowedBook = inventory.searchBook(
                            bookToBorrow);
                        if (borrowedBook != null) {
                            writer.write("Book borrowed: "
                                + borrowedBook.getName() + "\n");
                        } else {
                            writer.write("Book not found: "
                                + bookToBorrow + "\n");
                        }
                        break;

                    // Allows new members to sign up.
                    case "SignUp":
                        final String newMemberName = tokens[1].trim();
                        final Member newMember = new Member(newMemberName);
                        // Add the member to the inventory
                        inventory.addMember(newMember);
                        writer.write("Member signed up: "
                            + newMemberName + "\n");
                        break;

                    // Allows user to donate book
                    case "DonateBook":
                        final String donatedBookName = tokens[1];
                        // Checks if the book is already in the library.
                        final Book donatedBook = inventory.searchBook(
                            donatedBookName);
                        if (donatedBook != null) {
                            writer.write("Book already "
                                + "exists in the inventory: "
                                    + donatedBook.getName() + "\n");
                        } else {
                            // Create a new book using the donated book details
                            final String donatedAuthor = tokens[2];
                            final String donatedPublisher = tokens[3];
                            final String donatedPublicationDate = tokens[4];
                            final Book newDonatedBook = new Book(
                                donatedBookName, donatedAuthor,
                                    donatedPublisher, donatedPublicationDate);
                            // Add the donated book to the inventory
                            inventory.addBook(newDonatedBook);
                            writer.write("Book donated: "
                                + newDonatedBook.getName()
                                    + " by " + currentMember.getName() + "\n");
                        }
                        break;

                    // Returns book to the library
                    case "ReturnBook":
                        final String bookToReturn = tokens[1];
                        // Search for the book in the inventory
                        final Book returnedBook =
                            inventory.searchBook(bookToReturn);
                        if (returnedBook != null) {
                            writer.write("Book returned: "
                                + returnedBook.getName()
                                    + " by " + currentMember.getName() + "\n");
                            returnedBook.setBorrowedBy(null);
                        } else {
                            writer.write("Book not found: "
                                + bookToReturn + "\n");
                        }
                        break;

                    // Prints every book we have in the library
                    case "PrintInventory":
                        writer.write("Inventory:\n");
                        for (Book inventoryBook : bookList) {
                            writer.write("Book: "
                                + inventoryBook.getName() + "\n");
                            writer.write("Author: "
                                + inventoryBook.getAuthor() + "\n");
                            writer.write("Publisher: "
                                + inventoryBook.getPublisher() + "\n");
                            writer.write("Publication Date: "
                                + inventoryBook.getPublicationDate() + "\n");
                            writer.write("\n");
                        }
                        break;

                    // Prints every member in the library
                    case "PRINT_MEMBERS":
                        final List<Member> members = inventory.getMembers();
                        for (Member member : members) {
                            writer.write("Member ID: "
                                + member.getId() + "\n");
                            writer.write("Member Name: "
                                + member.getName() + "\n");
                            writer.write("Amount Owed: $"
                                + member.getAmountOwed() + "\n");
                            final List<BorrowedBook> borrowedBooks =
                                member.getBorrowedBooks();
                            for (BorrowedBook borrowedBookItem
                                : borrowedBooks) {
                                writer.write("Book: "
                                    + borrowedBookItem.getBook().getName()
                                        + "\n");
                                writer.write("Borrowed Date: "
                                    + borrowedBookItem.getBorrowedDate()
                                        + "\n");
                                writer.write("Returned Date: "
                                    + borrowedBookItem.getReturnedDate()
                                        + "\n");
                            }
                            writer.write("\n");
                        }
                        break;

                    default:
                        writer.write("Invalid command: " + command + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final LibraryManagementSystem library = new LibraryManagementSystem();
        // Run the library management system with the
        // provided input and output files
        library.run("input.txt", "output.txt");
    }
}
