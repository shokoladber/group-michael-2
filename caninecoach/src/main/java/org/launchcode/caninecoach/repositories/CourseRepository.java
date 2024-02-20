package org.launchcode.caninecoach.repositories;

import jakarta.persistence.Id;
import org.launchcode.caninecoach.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {


    Optional<Course> findById(int id);

    @Query("SELECT c.name, t.name, d.description " +
            "FROM Course c " +
            "JOIN c.courseInfo ci " +
            "JOIN ci.type t " +
            "JOIN ci.details d " +
            "WHERE c.name LIKE %:keyword% OR t.name LIKE %:keyword% OR d.description LIKE %:keyword%")
    List<Object[]> searchCoursesWithKeyword(@Param("keyword") String keyword);;

}
