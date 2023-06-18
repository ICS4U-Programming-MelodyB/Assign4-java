import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of the all the books
 * in the library.
 * It also inherits properties from
 * LibraryItem.
 *
 * @author  Melody Berhane
 * @version 1.0
 *
 * @since 2023-06-16.
 */

public class Inventory {
    // List of books in the inventory
    private List<Book> books;
    // List of members in the library
    private List<Member> members;

    // Constructor to initialize the inventory
    public Inventory() {
        // Create an empty list for books
        this.books = new ArrayList<>();
        // Create an empty list for members
        this.members = new ArrayList<>();
    }

    // Add a book to the inventory
    public void addBook(Book book) {
        books.add(book);
    }

    // Get the list of books in the inventory
    public List<Book> getBooks() {
        return books;
    }

    // Get the list of members in the library
    public List<Member> getMembers() {
        return members;
    }

    // Remove a book from the inventory
    public void removeBook(Book book) {
        books.remove(book);
    }

    // Add a member to the library
    public void addMember(Member member) {
        members.add(member);
    }

    // Search for a book by name
    public Book searchBook(String name) {
        for (Book book : books) {
            // Check if the book name matches
            if (book.getName().equalsIgnoreCase(name)) {
                // Return the book if found
                return book;
            }
        }
        // Return null if the book is not found
        return null;
    }

    // Find a member by name
    public Member findMember(String name) {
        for (Member member : members) {
            // Check if the member name matches
            if (member.getName().equalsIgnoreCase(name)) {
                // Return the member if found
                return member;
            }
        }
        // Return null if the member is not found
        return null;
    }
}
