package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

public interface BlogRepository extends CrudRepository<Blog, Integer> {

}
