package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    Book createBook(Book book);

    Book updateBook(Book book);

    List<Book> listBook();

    Book bookById(long bookId);

    void deleteBook(long id);
}
