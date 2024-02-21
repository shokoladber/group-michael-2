package org.launchcode.caninecoach.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
@Entity
public class Profile extends AbstractEntity{

    //@JoinColumn(name = "user_id")
    //private User user_id;

    private String name;

    private String breed;

    private String age;

    private String photoUrl;

    public Profile(String name, String breed, String age, String photoUrl) {
        //this.user_id = user_id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.photoUrl = photoUrl;
    }

    public Profile() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }



}
