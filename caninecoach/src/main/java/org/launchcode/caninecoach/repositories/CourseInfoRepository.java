package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInfoRepository extends JpaRepository<CourseInfo, Integer> {
}
