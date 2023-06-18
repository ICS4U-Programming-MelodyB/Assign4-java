import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of the members information
 * like their name and their ID.
 *
 * @author  Melody Berhane
 * @version 1.0
 *
 * @since 2023-06-16.
 */

public class Member {
    // Static variable to generate unique member IDs
    private static int idCounter = 1;
    // Member ID
    private int id;
    // Member name
    private String name;
    // List of books borrowed by the member
    private List<BorrowedBook> borrowedBooks;
    // Amount owed by the member
    private double amountOwed;

    // Constructor to initialize a member with a name
    public Member(String name) {
        // Assign a unique ID to the member and increment the counter
        this.id = idCounter++;
        // Set the member name
        this.name = name;
        // Create an empty list for borrowed books
        this.borrowedBooks = new ArrayList<>();
        // Initialize the amount owed to zero
        this.amountOwed = 0.0;
    }

    // Getters and setters

    /**
     * Get the ID of the member.
     *
     * @return The member ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the name of the member.
     *
     * @return The member name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the list of borrowed books by the member.
     *
     * @return The list of borrowed books.
     */
    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Get the amount owed by the member.
     *
     * @return The amount owed.
     */
    public double getAmountOwed() {
        return amountOwed;
    }

    /**
     * Borrow a book and add it to the member's borrowed books list.
     *
     * @param borrowedBook The book to be borrowed.
     */
    public void borrowBook(BorrowedBook borrowedBook) {
        borrowedBooks.add(borrowedBook);
    }

    /**
     * Set the amount owed by the member.
     *
     * @param amountOwed The new amount owed.
     */
    public void setAmountOwed(double amountOwed) {
        this.amountOwed = amountOwed;
    }

    /**
     * Return a book and calculate the fine if applicable.
     * The book will be removed from the member's borrowed books list.
     *
     * @param book The book to be returned.
     */
    public void returnBook(Book book) {
        BorrowedBook foundBook = null;
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getBook().equals(book)) {
                foundBook = borrowedBook;
                break;
            }
        }
        if (foundBook != null) {
            borrowedBooks.remove(foundBook);
            final double fine = foundBook.calculateFine();
            amountOwed += fine;
        }
    }
}
