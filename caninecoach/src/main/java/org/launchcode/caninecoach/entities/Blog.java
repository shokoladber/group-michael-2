package org.launchcode.caninecoach.entities;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class Blog extends AbstractEntity{

    public Blog() {
    }

    public Blog(Date dateCreated, String content, String author) {
        this.dateCreated = dateCreated;
        this.content = content;
        this.author = author;
    }

    @Column(name ="created_at")
    private Date dateCreated;

    @NotNull
    @Min(3)
    private String content;

    @NotNull
    @Min(3)
    private String author;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
