package com.lambdaschool.school;

import com.lambdaschool.school.model.*;
import com.lambdaschool.school.repository.CourseRepository;
import com.lambdaschool.school.repository.InstructorRepository;
import com.lambdaschool.school.repository.StudentRepository;
import com.lambdaschool.school.repository.UserRepository;
import com.lambdaschool.school.service.RoleService;
import com.lambdaschool.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
//@Component
public class SeedData implements CommandLineRunner
{
    private CourseRepository courserepos;
    private StudentRepository studentrepos;
    private InstructorRepository instructorrepos;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    public SeedData(CourseRepository courserepos, StudentRepository studentrepos, InstructorRepository instructorrepos)
    {
        this.courserepos = courserepos;
        this.studentrepos = studentrepos;
        this.instructorrepos = instructorrepos;

    }

    @Override
    public void run(String[] args) throws Exception
    {
        Instructor i1 = new Instructor("Sally");
        Instructor i2 = new Instructor("Lucy");
        Instructor i3 = new Instructor("Charlie");

        Student s1 = new Student("John");
        Student s2 = new Student("Julian");
        Student s3 = new Student("Mary");
        Student s4 = new Student("Julian");
        Student s5 = new Student("Tyler");
        Student s6 = new Student("Kim");
        Student s7 = new Student("Juan");
        Student s8 = new Student("Robby");
        Student s9 = new Student("Roberto");
        Student s10 = new Student("Bob");
        Student s11 = new Student("Liz");
        Student s12 = new Student("June");
        Student s13 = new Student("April");

        Course c1 = new Course("Data Science", i1);
        Course c2 = new Course("JavaScript", i1);
        Course c3 = new Course("Node.js", i1);
        Course c4 = new Course("Java Back End", i2);
        Course c5 = new Course("Mobile IOS", i2);
        Course c6 = new Course("Mobile Android", i3);

        s1.getCourses()
          .add(c1);
        s1.getCourses()
          .add(c4);
        s2.getCourses()
          .add(c2);
        s3.getCourses()
          .add(c3);
        s3.getCourses()
          .add(c1);
        s3.getCourses()
          .add(c6);


        instructorrepos.save(i1);
        instructorrepos.save(i2);
        instructorrepos.save(i3);

        studentrepos.save(s1);
        studentrepos.save(s2);
        studentrepos.save(s3);
        studentrepos.save(s4);
        studentrepos.save(s5);
        studentrepos.save(s6);
        studentrepos.save(s7);
        studentrepos.save(s8);
        studentrepos.save(s9);
        studentrepos.save(s10);
        studentrepos.save(s11);
        studentrepos.save(s12);
        studentrepos.save(s13);

        courserepos.save(c1);
        courserepos.save(c2);
        courserepos.save(c3);
        courserepos.save(c4);
        courserepos.save(c5);
        courserepos.save(c6);

        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);
    }
}
