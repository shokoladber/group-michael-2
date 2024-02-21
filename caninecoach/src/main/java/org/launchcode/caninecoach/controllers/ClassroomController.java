package org.launchcode.caninecoach.controllers;

import com.google.api.services.classroom.model.Announcement;
import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.Student;
import org.launchcode.caninecoach.repositories.CourseRepository;
import org.launchcode.caninecoach.services.GoogleClassroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/googleclassroom")
public class ClassroomController {

    private static final Logger log = LoggerFactory.getLogger(ClassroomController.class);
    private final GoogleClassroomService googleClassroomService;

    private final CourseRepository courseRepository;

    @Autowired
    public ClassroomController(GoogleClassroomService googleClassroomService, CourseRepository courseRepository) {
        this.googleClassroomService = googleClassroomService;
        this.courseRepository = courseRepository;
    }

    // Trainer can create course
    @PostMapping("/courses")
    @PreAuthorize("hasAuthority('ROLE_PET_TRAINER')")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = googleClassroomService.createCourse(course);
            return ResponseEntity.ok(createdCourse);
        } catch (Exception e) {
            log.error("Failed to create course", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Trainer can update course created
    @PatchMapping("/courses/{courseId}")
    @PreAuthorize("hasAuthority('ROLE_PET_TRAINER')")
    public ResponseEntity<?> updateCourse(@PathVariable String courseId, @RequestBody Course course) {
        try {
            Course updatedCourse = googleClassroomService.updateCourse(courseId, course);
            return ResponseEntity.ok(updatedCourse);
        } catch (Exception e) {
            log.error("Failed to update course", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Trainer can delete course created
    @DeleteMapping("/courses/{courseId}")
    @PreAuthorize("hasAuthority('ROLE_PET_TRAINER')")
    public ResponseEntity<?> deleteCourse(@PathVariable String courseId) {
        try {
            googleClassroomService.deleteCourse(courseId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Failed to delete course", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // All users can view list of courses available
    @GetMapping("/courses")
    public ResponseEntity<?> listCourses() {
        try {
            List<Course> courses = googleClassroomService.listCourses();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            log.error("Failed to list courses", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Guardian can enroll in selected course
    @PostMapping("/courses/{courseId}/students")
    @PreAuthorize("hasAuthority('ROLE_PET_GUARDIAN')")
    public ResponseEntity<?> enrollInCourse(@PathVariable String courseId, @RequestBody String studentEmail) {
        try {
            Student student = googleClassroomService.enrollStudentInCourse(courseId, studentEmail);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            log.error("Error enrolling student in course {}", courseId, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Trainer can view roster enrolled in course
    @GetMapping("/courses/{courseId}/students")
    @PreAuthorize("hasAuthority('ROLE_PET_TRAINER')")
    public ResponseEntity<?> getRoster(@PathVariable String courseId) {
        try {
            List<Student> students = googleClassroomService.getCourseRoster(courseId);
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            log.error("Error retrieving roster for course {}", courseId, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Trainer can create an announcement for selected course
    @PostMapping("/courses/{courseId}/announcements")
    @PreAuthorize("hasAuthority('ROLE_PET_TRAINER')")
    public ResponseEntity<?> createAnnouncement(@PathVariable String courseId, @RequestBody Announcement announcement) {
        try {
            Announcement createdAnnouncement = googleClassroomService.createAnnouncement(courseId, announcement);
            return ResponseEntity.ok(createdAnnouncement);
        } catch (Exception e) {
            log.error("Failed to create announcement", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Trainer can delete announcement created for course
    @DeleteMapping("/courses/{courseId}/announcements/{announcementId}")
    @PreAuthorize("hasAuthority('ROLE_PET_TRAINER')")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable String courseId, @PathVariable String announcementId) {
        try {
            googleClassroomService.deleteAnnouncement(courseId, announcementId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Failed to delete announcement", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Trainer can update announcement created for course
    @PatchMapping("/courses/{courseId}/announcements/{announcementId}")
    @PreAuthorize("hasAuthority('ROLE_PET_TRAINER')")
    public ResponseEntity<?> updateAnnouncement(@PathVariable String courseId, @PathVariable String announcementId, @RequestBody Announcement announcement) {
        try {
            Announcement updatedAnnouncement = googleClassroomService.updateAnnouncement(courseId, announcementId, announcement);
            return ResponseEntity.ok(updatedAnnouncement);
        } catch (Exception e) {
            log.error("Failed to update announcement", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // All users can view announcement for course
    @GetMapping("/courses/{courseId}/announcements")
    public ResponseEntity<?> listAnnouncements(@PathVariable String courseId) {
        try {
            List<Announcement> announcements = googleClassroomService.listAnnouncements(courseId);
            return ResponseEntity.ok(announcements);
        } catch (Exception e) {
            log.error("Failed to list announcements", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}