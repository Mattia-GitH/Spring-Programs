package com.SpringH2DB.SpringH2.controller;

import com.SpringH2DB.SpringH2.model.Book;
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
    public ResponseEntity<List<Book>> listBooks() {
        return ResponseEntity.ok().body(bookService.listBook());
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> bookByID(@PathVariable Long id) {
        return ResponseEntity.ok().body(bookService.bookById(id));
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok().body(bookService.createBook(book));
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return ResponseEntity.ok().body(bookService.updateBook(book));
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }
}
