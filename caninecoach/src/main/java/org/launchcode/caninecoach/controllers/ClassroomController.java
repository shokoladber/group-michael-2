package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.services.GoogleClassroomService;
import com.google.api.services.classroom.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

    private final GoogleClassroomService googleClassroomService;

    @Autowired
    public ClassroomController(GoogleClassroomService googleClassroomService) {
        this.googleClassroomService = googleClassroomService;
    }

    @GetMapping("/courses")
    public List<Course> getCourses() throws Exception {
        return googleClassroomService.listCourses();
    }
}
