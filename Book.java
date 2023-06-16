/**
 * Gets and stores the books name, publish date,
 * publisher and assigns it a special ID.
 *
 * @author  Melody Berhane
 * @version 1.0
 *
 * @since 2023-06-14.
 */
public class Book {
    // Class attributes
    private static int nextID = 1;

    // Instance variables
    private String name;
    private String publisher;
    private String publishingDate;
    private int id;

    // Constructor
    public Book(String name, String publisher, String publishingDate) {
        this.name = name;
        this.publisher = publisher;
        this.publishingDate = publishingDate;
        this.id = nextID;
        nextID++;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public int getId() {
        return id;
    }
}
