package com.SpringH2DB.SpringH2.model;

import java.sql.Date;
import java.util.Objects;

public class StudentModel {

    private long id;
    private String name;
    private String surname;
    private int age;
    private Date publication;

    public StudentModel() {
    }

    public StudentModel(long id, String name, String surname, int age, Date publication) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.publication = publication;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        StudentModel that = (StudentModel) o;
        return id == that.id &&
                age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(publication, that.publication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, publication);
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", publication=" + publication +
                '}';
    }
}
