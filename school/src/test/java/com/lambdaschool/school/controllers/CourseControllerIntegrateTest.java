package com.lambdaschool.school.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.InstructorService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrateTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private InstructorService instructorService;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext()
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    // GET /courses/courses
    @Test
    public void courseAllResponseTime()
    {
        given().when()
               .get("/courses/courses")
               .then()
               .time(lessThan(3000L));
    }

    // POST /courses/course/add
    @Test
    public void givenPostACourse() throws Exception
    {

        //    Instructor s5 = new Instructor("Sally");
        //    instructorrepos.save(s5);
        //    s5.getCourses().add(new Course("Java Fundamentals", s5));
        //    Course c7 = new Course("Java Fundamentals", s5);

        Instructor s1 = instructorService.findInstructorById(1);
        Course c7 = new Course("Java Fundamentals", s1);


        ObjectMapper mapper = new ObjectMapper();
        String stringC7 = mapper.writeValueAsString(c7);

        given().contentType("application/json")
               .body(stringC7)
               .when()
               .post("/courses/course/add")
               .then()
               .statusCode(201);
        System.out.println("C7String " + stringC7);
    }
}
