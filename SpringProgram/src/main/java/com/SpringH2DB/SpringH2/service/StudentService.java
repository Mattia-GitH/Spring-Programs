package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.model.StudentModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    StudentModel createStudent(StudentModel studentModel);

    StudentModel updateStudent(StudentModel studentModel);

    List<StudentModel> listStudents();

    StudentModel studentById(long id);

    void deleteStudent(long id);

    List<StudentModel> findByName(String name);

    List<StudentModel> ageGreaterThanEqual(int age);

}
