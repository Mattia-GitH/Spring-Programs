package com.SpringH2DB.SpringH2;

import com.SpringH2DB.SpringH2.convert.BookConvert;
import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.BookModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookTest {

    @Autowired
    BookConvert bookConvert;

    @Test
    void converterBidirectionalModelEntity() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("Autore");
        bookEntity.setTitle("Titolo");
        bookEntity.setIsbn("isbn");

        BookModel bookModel = bookConvert.toBookModel(bookEntity);
        BookEntity convertedToModel = bookConvert.toBookEntity(bookModel);

        Assertions.assertEquals(convertedToModel, bookEntity);
    }
}
