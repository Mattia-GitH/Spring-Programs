package com.SpringH2DB.SpringH2.model;

import java.sql.Date;
import java.util.Objects;

public class BookModel {

    private long id;
    private String title;
    private String author;
    private String isbn;
    private Date publication;

    public BookModel() {
    }

    public BookModel(long id, String title, String author, String isbn, Date publication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publication = publication;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookModel bookModel = (BookModel) o;
        return id == bookModel.id &&
                publication == bookModel.publication &&
                Objects.equals(title, bookModel.title) &&
                Objects.equals(author, bookModel.author) &&
                Objects.equals(isbn, bookModel.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, publication);
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publication=" + publication +
                '}';
    }
}
