package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.convert.BookConvert;
import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.exception.BookNotFoundException;
import com.SpringH2DB.SpringH2.model.BookModel;
import com.SpringH2DB.SpringH2.model.JoinStudentBook;
import com.SpringH2DB.SpringH2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements BookService {

    private final BookRepository repository;
    private final BookConvert bookConvert;

    @Autowired
    public BookServiceImp(BookRepository repository, BookConvert bookConvert) {
        this.repository = repository;
        this.bookConvert = bookConvert;
    }

    @Override
    public BookModel createBook(BookModel bookModel) {
        BookEntity toSave = bookConvert.toBookEntity(bookModel);
        repository.save(toSave);
        return bookConvert.toBookModel(toSave);
    }

    @Override
    public BookModel updateBook(BookModel bookModel) {
        Optional<BookEntity> bookOpt = repository.findById(bookModel.getId());
        if (bookOpt.isPresent()) {
            BookEntity bookEntityUpdate = bookOpt.get();
            bookEntityUpdate.setAuthor(bookModel.getAuthor());
            bookEntityUpdate.setIsbn(bookModel.getIsbn());
            bookEntityUpdate.setTitle(bookModel.getTitle());
            bookEntityUpdate.setPublication(bookModel.getPublication());
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

    @Override
    public List<BookModel> findByAuthor(String author) {
        List<BookEntity> bookEntities = repository.findByAuthorContaining(author);
        return bookConvert.listBooks(bookEntities);
    }

    @Override
    public List<BookModel> orderByAuthor() {
        List<BookEntity> bookEntities = repository.findAllByAuthorIsNotNullOrderByAuthor();
        return bookConvert.listBooks(bookEntities);
    }

    @Override
    public List<JoinStudentBook> booksAndAuthors() {
        List<JoinStudentBook> listBooksAndAuthors = repository.booksAndAuthors();
        return listBooksAndAuthors.stream().collect(Collectors.toList());
    }

    @Override
    public List<BookModel> publishedAfterDate(Date publication) {
        List<BookEntity> bookEntities = repository.findByPublicationGreaterThanEqual(publication);
        return bookConvert.listBooks(bookEntities);
    }

    @Override
    public List<BookModel> titleStartingWith(String letters) {
        List<BookEntity> bookEntities = repository.findByTitleStartingWith(letters);
        return bookConvert.listBooks(bookEntities);
    }

    @Override
    public List<BookModel> orderByPublicationAsc() {
        List<BookEntity> bookEntities = repository.findAllByAuthorIsNotNullOrderByPublicationAsc();
        return bookConvert.listBooks(bookEntities);
    }

    @Override
    public List<BookModel> orderByPublicationDesc() {
        List<BookEntity> bookEntities = repository.findAllByAuthorIsNotNullOrderByPublicationDesc();
        return bookConvert.listBooks(bookEntities);
    }

    @Transactional
    @Override
    public void deleteOldBooks(Date publication) {
        repository.deleteOldBooks(publication);
    }

    @Transactional
    @Override
    public BookModel fixAuthor(String author, long id) {
        Optional<BookEntity> bookEntity = repository.findById(id);
        if (bookEntity.isPresent()) {
            repository.fixAuthor(author, id);
            return bookConvert.toBookModel(bookEntity.get());
        } else {
            throw new BookNotFoundException("book not found id: " + id);
        }
    }
}
