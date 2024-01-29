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

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "index";
    }
}
