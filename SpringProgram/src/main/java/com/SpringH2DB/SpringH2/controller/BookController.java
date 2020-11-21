package com.SpringH2DB.SpringH2.controller;

import com.SpringH2DB.SpringH2.model.BookModel;
import com.SpringH2DB.SpringH2.model.JoinStudentBook;
import com.SpringH2DB.SpringH2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookModel>> listBooks() {
        return ResponseEntity.ok().body(bookService.listBook());
    }

    @GetMapping("/booksAndAuthors")
    public ResponseEntity<List<JoinStudentBook>> booksAndAuthors() {
        return ResponseEntity.ok().body(bookService.booksAndAuthors());
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookModel> bookByID(@PathVariable Long id) {
        return ResponseEntity.ok().body(bookService.bookById(id));
    }

    @GetMapping("/books/{author}")
    public ResponseEntity<List<BookModel>> findByAuthor(@PathVariable String author) {
        return ResponseEntity.ok().body(bookService.findByAuthor(author));
    }

    @GetMapping("/books/order")
    public ResponseEntity<List<BookModel>> orederByAuthor() {
        return ResponseEntity.ok().body(bookService.orderByAuthor());
    }

    @PostMapping("/book")
    public ResponseEntity<BookModel> createBook(@RequestBody BookModel bookModel) {
        return ResponseEntity.ok().body(bookService.createBook(bookModel));
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id, @RequestBody BookModel bookModel) {
        bookModel.setId(id);
        return ResponseEntity.ok().body(bookService.updateBook(bookModel));
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }
}
