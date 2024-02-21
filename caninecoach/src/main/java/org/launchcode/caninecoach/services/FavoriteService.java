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
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public void addFavoriteCourse(Course course, User user) {
        if (!favoriteRepository.existsByCourseAndUser(course, user)) {
            Favorite favorite = new Favorite(course, user);
            favoriteRepository.save(favorite);
        }
    }

    public void removeFavoriteCourse(Course course, User user) {
        Optional<Favorite> favoriteOptional = favoriteRepository.findByCourseAndUser(course, user);
        favoriteOptional.ifPresent(favoriteRepository::delete);
    }

    public List<Course> getFavoriteCourses(User user) {
        List<Favorite> favorites = favoriteRepository.findByUser(user);
        return favorites.stream().map(Favorite::getCourse).collect(Collectors.toList());
    }
}


