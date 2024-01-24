package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.data.CourseRepository;
import org.launchcode.caninecoach.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;



@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Course> courses;
        if (searchTerm.isBlank()) {
            courses = (ArrayList<Course>) courseRepository.findAll();
        } else {
            courses = courseRepository.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("title", "Classes with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("Classes", courses);
        model.addAttribute("columns", columnChoices);

        return "search";
    }
}
