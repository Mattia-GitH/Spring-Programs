package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.model.BookModel;
import com.SpringH2DB.SpringH2.model.JoinStudentBook;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface BookService {

    BookModel createBook(BookModel bookModel);

    BookModel updateBook(BookModel bookModel);

    List<BookModel> listBook();

    BookModel bookById(long bookId);

    void deleteBook(long id);

    List<BookModel> findByAuthor(String author);

    List<BookModel> orderByAuthor();

    List<JoinStudentBook> booksAndAuthors();

    List<BookModel> publicatedAfterDate(Date publication);

    List<BookModel> titleStartingWith(String letters);

    List<BookModel> orderByPublicationAsc();

    List<BookModel> orderByPublicationDesc();

}
