package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.model.StudentModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void testing_createStudent(){
        StudentModel toCreate = new StudentModel();
        toCreate.setName("Name");
        toCreate.setSurname("Surname");
        toCreate.setAge(18);

        StudentModel created = studentService.createStudent(toCreate);
        toCreate.setId(created.getId());

        Assertions.assertEquals(toCreate, created);
    }

    @Test
    void testing_update(){
        StudentModel toUpdate = new StudentModel();
        toUpdate.setName("Name");
        studentService.createStudent(toUpdate);

        StudentModel update = new StudentModel();
        update.setId(1);
        update.setName("Name Updated");
        StudentModel updated = studentService.updateStudent(update);

        Assertions.assertEquals(update.getName(), updated.getName());
    }

    @Test
    void testing_listStudents(){
        StudentModel student1 = new StudentModel();
        StudentModel student2 = new StudentModel();

        studentService.createStudent(student1);
        studentService.createStudent(student2);

        List<StudentModel> studentModelList = studentService.listStudents();

        Assertions.assertEquals(2,studentModelList.size());
    }

    @Test
    void testing_delete(){
        StudentModel studentModel = new StudentModel();
        studentModel.setName("Name");
        studentModel.setSurname("Surname");
        studentService.createStudent(studentModel);

        studentService.deleteStudent(1L);
        List<StudentModel> studentModelList = studentService.listStudents();

        Assertions.assertEquals(0,studentModelList.size());
    }
}
