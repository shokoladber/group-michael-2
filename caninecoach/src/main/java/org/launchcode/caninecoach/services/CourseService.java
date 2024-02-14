package org.launchcode.caninecoach.services;

import jakarta.persistence.Id;
import org.launchcode.caninecoach.entities.Course;
import org.launchcode.caninecoach.entities.Type;
import org.launchcode.caninecoach.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<Course> findCourseById(Id id){
        return courseRepository.findById(id);
    }

    public Course saveCourse (Course course){
        return courseRepository.save(course);
    }

    public static ArrayList<Course> findByType(String column, String value, Iterable<Course> allCourses){
        ArrayList<Course> results = new ArrayList<>();


        if (value.toLowerCase().equals("all")){
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

}
