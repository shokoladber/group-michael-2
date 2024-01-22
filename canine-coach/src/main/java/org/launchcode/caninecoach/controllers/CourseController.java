package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.models.Course;
import org.launchcode.caninecoach.models.data.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("add-course")
    public String displayAddCourseForm(Model model) {
        model.addAttribute(new Course());
        return "add-course";
    }

    @PostMapping("add-course")
    public String processAddJobForm(@ModelAttribute  Course newCourse,
                                    Errors errors) {

        if (errors.hasErrors()) {
            return "add-course";
        }

        courseRepository.save(newCourse);
        return "redirect:";
    }
}