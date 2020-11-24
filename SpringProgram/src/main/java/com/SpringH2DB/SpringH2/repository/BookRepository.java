package com.SpringH2DB.SpringH2.repository;

import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.JoinStudentBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByAuthorContaining(String author);

    List<BookEntity> findAllByAuthorIsNotNullOrderByAuthor();

    @Query("SELECT new com.SpringH2DB.SpringH2.model.JoinStudentBook(b.title, s.name,s.surname)" +
            "FROM BookEntity b " +
            "INNER JOIN StudentEntity s ON b.author " +
            "LIKE CONCAT('%',s.surname) " +
            "GROUP BY b.author, b.title")
    List<JoinStudentBook> booksAndAuthors();

    List<BookEntity> findByPublicationGreaterThanEqual(Date publication);

    List<BookEntity> findByTitleStartingWith(String letters);

    List<BookEntity> findAllByAuthorIsNotNullOrderByPublicationAsc();

    List<BookEntity> findAllByAuthorIsNotNullOrderByPublicationDesc();
}
