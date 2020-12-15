package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.convert.BookConvert;
import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.BookModel;
import com.SpringH2DB.SpringH2.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookMockTest {

    @InjectMocks
    BookServiceImp bookService;

    @Mock
    BookRepository bookRepository;

    @Spy
    BookConvert bookConvert;

    @Test
    void testing_createBook(){
        BookModel bookModel = new BookModel();
        bookModel.setAuthor("Author");
        bookModel.setTitle("Title");

        BookEntity toSave = new BookEntity();
        toSave.setAuthor("Author");
        toSave.setTitle("Title");

        Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(toSave);

        BookModel saved = bookService.createBook(bookModel);

        Assertions.assertEquals(bookModel, saved);
        Mockito.verify(bookRepository, Mockito.times(1)).save(toSave);
        Mockito.verify(bookConvert, Mockito.times(1)).toBookEntity(bookModel);
    }

    @Test
    void testing_updateBook(){
        BookModel bookModel = new BookModel();
        bookModel.setAuthor("Author");
        bookModel.setTitle("Title");

        BookEntity toSave = new BookEntity();
        toSave.setAuthor("Author");
        toSave.setTitle("Title");

        Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(toSave);
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(toSave));

        bookService.createBook(bookModel);

        BookModel update = new BookModel();
        update.setId(1);
        update.setAuthor("Author Update");
        update.setTitle("Title Update");

        BookModel updated = bookService.updateBook(update);
        updated.setId(1L);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(update, updated);
        Mockito.verify(bookRepository, Mockito.times(1)).save(toSave);
        Mockito.verify(bookConvert, Mockito.times(1)).toBookEntity(bookModel);
    }

    @Test
    void testing_listBook(){
        List<BookEntity> bookEntitiesList = new ArrayList<>();
        bookEntitiesList.add(new BookEntity());
        bookEntitiesList.add(new BookEntity());

        Mockito.when(bookRepository.findAll()).thenReturn(bookEntitiesList);
        List<BookModel> bookEntities = bookConvert.listBooks(bookEntitiesList);

        List<BookModel> bookModels = bookService.listBook();

        Assertions.assertEquals(bookEntities, bookModels);
        Mockito.verify(bookRepository, Mockito.times(1)).findAll();
        Mockito.verify(bookConvert, Mockito.times(2)).listBooks(bookRepository.findAll());
    }

    @Test
    void testing_delete(){
        BookModel bookModel = new BookModel();
        bookModel.setId(1L);

        BookEntity toSave = new BookEntity();
        toSave.setId(1L);

        Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(toSave);
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(toSave));
        bookRepository.save(toSave);

        bookService.deleteBook(1L);

        Mockito.verify(bookRepository, Mockito.times(1)).delete(toSave);
        Mockito.verify(bookRepository, Mockito.times(1)).findById(toSave.getId());
    }

    @Test
    void testing_titleStartingWith(){
        BookModel bookA = new BookModel();
        bookA.setId(1L);
        bookA.setTitle("A");
        BookModel bookB = new BookModel();
        bookB.setId(2L);
        bookB.setTitle("B");

        BookEntity savedA = new BookEntity();
        bookA.setId(1L);
        bookA.setTitle("A");
        BookEntity savedB = new BookEntity();
        bookB.setId(2L);
        bookB.setTitle("B");

        List<BookEntity> results = new ArrayList<>();
        results.add(savedA);

        Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(savedA);
        bookService.createBook(bookA);

        Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(savedB);
        bookService.createBook(bookB);

        Mockito.when(bookRepository.findByTitleStartingWith("A")).thenReturn(results);

        List<BookModel> bookModels = bookService.titleStartingWith("A");
        Assertions.assertEquals(1,bookModels.size());
    }
}
