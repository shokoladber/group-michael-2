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

@Controller
@RequestMapping("blog")
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

    @GetMapping("/create")
    public String displayCreateBlogForm(){
        //template here?
        return "blog/create";
    }

    @PostMapping("/create")
    public String createBlog (@Valid Blog newBlog, Errors errors) {
        if (errors.hasErrors()){
            return "blog/create";
        }
        blogRepository.save(newBlog);
        return "redirect:";
    }

}
