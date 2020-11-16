package com.SpringH2DB.SpringH2.Convert;

import com.SpringH2DB.SpringH2.entity.StudentEntity;
import com.SpringH2DB.SpringH2.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentConvert {

    public static Student toModel(StudentEntity studentEntity){
        Student student = new Student();
        student.setId(studentEntity.getId());
        student.setName(studentEntity.getName());
        student.setSurname(studentEntity.getSurname());
        student.setAge(studentEntity.getAge());
        return student;
    }

    public static StudentEntity toEntity(Student student){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        studentEntity.setName(student.getName());
        studentEntity.setSurname(student.getSurname());
        studentEntity.setAge(student.getAge());
        return studentEntity;
    }

    public static List<Student> listStudentsModel(List<StudentEntity> studentEntities){
        return studentEntities.stream().map(StudentConvert::toModel).collect(Collectors.toList());
    }

    public static List<StudentEntity> listStudentsEntity(List<Student> students){
        return  students.stream().map(StudentConvert::toEntity).collect(Collectors.toList());
    }
}
