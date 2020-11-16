package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    Student createStudent(Student student);

    Student updateStudent(Student student);

    List<Student> listStudents();

    Student studentById(long id);

    void deleteStudent(long id);
}
