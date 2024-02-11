package org.launchcode.caninecoach.controllers;

import com.google.api.services.classroom.model.Student;
import org.launchcode.caninecoach.services.GoogleClassroomService;
import com.google.api.services.classroom.model.Course;
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
    private final GoogleClassroomService googleClassroomService;

    @Autowired
    public ClassroomController(GoogleClassroomService googleClassroomService) {
        this.googleClassroomService = googleClassroomService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses() {
        try {
            List<Course> courses = googleClassroomService.listCourses();
            if (courses.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            log.error("Failed to list courses", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/enrollStudent")
    public ResponseEntity<?> enrollStudent(@RequestParam String courseId, @RequestParam String studentEmail) {
        try {
            Student student = googleClassroomService.addStudentToCourse(courseId, studentEmail);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            log.error("Error enrolling student: {}", studentEmail, e);
            // Customize the error response as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enrolling student");
        }

    }
}

