package com.SpringH2DB.SpringH2.repository;

import com.SpringH2DB.SpringH2.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
