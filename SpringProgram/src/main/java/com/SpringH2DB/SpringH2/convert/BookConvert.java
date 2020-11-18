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
        return bookModel;
    }

    public List<BookModel> listBooks(List<BookEntity> bookEntities) {
        return bookEntities.stream().map(x -> toBookModel(x)).collect(Collectors.toList());
    }

    public BookEntity toBookEntity(BookModel bookModel) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookModel.getTitle());
        bookEntity.setAuthor(bookModel.getAuthor());
        bookEntity.setIsbn(bookModel.getIsbn());
        return bookEntity;
    }

    public List<BookEntity> listBooksEntity(List<BookModel> bookModels) {
        return bookModels.stream().map(x -> toBookEntity(x)).collect(Collectors.toList());
    }
}
