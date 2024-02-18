package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.Course;
import org.launchcode.caninecoach.repositories.CourseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final CourseRepository courseRepository;

    public SearchController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/type")
    public static ArrayList<Course> findByType(String column, String value, Iterable<Course> allCourses){
        ArrayList<Course> results = new ArrayList<>();


        if (value.equalsIgnoreCase("all")){
            return (ArrayList<Course>) allCourses;
        }

        if (column.equals("all")){
            results = findByValue(value, allCourses );
            return results;
        }
        for (Course course : allCourses) {

            String aValue = getFieldValue(course, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(course);
            }
        }

        return results;
    }


    public static String getFieldValue(Course course, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = course.getName();
        } else if (fieldName.equals("details")){
            theValue = course.getDetails().toString();
        } else {
            theValue = course.getType().toString();
        }

        return theValue;
    }
    public static ArrayList<Course> findByValue(String value, Iterable<Course> allCourses) {


        ArrayList<Course> results = new ArrayList<>();

        for (Course course: allCourses) {

            if (course.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(course);
            } else if (course.getType().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(course);
            } else if (course.getDetails().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(course);
            }

        }

        return results;
    }

}
