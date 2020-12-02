package com.SpringH2DB.SpringH2.repository;

import com.SpringH2DB.SpringH2.entity.StudentEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testing_findById_and_save() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAge(18);
        studentEntity.setName("Mattia");
        studentEntity.setActive(true);

        StudentEntity saved = studentRepository.save(studentEntity);
        StudentEntity toFind = studentRepository.findById(saved.getId()).orElse(null);

        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(saved, toFind);
    }

    @Test
    void testing_update() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAge(18);
        studentEntity.setName("Mattia");
        studentEntity.setActive(true);

        studentRepository.save(studentEntity);
        studentEntity.setAge(17);
        studentRepository.save(studentEntity);

        Assertions.assertEquals(17, studentEntity.getAge());
    }

    @Test
    void testing_deleteById() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1);
        studentEntity.setAge(18);
        studentEntity.setName("Mattia");
        studentEntity.setActive(true);

        StudentEntity save = studentRepository.save(studentEntity);
        studentRepository.deleteById(save.getId());

        Assertions.assertFalse(studentRepository.findById(save.getId()).isPresent());
    }

    @Test
    void testing_findAll() {
        for (int i = 0; i <= 8; i++) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(i);
            studentRepository.save(studentEntity);
        }
        Assertions.assertEquals(8, studentRepository.findAll().size());
    }

    @Test
    void testing_deleteInactive() {
        StudentEntity studentActive = new StudentEntity();
        studentActive.setActive(true);
        StudentEntity studentInactive = new StudentEntity();
        studentInactive.setActive(false);

        studentRepository.save(studentActive);
        studentRepository.save(studentInactive);

        studentRepository.deleteInactive();

        Assertions.assertEquals(1, studentRepository.findAll().size());
    }

    @Test
    void testing_findByActiveTrue_and_findByActiveFalse() {
        StudentEntity studentActive = new StudentEntity();
        studentActive.setActive(true);
        StudentEntity studentInactive = new StudentEntity();
        studentInactive.setActive(false);

        studentRepository.save(studentActive);
        studentRepository.save(studentInactive);

        Assertions.assertEquals(1, studentRepository.findByActiveTrue().size());
        Assertions.assertEquals(1, studentRepository.findByActiveFalse().size());
    }

    @Test
    void testing_findByAgeGreaterThanEqual() {
        for (int i = 0; i < 10; i++) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setAge(i);
            studentRepository.save(studentEntity);
        }

        List<StudentEntity> studentEntityList = studentRepository.findByAgeGreaterThanEqual(5);

        Assertions.assertEquals(5, studentEntityList.size());
    }
}
