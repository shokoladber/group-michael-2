package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.Course;
import org.launchcode.caninecoach.entities.Favorite;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {


    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public Favorite saveFavoriteCourse(Favorite favorite){
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> findFavorite(User user){
        return (List<Favorite>) favoriteRepository.findAll(user);
    }
}
