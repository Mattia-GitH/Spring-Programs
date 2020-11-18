package com.SpringH2DB.SpringH2;

import com.SpringH2DB.SpringH2.convert.StudentConvert;
import com.SpringH2DB.SpringH2.entity.StudentEntity;
import com.SpringH2DB.SpringH2.model.StudentModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentTest {

    @Test
    void converterBidirectionalModelEntity(){
        StudentModel student = new StudentModel();
        student.setId(1);
        student.setName("Mattia");
        student.setSurname("Schiavo");
        student.setAge(18);

        StudentEntity studentEntity = StudentConvert.toEntity(student);
        StudentModel studentModel = StudentConvert.toModel(studentEntity);

        Assertions.assertEquals(student, studentModel);
    }
}
