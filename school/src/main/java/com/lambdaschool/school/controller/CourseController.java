package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.ErrorDetail;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import com.lambdaschool.school.view.CountStudentsInCourses;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/courses")
public class CourseController
{
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "Return all Courses", response = Course.class, responseContainer = "List")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve(0..N)"),
                               @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Results of records per page"),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "All Course's Found", response = Course.class),
            @ApiResponse(code = 404, message = "Course's Not Found", response = ErrorDetail.class)})
    @GetMapping(value = "/courses", produces = {"application/json"})
    public ResponseEntity<?> listAllCoursesWithPaging(@PageableDefault(page = 0, size = 3, sort = {"coursename"}, direction = Sort.Direction.ASC)Pageable pageable)
    {
        ArrayList<Course> myCourses = courseService.findAll(pageable);
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }

    @ApiOperation(value = "Return the Student Count", response = Course.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Student Count Found", response = Course.class),
            @ApiResponse(code = 404, message = "Student Count Not Found", response = ErrorDetail.class)})
    @GetMapping(value = "/studcount", produces = {"application/json"})
    public ResponseEntity<?> getCountStudentsInCourses()
    {
        return new ResponseEntity<>(courseService.getCountStudentsInCourse(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Course By Id", response = Course.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Course Deleted By Id", response = void.class),
            @ApiResponse(code = 500, message = "Course Not Deleted By Id", response = ErrorDetail.class)})
    @DeleteMapping("/courses/{courseid}")
    public ResponseEntity<?> deleteCourseById(@PathVariable long courseid)
    {
        courseService.delete(courseid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
