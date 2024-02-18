package org.launchcode.caninecoach.controllers;


import jakarta.validation.Valid;
import org.launchcode.caninecoach.entities.Blog;
import org.launchcode.caninecoach.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private final BlogRepository blogRepository;


    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @RequestMapping("/")
    public String displayBlog (){
        //template here?
    return "blog";
    }

    @GetMapping("/formdata")
    public String displayCreateBlogForm(){
        return "/formdata";
    }

    @PostMapping("/post")
    public String createBlog (@Valid Blog newBlog, Errors errors) {
        if (errors.hasErrors()){
            return "/post";
        }
        blogRepository.save(newBlog);
        return "redirect:";
    }

    @GetMapping("/api/blog/editing")
    public String deleteBlog(Blog blog) {
        return "/editing";
    }



}
