package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.Convert.StudentConvert;
import com.SpringH2DB.SpringH2.entity.StudentEntity;
import com.SpringH2DB.SpringH2.exception.StudentNotFoundException;
import com.SpringH2DB.SpringH2.model.Student;
import com.SpringH2DB.SpringH2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService{

    @Autowired
    StudentRepository repository;

    StudentConvert studentConvert;

    @Override
    public Student createStudent(Student student) {
        repository.save(studentConvert.toEntity(student));
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        Optional<StudentEntity> studentOpt = repository.findById(student.getId());
        if(studentOpt.isPresent()){
            StudentEntity studentEntityUpdate = studentOpt.get();
            studentEntityUpdate.setId(student.getId());
            studentEntityUpdate.setName(student.getName());
            studentEntityUpdate.setSurname(student.getSurname());
            studentEntityUpdate.setAge(student.getAge());
            repository.save(studentEntityUpdate);
            return studentConvert.toModel(studentEntityUpdate);
        }else {
            throw new StudentNotFoundException("Student not found " + student.getId());
        }
    }

    @Override
    public List<Student> listStudents() {
        List<StudentEntity> studentEntityList = repository.findAll();
        return studentConvert.listStudentsModel(studentEntityList);
    }

    @Override
    public Student studentById(long id) {
        Optional<StudentEntity> studentOpt = repository.findById(id);
        if (studentOpt.isPresent()){
            return studentConvert.toModel(studentOpt.get());
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
}
