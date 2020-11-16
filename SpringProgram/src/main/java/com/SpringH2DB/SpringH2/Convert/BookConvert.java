package com.SpringH2DB.SpringH2.Convert;

import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.Book;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;


public class BookConvert {

    public static Book toBookModel(BookEntity bookEntity) {
        Book book = new Book();
        book.setTitle(bookEntity.getTitle());
        book.setAuthor(bookEntity.getAuthor());
        book.setIsbn(bookEntity.getIsbn());
        return book;
    }

    public static List<Book> listBooks(List<BookEntity> bookEntities) {
        return bookEntities.stream().map(x -> toBookModel(x)).collect(Collectors.toList());
    }

    public static BookEntity toBookEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setIsbn(book.getIsbn());
        return bookEntity;
    }

    public static List<BookEntity> listBooksEntity(List<Book> books) {
        return books.stream().map(x -> toBookEntity(x)).collect(Collectors.toList());
    }
}
