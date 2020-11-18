package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.convert.BookConvert;
import com.SpringH2DB.SpringH2.exception.BookNotFoundException;
import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.BookModel;
import com.SpringH2DB.SpringH2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookConvert bookConvert;

    @Override
    public BookModel createBook(BookModel bookModel) {
        repository.save(bookConvert.toBookEntity(bookModel));
        return bookModel;
    }

    @Override
    public BookModel updateBook(BookModel bookModel) {
        Optional<BookEntity> bookOpt = repository.findById(bookModel.getId());
        if (bookOpt.isPresent()) {
            BookEntity bookEntityUpdate = bookOpt.get();
            bookEntityUpdate.setAuthor(bookModel.getAuthor());
            bookEntityUpdate.setIsbn(bookModel.getIsbn());
            bookEntityUpdate.setTitle(bookModel.getTitle());
            repository.save(bookEntityUpdate);
            return bookConvert.toBookModel(bookEntityUpdate);
        } else {
            throw new BookNotFoundException("Book not found " + bookModel.getId());
        }
    }

    @Override
    public List<BookModel> listBook() {
        List<BookEntity> list = repository.findAll();
        return bookConvert.listBooks(list);
    }

    @Override
    public BookModel bookById(long bookId) {
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
