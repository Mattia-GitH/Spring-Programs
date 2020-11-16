package com.SpringH2DB.SpringH2;

import com.SpringH2DB.SpringH2.Convert.StudentConvert;
import com.SpringH2DB.SpringH2.entity.StudentEntity;
import com.SpringH2DB.SpringH2.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentTest {

    @Test
    void converter(){
        Student student = new Student();
        student.setId(1);
        student.setName("Mattia");
        student.setSurname("Schiavo");
        student.setAge(18);

        StudentEntity studentEntity = StudentConvert.toEntity(student);
        Student studentModel = StudentConvert.toModel(studentEntity);

        Assertions.assertEquals(student, studentModel);
    }
}
