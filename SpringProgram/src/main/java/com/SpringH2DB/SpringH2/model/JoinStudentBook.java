package com.SpringH2DB.SpringH2.model;

import java.util.Objects;

public class JoinStudentBook {
    private String title;
    private String name;
    private String surname;

    public JoinStudentBook() {
    }

    public JoinStudentBook(String title, String name, String surname) {
        this.title = title;
        this.name = name;
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinStudentBook that = (JoinStudentBook) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, name, surname);
    }

    @Override
    public String toString() {
        return "JoinStudentBook{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
