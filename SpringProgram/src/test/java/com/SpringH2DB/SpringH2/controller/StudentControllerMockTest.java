package com.SpringH2DB.SpringH2.controller;


import com.SpringH2DB.SpringH2.model.StudentModel;
import com.SpringH2DB.SpringH2.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(StudentController.class)
public class StudentControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private JacksonTester<StudentModel> studentJSONTest;

    @Autowired
    private JacksonTester<List<StudentModel>> listJSONTester;


    //Testing With Assertions
    @Test
    void testing_get_user_by_id() throws Exception {
        StudentModel student = new StudentModel();
        student.setName("Name");
        student.setSurname("Surname");
        student.setAge(18);
        student.setActive(true);


        Mockito.when(studentService.studentById(any(long.class))).thenReturn(student);

        MockHttpServletResponse response = mockMvc.perform(
                get("/api/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(response.getContentAsString(), studentJSONTest.write(student).getJson());
    }

    //Testing without Assertions
    @Test
    void testing_get_user_by_id_No_Assertions() throws Exception {
        StudentModel student = new StudentModel();
        student.setName("Name");
        student.setSurname("Surname");
        student.setAge(18);
        student.setActive(true);


        Mockito.when(studentService.studentById(any(long.class))).thenReturn(student);

        mockMvc.perform(get("/api/student/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(studentJSONTest.write(student).getJson()))
                .andReturn().getResponse();
    }

    @Test
    void testing_get_list_of_users() throws Exception {
        List<StudentModel> studentsList = new ArrayList<>();
        studentsList.add(new StudentModel());
        studentsList.add(new StudentModel());

        Mockito.when(studentService.listStudents()).thenReturn(studentsList);

        mockMvc.perform(get("/api/students")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(listJSONTester.write(studentsList).getJson()))
                .andReturn().getResponse();
    }

    @Test
    void testing_get_list_of_users_active() throws Exception {
        StudentModel studentActive = new StudentModel();
        studentActive.setName("Active");
        studentActive.setActive(true);

        List<StudentModel> studentsListActive = new ArrayList<>();
        studentsListActive.add(studentActive);

        Mockito.when(studentService.studentActive()).thenReturn(studentsListActive);

        mockMvc.perform(get("/api/students-active")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(listJSONTester.write(studentsListActive).getJson()))
                .andReturn().getResponse();
    }

    @Test
    void testing_create_student() throws Exception {
        StudentModel student = new StudentModel();
        student.setName("Name");
        student.setSurname("Surname");
        student.setAge(18);
        student.setActive(true);

        Mockito.when(studentService.createStudent(any(StudentModel.class))).thenReturn(student);

        mockMvc.perform(post("/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":0,\"name\":\"Name\",\"surname\":\"Surname\",\"age\":18,\"active\":true}"))
                .andExpect(status().isOk())
                .andExpect(content().json(studentJSONTest.write(student).getJson()))
                .andReturn().getResponse();
    }

    @Test
    void testing_update_student_with_id() throws Exception {
        StudentModel student = new StudentModel();
        student.setName("NameUpdated");
        student.setSurname("SurnameUpdated");
        student.setAge(18);
        student.setActive(true);

        Mockito.when(studentService.updateStudent(any(StudentModel.class))).thenReturn(student);

        mockMvc.perform(
                put("/api/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSONTest.write(student).getJson()))
                .andExpect(status().isOk())
                .andExpect(content().json(studentJSONTest.write(student).getJson()))
                .andReturn().getResponse();
    }
}
