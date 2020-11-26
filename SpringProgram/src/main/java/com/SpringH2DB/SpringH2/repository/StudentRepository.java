package com.SpringH2DB.SpringH2.repository;

import com.SpringH2DB.SpringH2.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findByName(String name);

    List<StudentEntity> findByAgeGreaterThanEqual(int age);

    List<StudentEntity> findByActiveFalse();

    List<StudentEntity> findByActiveTrue();

    List<StudentEntity> findByNameIsNotNullOrderBySurnameAsc();

    List<StudentEntity> findByNameIsNotNullOrderByActiveDesc();

    @Modifying
    @Query("UPDATE StudentEntity s SET s.active = ?1 WHERE s.id = ?2 ")
    void changeActive(boolean active, long id);

    @Modifying
    @Query("DELETE FROM StudentEntity s WHERE s.active=FALSE")
    void deleteInactive();
}
