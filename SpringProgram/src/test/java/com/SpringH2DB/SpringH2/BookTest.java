package com.SpringH2DB.SpringH2;

import com.SpringH2DB.SpringH2.Convert.BookConvert;
import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    void converter() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("Autore");
        bookEntity.setTitle("Titolo");
        bookEntity.setIsbn("isbn");

        Book book = BookConvert.toBookModel(bookEntity);
        BookEntity convertedToModel = BookConvert.toBookEntity(book);

        Assertions.assertEquals(convertedToModel, bookEntity);
    }
}
