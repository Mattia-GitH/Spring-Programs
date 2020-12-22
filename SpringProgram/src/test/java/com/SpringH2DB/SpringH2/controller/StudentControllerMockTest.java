package com.SpringH2DB.SpringH2.controller;


import com.SpringH2DB.SpringH2.entity.StudentEntity;
import com.SpringH2DB.SpringH2.model.StudentModel;
import com.SpringH2DB.SpringH2.repository.StudentRepository;
import com.SpringH2DB.SpringH2.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


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

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assertions.assertEquals(response.getContentAsString(), studentJSONTest.write(student).getJson());
    }

    @Test
    void testing_get_list_of_users() throws Exception {
        List<StudentModel> studentsList = new ArrayList<>();
        studentsList.add(new StudentModel());
        studentsList.add(new StudentModel());

        Mockito.when(studentService.listStudents()).thenReturn(studentsList);

        MockHttpServletResponse response = mockMvc.perform(
                get("/api/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        System.out.println("here " + response.getContentLength());

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assertions.assertEquals(response.getContentAsString(), listJSONTester.write(studentsList).getJson());
    }


}
