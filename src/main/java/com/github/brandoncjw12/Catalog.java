package com.github.brandoncjw12;
import java.util.Map;
import java.util.HashMap;
import java.time.Instant;

public class Catalog {
    private static Map<Integer, Instant> dueDateMap = new HashMap<>();
    private static Map<Integer, String> bookToOwnerMap = new HashMap<>();

    public static boolean borrowBookCatalogUpdate(int bookId, String username, Instant dueDate) {
        if (dueDateMap.containsKey(bookId) || bookToOwnerMap.containsKey(bookId)) {
            System.out.println("Book is already checked out!");
            return false;
        }

        dueDateMap.put(bookId, dueDate);
        bookToOwnerMap.put(bookId, username);
        return true;
    }

    public static boolean returnBookCatalogUpdate(int bookId, String username, Instant returnDate) {
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

    public static String getBookOwner(int bookId) {
        return bookToOwnerMap.getOrDefault(bookId, "Book is not currently checked out!");
    }

    public static Instant getDueDate(int bookId) {
        return dueDateMap.getOrDefault(bookId, Instant.ofEpochMilli(-1));
    }
}
