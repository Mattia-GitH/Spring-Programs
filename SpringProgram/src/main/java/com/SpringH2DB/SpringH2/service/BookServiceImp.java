package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.Convert.BookConvert;
import com.SpringH2DB.SpringH2.exception.BookNotFoundException;
import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.Book;
import com.SpringH2DB.SpringH2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository repository;

    private BookConvert bookConvert;

    @Override
    public Book createBook(Book book) {
        repository.save(bookConvert.toBookEntity(book));
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        Optional<BookEntity> bookOpt = repository.findById(book.getId());
        if (bookOpt.isPresent()) {
            BookEntity bookEntityUpdate = bookOpt.get();
            bookEntityUpdate.setId(book.getId());
            bookEntityUpdate.setAuthor(book.getAuthor());
            bookEntityUpdate.setIsbn(book.getIsbn());
            bookEntityUpdate.setTitle(book.getTitle());
            repository.save(bookEntityUpdate);
            return bookConvert.toBookModel(bookEntityUpdate);
        } else {
            throw new BookNotFoundException("Book not found " + book.getId());
        }
    }

    @Override
    public List<Book> listBook() {
        List<BookEntity> list = repository.findAll();
        return bookConvert.listBooks(list);
    }

    @Override
    public Book bookById(long bookId) {
        Optional<BookEntity> bookOpt = repository.findById(bookId);
        if (bookOpt.isPresent()) {
            return bookConvert.toBookModel(bookOpt.get());
        } else {
            throw new BookNotFoundException("Book not found " + bookId);
        }
    }

    @Override
    public void deleteBook(long bookId) {
        Optional<BookEntity> bookOpt = repository.findById(bookId);
        if (bookOpt.isPresent()) {
            repository.delete(bookOpt.get());
        } else {
            throw new BookNotFoundException("Book not found " + bookId);
        }
    }
}
