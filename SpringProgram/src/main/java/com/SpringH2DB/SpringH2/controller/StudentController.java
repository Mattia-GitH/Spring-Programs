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

    @GetMapping("/students-age/{age}")
    public ResponseEntity<List<StudentModel>> findByName(@PathVariable int age){
        return ResponseEntity.ok().body(studentService.ageGreaterThanEqual(age));
    }

    @GetMapping("/students-inactive")
    public ResponseEntity<List<StudentModel>> inactiveStudents(){
        return ResponseEntity.ok().body(studentService.studentInactive());
    }

    @GetMapping("/students-active")
    public ResponseEntity<List<StudentModel>> activeStudents(){
        return ResponseEntity.ok().body(studentService.studentActive());
    }

    @GetMapping("/students-order/surname")
    public ResponseEntity<List<StudentModel>> orderBySurname(){
        return ResponseEntity.ok().body(studentService.orderBySurnameAsc());
    }

    @GetMapping("/students-order/active")
    public ResponseEntity<List<StudentModel>> orderByActive(){
        return ResponseEntity.ok().body(studentService.orderByActiveDesc());
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

    @PutMapping("/student-active/")
    public ResponseEntity<StudentModel> changeActive(@RequestBody StudentModel studentModel){
        return ResponseEntity.ok().body(studentService.changeActive(studentModel));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().body("Delete student id:" + id);
    }

    @DeleteMapping("/students-inactive/delete")
    public ResponseEntity<String> deleteInactive(){
        studentService.deleteInactive();
        return ResponseEntity.ok().body("deleted all students inactive");
    }
}
