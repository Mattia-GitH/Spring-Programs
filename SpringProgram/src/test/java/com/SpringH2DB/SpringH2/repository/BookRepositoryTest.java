package com.SpringH2DB.SpringH2.repository;

import com.SpringH2DB.SpringH2.entity.BookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    //Generator of random date used for testing: testing_OrderByPublicationAsc_and_Desc
    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static Date createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        LocalDate localDate = LocalDate.of(year, month, day);
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        return date;
    }

    @Test
    void testing_OrderByPublicationAsc_and_Desc() {
        for (int i = 0; i < 10; i++) {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setAuthor("ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(i, i + 1));
            bookEntity.setPublication(createRandomDate(1000, 2020));
            bookRepository.save(bookEntity);
        }

        List<BookEntity> sortedByAsc = bookRepository.findAllByAuthorIsNotNullOrderByPublicationAsc();
        List<BookEntity> sortedByDesc = bookRepository.findAllByAuthorIsNotNullOrderByPublicationDesc();
        List<BookEntity> sortedByAsc2 = bookRepository.findAllByAuthorIsNotNullOrderByPublicationAsc();
        List<BookEntity> sortedByDesc2 = bookRepository.findAllByAuthorIsNotNullOrderByPublicationDesc();

        Assertions.assertEquals(sortedByAsc, sortedByAsc2);
        Assertions.assertEquals(sortedByDesc, sortedByDesc2);
    }
}
