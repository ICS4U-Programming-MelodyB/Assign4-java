/**
 * Keeps track of who borrows the
 * book and who returns it.
 *
 * @author  Melody Berhane
 * @version 1.0
 *
 * @since 2023-06-16.
 */

public class BorrowedBook {
    private Book book;
    private String borrowedDate;
    private String returnedDate;

    public BorrowedBook(Book book, String borrowedDate) {
        this.book = book;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

    // Getter method for book
    public Book getBook() {
        return book;
    }

    // Getter method for borrowedDate
    public String getBorrowedDate() {
        return borrowedDate;
    }

    // Getter method for returnedDate
    public String getReturnedDate() {
        return returnedDate;
    }

    // Set the returnedDate
    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

    // Calculate the fine for the borrowed book
    public double calculateFine() {
        // Calculate fine
        return 0.0;
    }
}
