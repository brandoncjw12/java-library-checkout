package com.github.brandoncjw12;

import java.time.Instant;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CatalogTest {
    @Test 
    public void borrowBookCatalogUpdate_success(){
        Book book1 = Book.makeBook("Narnia", "CS Lewis", 1);
        book1.addToBookCatalog();
        Instant randomTimestamp = Instant.ofEpochMilli(2000);
        
        boolean check = Catalog.borrowBookCatalogUpdate(1, "user1", randomTimestamp);

        assertTrue(check);
        assert(Catalog.getBookOwner(1)).equals("user1");
        assert(Catalog.getDueDate(1)).equals(randomTimestamp);
    }
}
