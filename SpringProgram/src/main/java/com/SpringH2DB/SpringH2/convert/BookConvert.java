package com.SpringH2DB.SpringH2.convert;

import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.BookModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConvert {

    public BookModel toBookModel(BookEntity bookEntity) {
        BookModel bookModel = new BookModel();
        bookModel.setTitle(bookEntity.getTitle());
        bookModel.setAuthor(bookEntity.getAuthor());
        bookModel.setIsbn(bookEntity.getIsbn());
        bookModel.setPublication(bookEntity.getPublication());
        return bookModel;
    }

    public List<BookModel> listBooks(List<BookEntity> bookEntities) {
        return bookEntities.stream().map(this::toBookModel).collect(Collectors.toList());
    }

    public BookEntity toBookEntity(BookModel bookModel) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookModel.getTitle());
        bookEntity.setAuthor(bookModel.getAuthor());
        bookEntity.setIsbn(bookModel.getIsbn());
        bookEntity.setPublication(bookModel.getPublication());
        return bookEntity;
    }

    public List<BookEntity> listBooksEntity(List<BookModel> bookModels) {
        return bookModels.stream().map(this::toBookEntity).collect(Collectors.toList());
    }
}
