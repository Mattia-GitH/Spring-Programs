package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.convert.StudentConvert;
import com.SpringH2DB.SpringH2.entity.StudentEntity;
import com.SpringH2DB.SpringH2.exception.StudentNotFoundException;
import com.SpringH2DB.SpringH2.model.StudentModel;
import com.SpringH2DB.SpringH2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService{

    @Autowired
    StudentRepository repository;

    @Override
    public StudentModel createStudent(StudentModel studentModel) {
        repository.save(StudentConvert.toEntity(studentModel));
        return studentModel;
    }

    @Override
    public StudentModel updateStudent(StudentModel studentModel) {
        Optional<StudentEntity> studentOpt = repository.findById(studentModel.getId());
        if(studentOpt.isPresent()){
            StudentEntity studentEntityUpdate = studentOpt.get();
            studentEntityUpdate.setName(studentModel.getName());
            studentEntityUpdate.setSurname(studentModel.getSurname());
            studentEntityUpdate.setAge(studentModel.getAge());
            studentEntityUpdate.setActive(studentModel.isActive());
            repository.save(studentEntityUpdate);
            return StudentConvert.toModel(studentEntityUpdate);
        }else {
            throw new StudentNotFoundException("Student not found " + studentModel.getId());
        }
    }

    @Override
    public List<StudentModel> listStudents() {
        List<StudentEntity> studentEntityList = repository.findAll();
        return StudentConvert.listStudentsModel(studentEntityList);
    }

    @Override
    public StudentModel studentById(long id) {
        Optional<StudentEntity> studentOpt = repository.findById(id);
        if (studentOpt.isPresent()){
            return StudentConvert.toModel(studentOpt.get());
        }else {
            throw new StudentNotFoundException("Student not found " + id);
        }
    }

    @Override
    public void deleteStudent(long id) {
        Optional<StudentEntity> studentEntityOptional = repository.findById(id);
        if(studentEntityOptional.isPresent()){
            repository.delete(studentEntityOptional.get());
        } else {
            throw new StudentNotFoundException("Student not found " + id);
        }
    }

    @Override
    public List<StudentModel> findByName(String name) {
        List<StudentEntity> studentEntityList = repository.findByName(name);
        return StudentConvert.listStudentsModel(studentEntityList);
    }

    @Override
    public List<StudentModel> ageGreaterThanEqual(int age) {
        List<StudentEntity> studentEntityList = repository.findByAgeGreaterThanEqual(age);
        return StudentConvert.listStudentsModel(studentEntityList);
    }

    @Override
    public List<StudentModel> studentInactive() {
        List<StudentEntity> studentEntityList = repository.findByActiveFalse();
        return StudentConvert.listStudentsModel(studentEntityList);
    }

    @Override
    public List<StudentModel> studentActive() {
        List<StudentEntity> studentEntityList = repository.findByActiveTrue();
        return StudentConvert.listStudentsModel(studentEntityList);
    }

    @Override
    public List<StudentModel> orderBySurnameAsc() {
        List<StudentEntity> studentEntityList = repository.findByNameIsNotNullOrderBySurnameAsc();
        return StudentConvert.listStudentsModel(studentEntityList);
    }

    @Override
    public List<StudentModel> orderByActiveDesc() {
        List<StudentEntity> studentEntityList = repository.findByNameIsNotNullOrderByActiveDesc();
        return StudentConvert.listStudentsModel(studentEntityList);
    }
}