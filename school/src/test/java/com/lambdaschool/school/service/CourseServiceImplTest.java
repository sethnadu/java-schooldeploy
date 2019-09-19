package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class CourseServiceImplTest
{
    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void deleteFound()
    {
        courseService.delete(1);
        assertEquals(5, courseService.findAll()
                                     .size());
    }

    @Test
    public void deleteNotFound()
    {
        courseService.delete(100);
        assertEquals(5, courseService.findAll()
                                     .size());
    }

    @Test
    public void save()
    {
        Instructor s1 = instructorService.findInstructorById(1);
        Course c7 = new Course("Java Fundamentals", s1);

        Course addCourse = courseService.save(c7);

        Course foundCourse = courseService.findCourseById(addCourse.getCourseid());
        assertEquals(addCourse.getCoursename(), foundCourse.getCoursename());

        System.out.println("Course " + c7.getCoursename());
        System.out.println("addCourse.getCoursename()" + addCourse.getCoursename());
        System.out.println("foundCourse.getCoursename()" + foundCourse.getCoursename());

    }
}