package com.github.brandoncjw12;

import org.junit.Test;

public class BookTest {
    @Test 
    public void addToBookCatalog_success() {
        Book book1 = Book.makeBook("Narnia", "CS Lewis", 1);
        book1.addToBookCatalog();

        assert(Book.getBookName(1)).equals("Narnia");
        assert(Book.getBookAuthor(1)).equals("CS Lewis");
    }

    @Test
    public void addToBookCatalog_readdingTheSameBookId_oldValuesOverridden() {
        Book book1 = Book.makeBook("Narnia", "CS Lewis", 1);
        Book book2 = Book.makeBook("Narnia 2", "CS Lewis 2", 1);
        book1.addToBookCatalog();
        book2.addToBookCatalog();

        assert(Book.getBookName(1)).equals("Narnia 2");
        assert(Book.getBookAuthor(1)).equals("CS Lewis 2");
    }

    
}
