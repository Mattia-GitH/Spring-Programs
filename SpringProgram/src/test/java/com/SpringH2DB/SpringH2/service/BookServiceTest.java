package com.SpringH2DB.SpringH2.service;


import com.SpringH2DB.SpringH2.model.BookModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void testing_createBook() {
        BookModel toCreate = new BookModel();
        toCreate.setAuthor("Author");
        toCreate.setTitle("Title");

        BookModel created = bookService.createBook(toCreate);
        toCreate.setId(created.getId());

        Assertions.assertEquals(created, toCreate);
    }

    @Test
    void testing_update() {
        BookModel oldBook = new BookModel();
        oldBook.setAuthor("Author");
        bookService.createBook(oldBook);

        BookModel update = new BookModel();
        update.setId(1);
        update.setAuthor("Author Updated");
        BookModel updated = bookService.updateBook(update);

        Assertions.assertEquals(update.getAuthor(), updated.getAuthor());
    }

    @Test
    void testing_listBook() {
        BookModel book1 = new BookModel();
        BookModel book2 = new BookModel();

        bookService.createBook(book1);
        bookService.createBook(book2);

        List<BookModel> bookModels = bookService.listBook();

        Assertions.assertEquals(2, bookModels.size());
    }

    @Test
    void testing_delete() {
        BookModel bookModel = new BookModel();
        bookModel.setAuthor("Author");
        bookModel.setTitle("Title");
        bookService.createBook(bookModel);

        bookService.deleteBook(1L);
        List<BookModel> bookModels = bookService.listBook();

        Assertions.assertEquals(0, bookModels.size());
    }


}
