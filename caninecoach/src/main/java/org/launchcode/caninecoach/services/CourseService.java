package org.launchcode.caninecoach.services;

import jakarta.persistence.Id;
import org.launchcode.caninecoach.entities.Course;
import org.launchcode.caninecoach.entities.Type;
import org.launchcode.caninecoach.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Course> listCourses () {
        return courseRepository.findAll();
    }

    public Course saveCourse (Course course){
        return courseRepository.save(course);
    }

}
