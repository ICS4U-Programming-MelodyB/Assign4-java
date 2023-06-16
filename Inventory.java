/**
 * Keeps track of the total num of book in 
 * the library and the number of books signed out.
 *
 * @author  Melody Berhane
 * @version 1.0
 *
 * @since 2023-06-14.
 */
public class Inventory {
    // Class attributes
    private static int totalBooks = 0;
    private static int booksSignedOut = 0;

    // Constructor
    public Inventory() {
        totalBooks = 0;
        booksSignedOut = 0;
    }

    // Methods
    public boolean addBook(Book book) {
        if (book instanceof Book) {
            totalBooks++;
            return true;
        } else {
            return false;
        }
    }

    public boolean signOut() {
        if (booksSignedOut < totalBooks) {
            booksSignedOut++;
            return true;
        } else {
            return false;
        }
    }

    public boolean returnBook() {
        if (booksSignedOut > 0) {
            booksSignedOut--;
            return true;
        } else {
            return false;
        }
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public int getBooksSignedOut() {
        return booksSignedOut;
    }
}
