package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private final CourseRepository courseRepository;

    public SearchController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/")
    public List<Object[]> search(@RequestParam("query") String keyword) {
        return courseRepository.searchCoursesWithKeyword(keyword);
    }

}
