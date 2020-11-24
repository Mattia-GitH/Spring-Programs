package com.SpringH2DB.SpringH2.repository;

import com.SpringH2DB.SpringH2.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findByName(String name);

    List<StudentEntity> findByAgeGreaterThanEqual(int age);

    List<StudentEntity> findByActiveFalse();

    List<StudentEntity> findByActiveTrue();

    List<StudentEntity> findByNameIsNotNullOrderBySurnameAsc();

    List<StudentEntity>findByNameIsNotNullOrderByActiveDesc();

}
