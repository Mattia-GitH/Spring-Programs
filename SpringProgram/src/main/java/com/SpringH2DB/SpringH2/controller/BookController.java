package com.SpringH2DB.SpringH2.controller;

import com.SpringH2DB.SpringH2.model.BookModel;
import com.SpringH2DB.SpringH2.model.JoinStudentBook;
import com.SpringH2DB.SpringH2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    @GetMapping("/books-date/{date}")
    public ResponseEntity<List<BookModel>> publicatedAfterDate(@PathVariable Date date) {
        return ResponseEntity.ok().body(bookService.publicatedAfterDate(date));
    }

    @GetMapping("/books-title/{letters}")
    public ResponseEntity<List<BookModel>> titleStartingWith(@PathVariable String letters) {
        return ResponseEntity.ok().body(bookService.titleStartingWith(letters));
    }

    @GetMapping("/books-order/asc")
    public ResponseEntity<List<BookModel>> publicationOrderAsc() {
        return ResponseEntity.ok().body(bookService.orderByPublicationAsc());
    }

    @GetMapping("/books-order/desc")
    public ResponseEntity<List<BookModel>> publicationOrderDesc() {
        return ResponseEntity.ok().body(bookService.orderByPublicationDesc());
    }

    @GetMapping("/books-old/{publication}")
    public ResponseEntity<String> deleteOldBooks(@PathVariable String publication) {
        Date date = Date.valueOf(publication);
        bookService.deleteOldBooks(date);
        return ResponseEntity.ok().body("Deleted books older than " + publication);
    }

    @GetMapping("book-fix-author/{author}/{id}")
    public ResponseEntity<BookModel> fixAuthor(@PathVariable String author, @PathVariable long id){
        return ResponseEntity.ok().body(bookService.fixAuthor(author,id));
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
    public ResponseEntity<String> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().body("Deleted book with id: " + id);
    }
}
