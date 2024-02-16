package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, Integer> {
}
