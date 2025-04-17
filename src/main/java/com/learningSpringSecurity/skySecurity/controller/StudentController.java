package com.learningSpringSecurity.skySecurity.controller;

import com.learningSpringSecurity.skySecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> studentList= new ArrayList<>(List.of(
            new Student(1, "Akash", 82),
            new Student(2,"Sawan", 78)
    ));

    @GetMapping("/Students")
    public List<Student> getAllStudents(){
        return studentList;

    }

    @GetMapping("/")
    public String greet(HttpServletRequest request){

        return "Hello Akash "+ request.getSession().getId();
    }

    @PostMapping("/Students")
    public Student saveStudent(@RequestBody Student student){
         studentList.add(student);
         return student;

    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        CsrfToken csrf = (CsrfToken) request.getAttribute("_csrf");
        return csrf;

    }





}
