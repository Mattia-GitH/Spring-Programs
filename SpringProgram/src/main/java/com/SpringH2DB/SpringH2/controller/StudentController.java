package com.SpringH2DB.SpringH2.controller;

import com.SpringH2DB.SpringH2.model.StudentModel;
import com.SpringH2DB.SpringH2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentModel>> listStudents(){
        return ResponseEntity.ok().body(studentService.listStudents());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentModel> studentById(@PathVariable long id){
        return ResponseEntity.ok().body(studentService.studentById(id));
    }

    @GetMapping("/students/{name}")
    public ResponseEntity<List<StudentModel>> findByName(@PathVariable String name){
        return ResponseEntity.ok().body(studentService.findByName(name));
    }

    @GetMapping("/students_age/{age}")
    public ResponseEntity<List<StudentModel>> findByName(@PathVariable int age){
        return ResponseEntity.ok().body(studentService.ageGreaterThanEqual(age));
    }


    @PostMapping("/student")
    public ResponseEntity<StudentModel> createStudent(@RequestBody StudentModel studentModel){
        return ResponseEntity.ok().body(studentService.createStudent(studentModel));
    }

    @PutMapping("/student/{id}")
    public  ResponseEntity<StudentModel> updateStudent(@PathVariable Long id, @RequestBody StudentModel studentModel){
        studentModel.setId(id);
        return ResponseEntity.ok().body(studentService.updateStudent(studentModel));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id){
        studentService.deleteStudent(id);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }

}