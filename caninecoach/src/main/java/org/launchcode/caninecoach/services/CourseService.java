package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.Course;
import org.launchcode.caninecoach.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<Course> findCourseById(int id){
        return courseRepository.findById(id);
    }

    public Course saveCourse (Course course){
        return courseRepository.save(course);
    }
    public List<Object[]> searchCoursesWithKeyword(String keyword) {
        return courseRepository.searchCoursesWithKeyword(keyword);
    }


    }

