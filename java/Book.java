package java;
import java.util.Map;

public class Book{
    private static Map<Integer, String> bookIdToAuthor;
    private static Map<Integer, String> bookIdToName;

    String name;
    String author;
    int uniqueId;

    Book(String name, String author, int uniqueId) {
        this.name = name;
        this.author = author;
        this.uniqueId = uniqueId;
    }

    public void addToBookCatalog() {
        bookIdToAuthor.put(this.uniqueId, this.author);
        bookIdToName.put(this.uniqueId, this.name);
    }

    public static String getBookName(Integer bookId) {
        return bookIdToName.getOrDefault(bookId, "");
    }

    public static String getBookAuthor(Integer bookId) {
        return bookIdToAuthor.getOrDefault(bookId, "");
    }

    public Integer getId() {
        return this.uniqueId;
    }
}