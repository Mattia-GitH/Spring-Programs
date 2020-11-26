package com.SpringH2DB.SpringH2.repository;

import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.JoinStudentBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByAuthorContaining(String author);

    List<BookEntity> findAllByAuthorIsNotNullOrderByAuthor();

    List<BookEntity> findByPublicationGreaterThanEqual(Date publication);

    List<BookEntity> findByTitleStartingWith(String letters);

    List<BookEntity> findAllByAuthorIsNotNullOrderByPublicationAsc();

    List<BookEntity> findAllByAuthorIsNotNullOrderByPublicationDesc();

    @Query("SELECT new com.SpringH2DB.SpringH2.model.JoinStudentBook(b.title, s.name,s.surname)" +
            "FROM BookEntity b " +
            "INNER JOIN StudentEntity s ON b.author " +
            "LIKE CONCAT('%',s.surname) " +
            "GROUP BY b.author, b.title")
    List<JoinStudentBook> booksAndAuthors();

    @Modifying
    @Query("DELETE FROM BookEntity b WHERE b.publication < ?1")
    void deleteOldBooks(Date publication);

    @Modifying
    @Query("UPDATE BookEntity b SET b.author = ?1 WHERE b.id = ?2")
    void fixAuthor(String author, long id);
}
