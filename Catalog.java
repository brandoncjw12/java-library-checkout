import java.security.Timestamp;
import java.util.Map;

public class Catalog {
    private static Map<Integer, Timestamp> dueDateMap;
    private static Map<Integer, String> bookToOwnerMap;

    public void borrowBookCatalogUpdate(Integer bookId, String username, Timestamp dueDate) {
        if (dueDateMap.containsKey(bookId) || bookToOwnerMap.containsKey(bookId)) {
            System.out.println("Book is already checked out!");
            return;
        }

        dueDateMap.put(bookId, dueDate);
        bookToOwnerMap.put(bookId, username);
    }

    public void returnBookCatalogUpdate(Integer bookId, String username, Timestamp returnDate) {
        if (!dueDateMap.containsKey(bookId) || !bookToOwnerMap.containsKey(bookId)) {
            System.out.println("Book is not checked out!");
            return;
        }

        if (!bookToOwnerMap.get(bookId).equals(username)) {
            System.out.println(String.format("Book does not belong to %s", username));
            return;
        }

        
    }
}
