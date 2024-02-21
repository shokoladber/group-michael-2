package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.repositories.CourseRepository;
import org.launchcode.caninecoach.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {
    private static final Logger log = LoggerFactory.getLogger(ClassroomController.class);



    private final CourseRepository courseRepository;


    private final CourseService courseService;

    @Autowired
    public ClassroomController(CourseRepository courseRepository, CourseService courseService) {

        this.courseRepository = courseRepository;
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Object[]>> getCourses() {
        try {
            List<Object[]> courses = courseRepository.findAllCoursesWithDetails();
            if (courses.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            log.error("Failed to list courses", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

   /* @PostMapping("/enrollStudent")
    public ResponseEntity<?> enrollStudent(@RequestParam String courseId, @RequestParam String studentEmail) {
        try {
            Student student = googleClassroomService.addStudentToCourse(courseId, studentEmail);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            log.error("Error enrolling student: {}", studentEmail, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enrolling student");
        }

    }*/
}

