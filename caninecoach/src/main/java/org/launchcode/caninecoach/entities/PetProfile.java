package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;

@Entity
public class PetProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Ensures the name cannot be null in the database
    private String name;

    @Column(nullable = false) // Ensures the type cannot be null
    private String type;

    @Column(nullable = true) // Age can be null if not provided
    private Integer age;

    @Column(length = 500) // Example of customizing column definition
    private String description;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY loading: guardian details are loaded on demand
    @JoinColumn(name = "guardian_id", nullable = false) // Specifies the foreign key column
    private User guardian;

    // Standard getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getGuardian() {
        return guardian;
    }

    public void setGuardian(User guardian) {
        this.guardian = guardian;
    }

    // Example toString method for debugging purposes
    @Override
    public String toString() {
        return "PetProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", guardianId=" + guardian.getId() + // For simplicity, just show guardian ID
                '}';
    }

    // Implement equals and hashCode based on ID for entity comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetProfile that = (PetProfile) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
