package com.SpringH2DB.SpringH2.convert;

import com.SpringH2DB.SpringH2.entity.StudentEntity;
import com.SpringH2DB.SpringH2.model.StudentModel;

import java.util.List;
import java.util.stream.Collectors;

public class StudentConvert {

    private StudentConvert() {

    }

    public static StudentModel toModel(StudentEntity studentEntity) {
        StudentModel studentModel = new StudentModel();
        studentModel.setId(studentEntity.getId());
        studentModel.setName(studentEntity.getName());
        studentModel.setSurname(studentEntity.getSurname());
        studentModel.setAge(studentEntity.getAge());
        studentModel.setActive(studentEntity.isActive());
        return studentModel;
    }

    public static StudentEntity toEntity(StudentModel studentModel) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentModel.getId());
        studentEntity.setName(studentModel.getName());
        studentEntity.setSurname(studentModel.getSurname());
        studentEntity.setAge(studentModel.getAge());
        studentEntity.setActive(studentModel.isActive());
        return studentEntity;
    }

    public static List<StudentModel> listStudentsModel(List<StudentEntity> studentEntities) {
        return studentEntities.stream().map(StudentConvert::toModel).collect(Collectors.toList());
    }

    public static List<StudentEntity> listStudentsEntity(List<StudentModel> studentModels) {
        return studentModels.stream().map(StudentConvert::toEntity).collect(Collectors.toList());
    }
}
