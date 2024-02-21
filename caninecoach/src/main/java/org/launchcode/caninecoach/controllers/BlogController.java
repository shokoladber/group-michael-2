package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.Blog;
import org.launchcode.caninecoach.repositories.BlogRepository;
import org.launchcode.caninecoach.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private final BlogRepository blogRepository;


    @Autowired
    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }


    @GetMapping("/formdata")
    public String displayCreateBlogForm() {
        return "/formdata";
    }

    @PostMapping
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }


    @DeleteMapping("/api/blogs/{blogId}")
    public String deleteBlogById(@PathVariable int blogId) {
        return "Blog post with ID " + blogId + " has been deleted";
    }

}
