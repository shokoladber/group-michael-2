package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.Favorite;
import org.launchcode.caninecoach.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {


    Object findAll(User user);

}
