package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.model.BookModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    BookModel createBook(BookModel bookModel);

    BookModel updateBook(BookModel bookModel);

    List<BookModel> listBook();

    BookModel bookById(long bookId);

    void deleteBook(long id);
}
