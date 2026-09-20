package com.github.brandoncjw12;
import java.util.Map;
import java.util.HashMap;

public class Book{
    private static Map<Integer, String> bookIdToAuthor = new HashMap<>();
    private static Map<Integer, String> bookIdToName = new HashMap<>();

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

    public static String getBookName(int bookId) {
        return bookIdToName.getOrDefault(bookId, "");
    }

    public static String getBookAuthor(int bookId) {
        return bookIdToAuthor.getOrDefault(bookId, "");
    }

    public int getId() {
        return this.uniqueId;
    }

    public static Book makeBook(String name, String author, int bookId) {
        return new Book(name, author, bookId);
    }
}