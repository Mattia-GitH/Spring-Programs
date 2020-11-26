package com.SpringH2DB.SpringH2.controller;

import com.SpringH2DB.SpringH2.model.StudentModel;
import com.SpringH2DB.SpringH2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentModel>> listStudents() {
        return ResponseEntity.ok().body(studentService.listStudents());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentModel> studentById(@PathVariable long id) {
        return ResponseEntity.ok().body(studentService.studentById(id));
    }

    @GetMapping("/students/{name}")
    public ResponseEntity<List<StudentModel>> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(studentService.findByName(name));
    }

    @GetMapping("/students-age/{age}")
    public ResponseEntity<List<StudentModel>> findByName(@PathVariable int age) {
        return ResponseEntity.ok().body(studentService.ageGreaterThanEqual(age));
    }

    @GetMapping("/students-inactive")
    public ResponseEntity<List<StudentModel>> inactiveStudents() {
        return ResponseEntity.ok().body(studentService.studentInactive());
    }

    @GetMapping("/students-active")
    public ResponseEntity<List<StudentModel>> activeStudents() {
        return ResponseEntity.ok().body(studentService.studentActive());
    }

    @GetMapping("/students-order/surname")
    public ResponseEntity<List<StudentModel>> orderBySurname() {
        return ResponseEntity.ok().body(studentService.orderBySurnameAsc());
    }

    @GetMapping("/students-order/active")
    public ResponseEntity<List<StudentModel>> orderByActive() {
        return ResponseEntity.ok().body(studentService.orderByActiveDesc());
    }

    @GetMapping("/student-active/{status}/{id}")
    public ResponseEntity<StudentModel> changeActive(@PathVariable boolean status, @PathVariable long id) {
        return ResponseEntity.ok().body(studentService.changeActive(status, id));
    }

    @GetMapping("/students-inactive/delete")
    public ResponseEntity<String> deleteInactive() {
        studentService.deleteInactive();
        return ResponseEntity.ok().body("deleted all students inactive");
    }

    @GetMapping("/student-age/{age}/{id}")
    public ResponseEntity<StudentModel> updateAge(@PathVariable int age, @PathVariable long id){
        return ResponseEntity.ok().body(studentService.updateAge(age, id));
    }

    @PostMapping("/student")
    public ResponseEntity<StudentModel> createStudent(@RequestBody StudentModel studentModel) {
        return ResponseEntity.ok().body(studentService.createStudent(studentModel));
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentModel> updateStudent(@PathVariable Long id, @RequestBody StudentModel studentModel) {
        studentModel.setId(id);
        return ResponseEntity.ok().body(studentService.updateStudent(studentModel));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().body("Delete student id:" + id);
    }
}
