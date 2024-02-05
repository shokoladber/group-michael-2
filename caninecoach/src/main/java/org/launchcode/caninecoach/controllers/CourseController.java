package org.launchcode.caninecoach.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.api.services.classroom.model.Course;
import org.launchcode.caninecoach.services.GoogleClassroomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final Logger log = LoggerFactory.getLogger(CourseController.class);

    private final GoogleClassroomService googleClassroomService;

    public CourseController(GoogleClassroomService googleClassroomService) {
        this.googleClassroomService = googleClassroomService;
    }

    @GetMapping("/list")
    public List<Course> listCourses() {
        try {
            return googleClassroomService.listGoogleCourses();
        } catch (IOException e) {
            log.error("Error while fetching classes", e);
            return Collections.emptyList();
        }
    }
}
