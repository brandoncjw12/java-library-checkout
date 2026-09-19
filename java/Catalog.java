package java;
import java.util.Map;
import java.time.Instant;

public class Catalog {
    private static Map<Integer, Instant> dueDateMap;
    private static Map<Integer, String> bookToOwnerMap;

    public static boolean borrowBookCatalogUpdate(Integer bookId, String username, Instant dueDate) {
        if (dueDateMap.containsKey(bookId) || bookToOwnerMap.containsKey(bookId)) {
            System.out.println("Book is already checked out!");
            return false;
        }

        dueDateMap.put(bookId, dueDate);
        bookToOwnerMap.put(bookId, username);
        return true;
    }

    public static boolean returnBookCatalogUpdate(Integer bookId, String username, Instant returnDate) {
        if (!dueDateMap.containsKey(bookId) || !bookToOwnerMap.containsKey(bookId)) {
            System.out.println("Book is not checked out!");
            return false;
        }

        if (!bookToOwnerMap.get(bookId).equals(username)) {
            System.out.println(String.format("Book does not belong to %s", username));
            return false;
        }

        if (returnDate.isAfter(dueDateMap.get(bookId))) {
            People.updateBalance(username);
        }
        dueDateMap.remove(bookId);
        bookToOwnerMap.remove(bookId);
        return true;
    }

    public static String getBookOwner(Integer bookId) {
        return bookToOwnerMap.getOrDefault(bookId, "Book is not currently checked out!");
    }
}
