package com.github.brandoncjw12;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class People {
    private static Map<String, List<Integer>> personToBorrowedBooks;
    private static Map<String, Integer> personToBalanceMap;

    String username;

    People(String username) {
        this.username = username;
    }

    public void borrowBook(Book book){
        book.addToBookCatalog();
        Integer bookId = book.getId();
        if (Catalog.borrowBookCatalogUpdate(bookId, this.username, Instant.now().plus(21, ChronoUnit.DAYS))) {
            personToBorrowedBooks.getOrDefault(personToBalanceMap, List.of()).add(bookId);
        };
    }

    public void returnBook(Book book) {
        Integer bookId = book.getId();
        if (Catalog.returnBookCatalogUpdate(bookId, this.username, Instant.now())) {
            if (!personToBorrowedBooks.getOrDefault(this.username, List.of()).contains(bookId)) {
                System.out.println("This person doesn't have this book!");
                return;
            }
            personToBorrowedBooks.getOrDefault(personToBalanceMap, List.of()).remove(bookId);
        }
    }

    public static void updateBalance(String username) {
        personToBalanceMap.put(username, personToBalanceMap.getOrDefault(username, 0) + 20);
    }

    public List<String> getAllBookNamesOwned() {
        List<String> allBooks = new ArrayList<>();
        for (Integer bookId : personToBorrowedBooks.getOrDefault(this.username, null)) {
            allBooks.add(Book.getBookName(bookId));
        }

        return allBooks;
    }

    public String getFavoriteAuthor() {
        Map<String, Integer> authorToCountMap = new HashMap<>();

        Integer currentMax = -1;
        String currentBestAuthor = "";
        for (Integer bookId : personToBorrowedBooks.getOrDefault(this.username, null)) {
            String currentAuthor = Book.getBookAuthor(bookId);
            Integer currentCount = authorToCountMap.getOrDefault(currentAuthor, 0);
            authorToCountMap.put(currentAuthor, currentCount + 1);
            if (currentCount + 1 > currentMax) {
                currentMax = currentCount + 1;
                currentBestAuthor = currentAuthor;
            }
        }
        return currentBestAuthor;
    }

    public static People makePerson(String username) {
        return new People(username);
    }
}
