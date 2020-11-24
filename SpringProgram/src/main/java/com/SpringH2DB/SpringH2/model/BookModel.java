package com.SpringH2DB.SpringH2.model;

import java.util.Objects;

public class BookModel {

    private long id;
    private String title;
    private String author;
    private String isbn;
    private boolean active;

    public BookModel() {
    }

    public BookModel(long id, String title, String author, String isbn, boolean active) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookModel bookModel = (BookModel) o;
        return id == bookModel.id &&
                active == bookModel.active &&
                Objects.equals(title, bookModel.title) &&
                Objects.equals(author, bookModel.author) &&
                Objects.equals(isbn, bookModel.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, active);
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", active=" + active +
                '}';
    }
}
