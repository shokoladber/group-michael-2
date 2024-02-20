package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.Blog;
import org.launchcode.caninecoach.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {


        @Autowired
        private BlogRepository blogRepository;

        public List<Blog> getAllBlogs() {
            return blogRepository.findAll();
        }

        public Blog createBlog(Blog blog) {
            return blogRepository.save(blog);
        }
    public void deleteBlogById(int blogId) {

        Optional<Blog> optionalBlog = blogRepository.findById(blogId);
        if (optionalBlog.isPresent()) {

            blogRepository.deleteById(blogId);
        } else {

            throw new RuntimeException("Blog post with ID " + blogId + " not found");
        }
    }
}





