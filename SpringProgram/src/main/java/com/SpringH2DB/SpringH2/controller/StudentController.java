package com.SpringH2DB.SpringH2.controller;

import com.SpringH2DB.SpringH2.model.Student;
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
    public ResponseEntity<List<Student>> listStudents(){
        return ResponseEntity.ok().body(studentService.listStudents());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> studentById(@PathVariable long id){
        return ResponseEntity.ok().body(studentService.studentById(id));
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.ok().body(studentService.createStudent(student));
    }

    @PutMapping("/student/{id}")
    public  ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        student.setId(id);
        return ResponseEntity.ok().body(studentService.updateStudent(student));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id){
        studentService.deleteStudent(id);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }

}
