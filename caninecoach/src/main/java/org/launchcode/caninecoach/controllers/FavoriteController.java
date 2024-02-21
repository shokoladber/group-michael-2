package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.Course;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFavoriteCourse(@RequestParam Course course, @RequestParam User user) {
        // Call the service method to add the course to favorites
        favoriteService.addFavoriteCourse(course, user);
        return ResponseEntity.ok("Course added to favorites successfully.");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeFavoriteCourse(@RequestParam Course course, @RequestParam User user) {
        // Call the service method to remove the course from favorites
        favoriteService.removeFavoriteCourse(course, user);
        return ResponseEntity.ok("Course removed from favorites successfully.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Course>> getFavoriteCourses(@RequestParam User user) {
        // Call the service method to retrieve favorite courses for the user
        List<Course> favoriteCourses = favoriteService.getFavoriteCourses(user);
        return ResponseEntity.ok(favoriteCourses);
    }
}

