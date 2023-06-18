/**
 * Stores the books name, author
 * publisher, publish date and if
 * it was borrowed by someone
 * Inherits properties from LibraryItem.
 *
 * @author  Melody Berhane
 * @version 1.0
 *
 * @since 2023-06-16.
 */

public class Book {
    private String name;
    private String author;
    private String publisher;
    private String publicationDate;
    private Member borrowedBy;

    public Book(String name,
        String author, String publisher, String publicationDate) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.borrowedBy = null;
    }

    // Getters and setters

    /**
     * Get the member who has borrowed the book.
     *
     * @return The member who has borrowed the book.
     */
    public Member getBorrowedBy() {
        return borrowedBy;
    }

    /**
     * Set the member who has borrowed the book.
     *
     * @param borrowedBy The member who has borrowed the book.
     */
    public void setBorrowedBy(Member borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    /**
     * Get the name of the book.
     *
     * @return The name of the book.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get the publisher of the book.
     *
     * @return The publisher of the book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Get the publication date of the book.
     *
     * @return The publication date of the book.
     */
    public String getPublicationDate() {
        return publicationDate;
    }
}
