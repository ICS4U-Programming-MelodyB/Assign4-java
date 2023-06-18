/**
 * This is the parent class that
 * stores the name of the book, the
 * author, the publisher and publish date.
 *
 * @author  Melody Berhane
 * @version 1.0
 *
 * @since 2023-06-16.
 */

class LibraryItem {
    // Name of the library item
    protected String name;
    // Author of the library item
    protected String author;
    // Publisher of the library item
    protected String publisher;
    // Publication date of the library item
    protected String publicationDate;

    public LibraryItem(String name,
        String author, String publisher, String publicationDate) {
        // Initialize the name
        this.name = name;
        // Initialize the author
        this.author = author;
        // Initialize the publisher
        this.publisher = publisher;
        // Initialize the publication date
        this.publicationDate = publicationDate;
    }
}

class Book extends LibraryItem {
    public Book(String name,
        String author, String publisher, String publicationDate) {
        // Call the superclass constructor with the provided parameters
        super(name, author, publisher, publicationDate);
    }
}

class BorrowedBook extends LibraryItem {
    // Date when the book was borrowed
    private String borrowedDate;
    // Date when the book was returned
    private String returnedDate;

    public BorrowedBook(Book book, String borrowedDate) {
        // Call the superclass constructor with the properties of the book
        super(book.name, book.author, book.publisher, book.publicationDate);
        // Set the borrowed date
        this.borrowedDate = borrowedDate;
        // Initialize the returned date as null
        this.returnedDate = null;
    }

    public void returnBook(String returnedDate) {
        // Set the returned date of the borrowed book
        this.returnedDate = returnedDate;
    }
}
